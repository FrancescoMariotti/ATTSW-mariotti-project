package com.mariotti.project.controllers;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.services.EmployeeService;
import com.mariotti.project.services.OfficeService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeWebController.class)
public class EmployeeWebControllerHtmlTest {

	@MockBean
	private EmployeeService employeeService;
	@MockBean
	private OfficeService officeService;

	@Autowired
	private WebClient webClient;

	@Test
	public void testEmployeesOfficePageTitle() throws Exception {
		HtmlPage page = webClient.getPage("/employees_office/1");
		assertThat(page.getTitleText()).isEqualTo("Employees");
	}

	@Test
	public void testEmployeesOfficePageWithoutEmployees() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		when(employeeService.getEmployeesByOffice(office)).thenReturn(Collections.emptyList());
		HtmlPage page = this.webClient.getPage("/employees_office/1");
		assertThat(page.getBody().getTextContent()).contains("No employee found in this office");
	}

	@Test
	public void testEmployeesOfficePageWithEmployeesShouldShowThemInATable() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		when(employeeService.getEmployeesByOffice(office)).thenReturn(
				asList(new Employee(1L, "employee1", 1000, office), new Employee(2L, "employee2", 2000, office)));
		HtmlPage page = this.webClient.getPage("/employees_office/1");
		assertThat(page.getBody().getTextContent()).doesNotContain("No employee found in this office");
		HtmlTable table = page.getHtmlElementById("employee_table");
		assertThat(removeWindowsCR(table.asText())).isEqualTo("Employees:\n" + "ID	" + "Name	" + "Salary\n" + "1	" + "employee1	"
				+ "1000\n" + "2	" + "employee2	" + "2000");
	}
	
	@Test
	public void testEditInexistentEmployee() throws Exception {
		when(employeeService.getEmployeeById(1L)).thenReturn(null);
		HtmlPage page = this.webClient.getPage("/employees_office/1/edit_employee/1");
		assertThat(page.getBody().getTextContent()).contains("No employee found with id: 1");
	}
	
	@Test
	public void testEditExistentEmployee() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		when(employeeService.getEmployeeById(1)).thenReturn(new Employee(1L, "original name", 1000, office));
		HtmlPage page = this.webClient.getPage("/employees_office/1/edit_employee/1");
		final HtmlForm form = page.getFormByName("employee_form");
		form.getInputByValue("original name").setValueAttribute("modified name");
		form.getInputByValue("1000").setValueAttribute("3000");
		form.getButtonByName("btn_submit").click();
		verify(employeeService).updateEmployeeById(1L, new Employee(1L, "modified name", 3000, office));
	}
	
	@Test
	public void testEditNewEmployee() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		HtmlPage page = this.webClient.getPage("/employees_office/1/new_employee");
		final HtmlForm form = page.getFormByName("employee_form");
		form.getInputByName("name").setValueAttribute("new name");
		form.getInputByName("salary").setValueAttribute("1000");
		form.getButtonByName("btn_submit").click();
		verify(employeeService).insertNewEmployee(new Employee(null, "new name", 1000, office));
	}
	
	@Test
	public void testEmployeesOfficePageShouldHaveALinkForAddingNewEmployee() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		HtmlPage page = this.webClient.getPage("/employees_office/1");
		assertThat(page.getAnchorByText("New employee").getHrefAttribute()).isEqualTo("/employees_office/1/new_employee");
	}
	
	private String removeWindowsCR(String s) {
		return s.replaceAll("\r", "");
	}
}

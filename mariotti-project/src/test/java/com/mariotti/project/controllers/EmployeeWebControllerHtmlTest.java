package com.mariotti.project.controllers;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
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
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
	}
}

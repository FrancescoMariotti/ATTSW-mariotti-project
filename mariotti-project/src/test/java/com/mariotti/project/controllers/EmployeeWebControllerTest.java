package com.mariotti.project.controllers;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.services.EmployeeService;
import com.mariotti.project.services.OfficeService;

import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = EmployeeWebController.class)
public class EmployeeWebControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private OfficeService officeService;
	@MockBean
	private EmployeeService employeeService;

	@Test
	public void testStatus200() throws Exception {
		Office office = new Office(1L, "test", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(get("/employees_office/1")).andExpect(status().is2xxSuccessful())
				.andExpect(model().attribute("office", office));
	}

	@Test
	public void testReturnEmployeesOfficeView() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		ModelAndViewAssert.assertViewName(mvc.perform(get("/employees_office/1")).andReturn().getModelAndView(),
				"employees_office");
	}

	@Test
	public void testEmployeesOfficeViewShowsEmployees() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		List<Employee> employees = asList(new Employee(1L, "test", 1000, office));
		when(employeeService.getEmployeesByOffice(office)).thenReturn(employees);
		mvc.perform(get("/employees_office/1")).andExpect(view().name("employees_office"))
				.andExpect(model().attribute("employees", employees)).andExpect(model().attribute("message", ""));
	}

	@Test
	public void testEmployeesOfficeViewWithNoEmployeesShowsMessage() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		when(employeeService.getEmployeesByOffice(office)).thenReturn(Collections.emptyList());
		mvc.perform(get("/employees_office/1")).andExpect(view().name("employees_office"))
				.andExpect(model().attribute("employees", Collections.emptyList()))
				.andExpect(model().attribute("message", "No employee found in this office"));
	}

	@Test
	public void testEditEmployeeWhenHeIsNotFound() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		when(employeeService.getEmployeeById(1L)).thenReturn(null);
		mvc.perform(get("/employees_office/1/edit_employee/1")).andExpect(view().name("edit_employee"))
				.andExpect(model().attribute("office", office)).andExpect(model().attribute("employee", nullValue()))
				.andExpect(model().attribute("message", "No employee found with id: 1"));
	}

	@Test
	public void testEditEmployeeWhenHeIsFound() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		Employee employee = new Employee(1L, "employee", 1000, office);
		when(employeeService.getEmployeeById(1L)).thenReturn(employee);
		mvc.perform(get("/employees_office/1/edit_employee/1")).andExpect(view().name("edit_employee"))
				.andExpect(model().attribute("office", office)).andExpect(model().attribute("employee", employee))
				.andExpect(model().attribute("message", ""));
	}

	@Test
	public void testEditNewEmployee() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(get("/employees_office/1/new_employee")).andExpect(view().name("edit_employee"))
				.andExpect(model().attribute("office", office))
				.andExpect(model().attribute("employee", new Employee(office)))
				.andExpect(model().attribute("message", ""));
		verifyZeroInteractions(employeeService);
	}

	@Test
	public void testPostEmployeeWithIdShouldUpdateExistingEmployee() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(MockMvcRequestBuilderUtils.postForm("/employees_office/1/save_employee",
				new Employee(1L, "test", 1000, office))).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/employees_office/1"));
		verify(employeeService).updateEmployeeById(1L, new Employee(1L, "test", 1000, office));
	}

	@Test
	public void testPostEmployeeWithNoIdShouldAddNewOffice() throws Exception {
		Office office = new Office(1L, "office", new ArrayList<Employee>());
		when(officeService.getOfficeById(1L)).thenReturn(office);
		mvc.perform(MockMvcRequestBuilderUtils.postForm("/employees_office/1/save_employee",
				new Employee(null, "test", 1000, office))).andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/employees_office/1"));
		verify(employeeService).insertNewEmployee(new Employee(null, "test", 1000, office));
	}
}

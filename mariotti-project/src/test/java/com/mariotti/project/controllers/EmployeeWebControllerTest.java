package com.mariotti.project.controllers;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.services.EmployeeService;
import com.mariotti.project.services.OfficeService;

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
			.andExpect(model().attribute("employees", employees));
	}

}

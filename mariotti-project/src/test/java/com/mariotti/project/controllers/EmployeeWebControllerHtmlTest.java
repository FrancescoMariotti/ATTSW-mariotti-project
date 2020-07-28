package com.mariotti.project.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
}

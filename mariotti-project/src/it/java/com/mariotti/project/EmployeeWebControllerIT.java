package com.mariotti.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.mariotti.project.models.Employee;
import com.mariotti.project.models.Office;
import com.mariotti.project.repositories.EmployeeRepository;
import com.mariotti.project.repositories.OfficeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeWebControllerIT {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private String url;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private OfficeRepository officeRepository;
	private Office office;

	@Before
	public void setup() {
		employeeRepository.deleteAll();
		officeRepository.deleteAll();
		office = officeRepository.save(new Office(null, "office", new ArrayList<>()));
		url = "http://localhost:" + port + "/employees_office/" + office.getId();
		driver = new HtmlUnitDriver();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void testEmployeesOfficePageShouldContainEmployee() {
		Employee employee = employeeRepository.save(new Employee(null, "employee", 1000, office));
		driver.get(url);
		assertThat(driver.findElement(By.id("employee_table")).getText()).contains("employee", "1000", "Edit");
		driver.findElement(By.cssSelector(
				"a[href*='/employees_office/" + office.getId() + "/edit_employee/" + employee.getId() + "']"));
	}

}

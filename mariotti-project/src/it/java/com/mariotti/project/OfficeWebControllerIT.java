package com.mariotti.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class OfficeWebControllerIT {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private String url;
	@Autowired
	private OfficeRepository officeRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Before
	public void setup() {
		officeRepository.deleteAll();
		url = "http://localhost:" + port;
		driver = new HtmlUnitDriver();
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void testHomePageShouldContainOffice() {
		Office office = officeRepository.save(new Office(null, "office", new ArrayList<>()));
		driver.get(url);
		assertThat(driver.findElement(By.id("office_table")).getText()).contains("office", "Edit");
		driver.findElement(By.cssSelector(
				"a[href*='/edit_office/" + office.getId() + "']"));
	}

	@Test
	public void testEditOfficePageNewOffice() throws Exception {
		driver.get(url + "/new_office");
		driver.findElement(By.name("name")).sendKeys("another office");
		driver.findElement(By.name("btn_submit")).click();
		assertThat(officeRepository.findByName("another office")).isNotNull();
	}

	@Test
	public void testEditOfficePageUpdateOffice() throws Exception {
		Office office = officeRepository.save(new Office(null, "old office", new ArrayList<>()));
		driver.get(url + "/edit_office/" + office.getId());
		final WebElement nameBox = driver.findElement(By.name("name"));
		nameBox.clear();
		nameBox.sendKeys("modified office");
		driver.findElement(By.name("btn_submit")).click();
		assertThat(officeRepository.findByName("modified office")).isNotNull();
	}
	
	@Test
	public void testDeleteOfficePageRemoveOfficeAndItsEmployees() throws Exception {
		Office office = officeRepository.save(new Office(null, "officeToDelete", new ArrayList<>()));
		employeeRepository.save(new Employee(null, "employeeToDelete", 1000, office));
		driver.get(url);
		driver.findElement(By.linkText("Delete")).click();
		assertThat(officeRepository.findByName("officeToDelete")).isNull();
		assertThat(employeeRepository.findByName("employeeToDelete")).isNull();
	}

}

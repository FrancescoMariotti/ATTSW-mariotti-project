package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ScenarioState;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GivenTheEmployeesOfficePageWithAnEmployee extends Stage<GivenTheEmployeesOfficePageWithAnEmployee> {

	private static int port = Integer.parseInt(System.getProperty("server.port", "8080"));
	private static String url = "http://localhost:" + port;

	@ScenarioState
	WebDriver driver;
	@ScenarioState
	String officeId;
	@ScenarioState
	String employeeId;

	@BeforeStage
	public void setup() {
		WebDriverManager.chromedriver().setup();
		url = "http://localhost:" + port;
		driver = new ChromeDriver();
	}

	public GivenTheEmployeesOfficePageWithAnEmployee theEmployeesOfficePageWithAnEmployee() {
		officeId = postOffice("an office");
		employeeId = postEmployee("an employee", 1000L);
		driver.get(url + "/employees_office/" + officeId);
		return self();
	}

	private String postOffice(String name) {
		driver.get(url);
		driver.findElement(By.cssSelector("a[href*='/new_office")).click();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("btn_submit")).click();
		String id = driver.findElement(By.id("office_table")).findElement(By.cssSelector("td")).getText();
		driver.findElement(By.cssSelector("a[href*='/employees_office/" + id)).click();
		return id;
	}

	private String postEmployee(String name, Long salary) {
		driver.get(url + "/employees_office/" + officeId);
		driver.findElement(By.cssSelector("a[href*='/employees_office/" + officeId + "/new_employee")).click();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("salary")).sendKeys(salary.toString());
		driver.findElement(By.name("btn_submit")).click();
		return driver.findElement(By.id("employee_table")).findElement(By.cssSelector("td")).getText();
	}
}

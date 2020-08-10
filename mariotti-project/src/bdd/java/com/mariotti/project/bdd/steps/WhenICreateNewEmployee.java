package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class WhenICreateNewEmployee extends Stage<WhenICreateNewEmployee> {

	@ScenarioState
	WebDriver driver;
	@ScenarioState
	String officeId;

	public WhenICreateNewEmployee iCreateNewEmployee() {
		driver.findElement(By.cssSelector("a[href*='/employees_office/" + officeId + "/new_employee")).click();
		driver.findElement(By.name("name")).sendKeys("new employee");
		driver.findElement(By.name("salary")).sendKeys("1000");
		driver.findElement(By.name("btn_submit")).click();
		return self();
	}
}

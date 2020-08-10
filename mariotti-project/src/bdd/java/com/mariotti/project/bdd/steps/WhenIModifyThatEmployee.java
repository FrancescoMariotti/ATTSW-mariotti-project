package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class WhenIModifyThatEmployee extends Stage<WhenIModifyThatEmployee> {

	@ScenarioState
	WebDriver driver;
	@ScenarioState
	String officeId;
	@ScenarioState
	String employeeId;

	public WhenIModifyThatEmployee iModifyThatEmployee() {
		driver.findElement(By.cssSelector("a[href*='/employees_office/" + officeId + "/edit_employee/" 
				+ employeeId)).click();
		final WebElement nameField = driver.findElement(By.name("name"));
		nameField.clear();
		nameField.sendKeys("modified employee");
		final WebElement salaryField = driver.findElement(By.name("salary"));
		salaryField.clear();
		salaryField.sendKeys("2000");
		driver.findElement(By.name("btn_submit")).click();
		return self();
	}
}

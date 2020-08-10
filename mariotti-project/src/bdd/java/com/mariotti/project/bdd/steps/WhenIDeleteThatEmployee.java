package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class WhenIDeleteThatEmployee extends Stage<WhenIDeleteThatEmployee> {

	@ScenarioState
	WebDriver driver;
	@ScenarioState
	String officeId;
	@ScenarioState
	String employeeId;

	public WhenIDeleteThatEmployee iDeleteThatEmployee() {
		driver.findElement(By.cssSelector("a[href*='/employees_office/" + officeId + "/delete_employee/" 
				+ employeeId)).click();
		return self();
	}
}

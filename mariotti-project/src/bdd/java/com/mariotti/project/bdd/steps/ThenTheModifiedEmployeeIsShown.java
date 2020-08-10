package com.mariotti.project.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class ThenTheModifiedEmployeeIsShown extends Stage<ThenTheModifiedEmployeeIsShown> {

	@ScenarioState
	WebDriver driver;
	@ScenarioState
	String officeId;
	@ScenarioState
	String employeeId;

	@AfterStage
	public void teardown() {
		driver.quit();
	}

	public ThenTheModifiedEmployeeIsShown theModifiedEmployeeIsShown() {
		assertThat(driver.findElement(By.id("employee_table")).getText()).contains(employeeId, "modified employee");
		return self();
	}
}


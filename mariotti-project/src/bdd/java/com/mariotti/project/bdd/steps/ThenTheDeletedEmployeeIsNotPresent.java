package com.mariotti.project.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class ThenTheDeletedEmployeeIsNotPresent extends Stage<ThenTheDeletedEmployeeIsNotPresent> {

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

	public ThenTheDeletedEmployeeIsNotPresent theDeletedEmployeeIsNotPresent() {
		assertThat(driver.findElement(By.id("employee_table")).getText()).doesNotContain(employeeId + " ");
		return self();
	}
}

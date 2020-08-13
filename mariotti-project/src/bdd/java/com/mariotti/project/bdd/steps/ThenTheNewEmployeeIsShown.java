package com.mariotti.project.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class ThenTheNewEmployeeIsShown extends Stage<ThenTheNewEmployeeIsShown> {

	@ScenarioState
	WebDriver driver;

	@AfterStage
	public void teardown() {
		driver.quit();
	}

	public ThenTheNewEmployeeIsShown theNewEmployeeIsShown() {
		assertThat(driver.findElement(By.id("employee_table")).getText()).contains("new employee", "1000");
		return self();
	}
}

package com.mariotti.project.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class ThenTheDeletedOfficeIsNotPresent extends Stage<ThenTheDeletedOfficeIsNotPresent> {

	@ScenarioState
	WebDriver driver;

	@ScenarioState
	String id;

	@AfterStage
	public void teardown() {
		driver.quit();
	}

	public ThenTheDeletedOfficeIsNotPresent theDeletedOfficeIsNotPresent() {
		assertThat(driver.findElement(By.cssSelector("body")).getText()).doesNotContain(id + " ");
		return self();
	}
}

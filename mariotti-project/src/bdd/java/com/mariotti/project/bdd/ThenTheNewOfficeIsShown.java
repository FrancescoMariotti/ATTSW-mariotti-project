package com.mariotti.project.bdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

public class ThenTheNewOfficeIsShown extends Stage<ThenTheNewOfficeIsShown> {
	
	@ExpectedScenarioState
	WebDriver driver;
	
	@AfterStage
	public void teardown() {
		driver.quit();
	}
	
    public ThenTheNewOfficeIsShown theNewOfficeIsShown() {
    	assertThat(driver.findElement(By.id("office_table")).getText()).contains("new office");
        return self();
    }
}

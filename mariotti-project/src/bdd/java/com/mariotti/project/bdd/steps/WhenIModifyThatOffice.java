package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

public class WhenIModifyThatOffice extends Stage<WhenIModifyThatOffice> {

	@ExpectedScenarioState
	WebDriver driver;

	@ExpectedScenarioState
	String id;

	public WhenIModifyThatOffice iModifyThatOffice() {
		driver.findElement(By.cssSelector("a[href*='/edit_office/" + id + "']")).click();
		final WebElement nameField = driver.findElement(By.name("name"));
		nameField.clear();
		nameField.sendKeys("modified office");
		driver.findElement(By.name("btn_submit")).click();
		return self();
	}
}


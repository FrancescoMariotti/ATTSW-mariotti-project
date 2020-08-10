package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;

public class WhenIDeleteThatOffice extends Stage<WhenIDeleteThatOffice> {

	@ScenarioState
	WebDriver driver;

	@ScenarioState
	String id;

	public WhenIDeleteThatOffice iDeleteThatOffice() {
		driver.findElement(By.cssSelector("a[href*='/delete_office/" + id + "']")).click();
		return self();
	}
}

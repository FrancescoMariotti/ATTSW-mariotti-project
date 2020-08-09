package com.mariotti.project.bdd.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

public class WhenICreateNewOffice extends Stage<WhenICreateNewOffice> {
	
	@ExpectedScenarioState
	WebDriver driver;
	
    public WhenICreateNewOffice iCreateNewOffice() {
    	driver.findElement(By.cssSelector("a[href*='/new_office")).click();
		driver.findElement(By.name("name")).sendKeys("new office");
		driver.findElement(By.name("btn_submit")).click();
        return self();
    }
}

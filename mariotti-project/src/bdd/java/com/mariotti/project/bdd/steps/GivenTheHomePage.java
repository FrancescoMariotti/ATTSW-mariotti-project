package com.mariotti.project.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ScenarioState;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GivenTheHomePage extends Stage<GivenTheHomePage> {
	
	private static int port = Integer.parseInt(System.getProperty("server.port", "8080"));
	private static String url = "http://localhost:" + port;
	
	@ScenarioState
	WebDriver driver;
	
	@BeforeStage
	public void setup() {
		WebDriverManager.chromedriver().setup();
		url = "http://localhost:" + port;
		driver = new ChromeDriver();
	}

    public GivenTheHomePage theHomePage() {
    	driver.get(url);
        return self();
    }
}

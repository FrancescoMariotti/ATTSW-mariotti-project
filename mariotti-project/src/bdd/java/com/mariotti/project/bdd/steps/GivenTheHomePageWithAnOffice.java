package com.mariotti.project.bdd.steps;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ScenarioState;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GivenTheHomePageWithAnOffice extends Stage<GivenTheHomePageWithAnOffice> {

	private static int port = Integer.parseInt(System.getProperty("server.port", "8080"));
	private static String url = "http://localhost:" + port;

	@ScenarioState
	WebDriver driver;

	@ScenarioState
	String id;

	@BeforeStage
	public void setup() {
		WebDriverManager.chromedriver().setup();
		url = "http://localhost:" + port;
		driver = new ChromeDriver();
	}

	public GivenTheHomePageWithAnOffice theHomePageWithAnOffice() throws JSONException {
		id = postOffice("an office");
		driver.get(url);
		return self();
	}

	private String postOffice(String name) {
		driver.get(url);
		driver.findElement(By.cssSelector("a[href*='/new_office")).click();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.name("btn_submit")).click();
		// return the id of the office
		return driver.findElement(By.id("office_table")).findElement(By.cssSelector("td")).getText();
	}
}

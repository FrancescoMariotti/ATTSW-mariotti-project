package com.mariotti.project.bdd.steps;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GivenTheHomePageWithAnOffice extends Stage<GivenTheHomePageWithAnOffice> {

	private static int port = Integer.parseInt(System.getProperty("server.port", "8080"));
	private static String url = "http://localhost:" + port;

	@ProvidedScenarioState
	WebDriver driver;
	
	@ProvidedScenarioState
	String id;

	@BeforeStage
	public void setup() {
		WebDriverManager.chromedriver().setup();
		url = "http://localhost:" + port;
		driver = new ChromeDriver();
	}

	public GivenTheHomePageWithAnOffice theHomePageWithAnOffice() throws JSONException {
		id = postOffice("office to edit");
		driver.get(url);
		return self();
	}
	
	private String postOffice(String name){
		driver.get(url);
		driver.findElement(By.cssSelector("a[href*='/new_office")).click();
		driver.findElement(By.name("name")).sendKeys("office to edit");
		driver.findElement(By.name("btn_submit")).click();
		return driver.findElement(By.id("office_table")).findElement(By.cssSelector("td")).getText();
	}
}


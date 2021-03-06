package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EdgeTest {
	private WebDriver driver;

	@Test
	public void testEasy() throws InterruptedException {
		driver.get("http://www.google.com/xhtml");
		Thread.sleep(2000);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("EdgeDriver");
		searchBox.submit();
		Thread.sleep(2000);
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
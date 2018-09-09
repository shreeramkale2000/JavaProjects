package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IETest {
	private WebDriver driver;

	@Test
	public void testEasy() throws InterruptedException {
		driver.get("http://www.google.com/xhtml");
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
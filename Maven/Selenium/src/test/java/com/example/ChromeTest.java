package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeTest {
	private WebDriver driver;

	@Test
	public void testEasy() throws InterruptedException {
		driver.get("http://www.google.com/xhtml");
		Thread.sleep(2000);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		Thread.sleep(2000);
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setBinary("D:/ProgramFiles/PortableApps/GoogleChromePortable/App/Chrome-bin/Chrome.exe");
		driver = new ChromeDriver(options);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
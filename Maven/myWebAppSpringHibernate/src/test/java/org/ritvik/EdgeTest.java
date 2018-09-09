package org.ritvik;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EdgeTest {
	private WebDriver driver;

	@Test
	public void testEasy() {
		//driver.get("https://localhost:8084/myWebApp/REST/TestControllerModel");
		driver.get("http://localhost:8086/myWebApp/REST/TestControllerModel");
	}

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "src/test/resources/MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
		Thread.sleep(10000);
	}

	@AfterTest
	public void afterTest() {
		//driver.quit();
	}
}
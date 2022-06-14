package com.intrado.communicate.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.intrado.communicate.pageobjects.ParentPortalLoginPage;

public class ParentPortalAuthenticationTest {
	ParentPortalLoginPage pplp ;

	private static final String CHROMEDRIVER_BINARY_PATH = System.getProperty("user.dir")
			+ "\\driver\\chromedriver.exe";

	@Test
	public void testInvalidCredentials() throws InterruptedException {
		WebDriver driver = null;
		pplp = new ParentPortalLoginPage(driver);
		String emailAddress = "incorrect@email.com";
		String password = "WrongPassword1!";
		String url = "https://go.schoolmessenger.com/#/account/login";
		driver = getNewDriver() ;
		pplp.openWith(driver, url);
		pplp.setEmail(driver,emailAddress);
		pplp.setPassword(driver,password);
		pplp.clickLogInExpectingError(driver);
		pplp.assertAuthenticationError(driver);
		driver.quit();
	}

	private WebDriver getNewDriver() {
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_BINARY_PATH);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type", "--incognito", "--start-maximized");

		return new ChromeDriver(options);
	}
}
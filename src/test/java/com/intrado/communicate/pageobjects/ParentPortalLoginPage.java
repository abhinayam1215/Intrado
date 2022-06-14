package com.intrado.communicate.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ParentPortalLoginPage {
	
	By username = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='password']");
	By login_button = By.xpath("//button[text()='Log in']");
	By error_msg = By.xpath("(//p[text()='Authentication has failed.'])[1]");
	
	public ParentPortalLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void openWith(WebDriver driver,String url) {
		driver.get(url);
	}
	
	public void setEmail(WebDriver driver,String email) {
		driver.findElement(username).sendKeys(email);
	}

	public void setPassword(WebDriver driver,String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickLogInExpectingError(WebDriver driver) throws InterruptedException {
		driver.findElement(login_button).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void assertAuthenticationError(WebDriver driver) {
		String actual = driver.findElement(error_msg).getText();
		Assert.assertEquals(actual, "Authentication has failed.");
	}
}

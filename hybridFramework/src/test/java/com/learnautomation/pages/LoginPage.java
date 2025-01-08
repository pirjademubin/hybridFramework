package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
		
	}
	
	@FindBy(name = "email")
	WebElement username;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//div[text()='Login']")
	WebElement LoginButton;
	
	public void logintoCRM(String user, String pass) throws InterruptedException {
		Thread.sleep(2000);
		username.sendKeys(user);
		password.sendKeys(pass);
		LoginButton.click();
		Thread.sleep(10000);
		
	}
}

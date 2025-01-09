package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;
import com.learnautomation.utilities.ExcelDataProvider;

public class LoginTestCRM extends BaseClass {	
	
	
	@Test(priority=1)
	public void LoginApp() throws InterruptedException {
		
		ExcelDataProvider excel = new ExcelDataProvider();
		logger = report.createTest("Login to FreeCRM");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		loginPage.logintoCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		logger.pass("Login Done Successfully");
		
	}
	
//	@Test(priority=2)
//	public void LoginApp1() throws InterruptedException {
//		
//		ExcelDataProvider excel = new ExcelDataProvider();
//		logger = report.createTest("Logout");
//		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//		logger.info("Starting Application");
//		loginPage.logintoCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
//		logger.fail("Logout Failed");
//		
//	}

}

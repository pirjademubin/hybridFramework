package com.learnautomation.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.learnautomation.utilities.BrowserFactory;
import com.learnautomation.utilities.ConfigDataProvider;
import com.learnautomation.utilities.ExcelDataProvider;
import com.learnautomation.utilities.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider cp;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite() {
		Reporter.log("Setting Up Reports and Test is getting ready", true);
		excel = new ExcelDataProvider();
		cp = new ConfigDataProvider();
		ExtentSparkReporter reporter = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "./Reports/FreeCRM_"+ Helper.getCurrentDateTime() + ".html"));
		//ExtentHtmlReporter reporter= new ExtentHtmlReporter(new File(System.getProperty("user.dir"+ "./Reports/FreeCRM.html")));
		report = new ExtentReports();
		report.attachReporter(reporter);
		Reporter.log("All configurations done, Test can be started", true);
	}
	
	@Parameters({"browser", "URL"})
	@BeforeClass
	public void setUp(String browser, String url) {
		Reporter.log("Trying to start browser and application ready", true);
		//driver = BrowserFactory.startApplication(driver, cp.getBrowser(), cp.getURL());
		driver = BrowserFactory.startApplication(driver, browser, url);
		Reporter.log("Browser and Application is up and running", true);
	}
	
	@AfterClass
	public void tearDown() {
		Reporter.log("Closing the Application and browser", true);
		BrowserFactory.quitApplication(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			Helper.captureScreenshot(driver);
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			Helper.captureScreenshot(driver);
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			Helper.captureScreenshot(driver);
			logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		report.flush();
		
	}
}

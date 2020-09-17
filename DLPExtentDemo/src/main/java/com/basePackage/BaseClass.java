package com.basePackage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.extentManager.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	@BeforeSuite
	public void BeforeSuite() {
		ExtentManager.setExtent();
	}

	@AfterSuite
	public void AfterSuite() {
		ExtentManager.endReport();
	}

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		/*
		 * ChromeOptions chromeOptions= new ChromeOptions();
		 * chromeOptions.addArguments("headless");
		 * chromeOptions.addArguments("window-size=1980,1080");
		 */ driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.0.43:81/home");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);

	}

	//@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		driver.close();
	}

	

	public static String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShot\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/Demo4/ws/ExtentDemo/ScreenShot/" + filename + "_" + dateName
				+ ".png";
		return newImageString;
	}

	public static String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}
}

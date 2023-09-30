package com.Acttime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Acttime.pom.HomePage;
import com.Acttime.pom.LoginPage;

public class BaseClass {
	public static WebDriver driver;
	@BeforeSuite
	public void databaseconnection() {
		Reporter.log("database connected",true);
	}
	@BeforeClass
	public void launchbrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.actitime.com/login.do");
		Reporter.log("Browser launched succeefully",true);
	}
	
	@BeforeMethod
	public void LoginToActtime() throws IOException {
		LoginPage lp = new LoginPage(driver);
		FileLibrary f = new FileLibrary();
		String un = f.readDataProperty("username");
		lp.getUntbx().sendKeys(un);
		String pw = f.readDataProperty("password");
		lp.getPwtbx().sendKeys(pw);
		lp.getLgbtn().click();
		Reporter.log("Logged in Successfully",true);
	}
	
	@AfterMethod
	public void logoutFromActtime() {
		HomePage hp = new HomePage(driver);
		hp.getTasktab().click();
		Reporter.log("Loogged out successfully",true);
	}
	
	@AfterClass
	public void closebrowser() {
		driver.close();
		Reporter.log("Browser closed successfully",true);
	}
	
	@AfterSuite
	public void databasedisconnect() {
		Reporter.log("Database disconnect successfully",true);
	}
}

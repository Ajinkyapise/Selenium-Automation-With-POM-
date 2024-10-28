package com.automation.tests;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.automation.pages.base.basePage;
import com.automation.pages.home.homePage;
import com.automation.pages.login.loginPage;
import com.automation.pages.oppoPage.oppoPage;
import com.automation.utility.Constants;
import com.automation.utility.PropertiesUtility;

public class SelAuto extends basePage {
	String username = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "username");
	String passString = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "password");

	Logger mylog;
//
//	@BeforeMethod
//	public void setUp(Method method) {
//
//		extentReport.startExtentReport();
//		extentReport.startSingleTestReport(method.getName());
//	}
//
//	@AfterMethod
//	public void tearDown() {
//		// End the report after the test is done
//		extentReport.endReport();
//	}

	@Test(priority = 1)
	public void Test1() throws InterruptedException {
		String actualText = "Please enter your password.";
		launchBrowser("safari");
		url();

		loginPage page = new loginPage(driver);
		page.enterUsername(username);
		page.clearPassword();
		page.buttonClick();
		page.TextCheck(actualText);
		close("Test 1");

	}

	@Test(priority = 2)
	public void Test2() throws InterruptedException {
		launchBrowser("safari");
		url();
		loginPage page = new loginPage(driver);
		homePage homePage = new homePage(driver);
		page.enterUsername(username);
		page.enterPassword(passString);
		page.buttonClick();
		homePage.titleCheck();
		close("Test2");
	}

	@Test(priority = 3)
	public void Test3() throws InterruptedException {
		launchBrowser("safari");
		url();
		String homeUsernameString = "NewAjinkya Newpise";
		loginPage page = new loginPage(driver);
		homePage homePage = new homePage(driver);
		page.enterUsername(username);
		page.enterPassword(passString);
		page.rememberMeButton();
		page.buttonClick();
		homePage.titleCheck();
		homePage.userNameCheck(homeUsernameString);
		homePage.menuOpen();
		homePage.logoutClick();
		page.titleCheck();
		page.rememberedUserNameCheck(username);
		close("Test3");
	}

	@Test(priority = 4)
	public void Test4b() throws InterruptedException {

		launchBrowser("safari");
		url();
		String usernameString = "123";
		String passwordString = "22131";
		loginPage page = new loginPage(driver);
		page.enterUsername(usernameString);
		page.enterPassword(passwordString);
		page.buttonClick();
		page.errorCheck();

		close("Test4b");
	}

	@Test(priority = 5)
	public void Test17() throws InterruptedException {

		launchBrowser("safari");
		url();

		loginPage page = new loginPage(driver);
		homePage homePage = new homePage(driver);
		oppoPage oppotuPage = new oppoPage(driver);
		page.enterUsername(username);
		page.enterPassword(passString);
		page.buttonClick();
		homePage.titleCheck();
		homePage.oppobuttonClick();
		oppotuPage.titleCheck();
		oppotuPage.oppoPipelinebuttonClick();
		oppotuPage.oppoPipelinetitleCheck();
		close("Test17");

	}
}
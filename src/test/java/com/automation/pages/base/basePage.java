package com.automation.pages.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.base.baseTest;
import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;

public class basePage extends baseTest {

	private static final Logger log = LogManager.getLogger(basePage.class);
	protected static ExtentReportsUtility extentReport = ExtentReportsUtility.getInstance();

	public static void LoginWpass(String a) throws InterruptedException {

		log.info("Launching browser: {}", a);
		extentReport.logTestInfo("Launching browser: " + a);

		launchBrowser(a);

		log.info("Navigating to Salesforce login page.");
		extentReport.logTestInfo("Navigating to Salesforce login page.");
		driver.get("https://login.salesforce.com/");

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

		log.debug("Login page loaded, proceeding with entering credentials.");
		extentReport.logTestInfo("Login page loaded, proceeding with entering credentials.");
		usernameNPass();
	}

	public static void url() throws InterruptedException {
		String urlString = PropertiesUtility.readDataFromPropertyFile(Constants.APPLICATION_PROPERTIES, "url");
		log.info("Navigating to URL: {}", urlString);
		extentReport.logTestInfo("Navigating to URL: " + urlString);
		driver.get(urlString);
	}

	public static void usernameNPass() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			// Username entered
			WebElement users = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
			log.debug("Username field is visible.");
			extentReport.logTestInfo("Username field is visible.");
			enterText(users, "ajinkyapise@gmail.com", "username");

			// Password entered
			WebElement pass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
			log.debug("Password field is visible.");
			extentReport.logTestInfo("Password field is visible.");
			enterText(pass, "Ajinkya@3599", "password");

			// Login Button Click
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Login")));
			log.debug("Login button is clickable.");
			extentReport.logTestInfo("Login button is clickable.");
			buttonClick(loginButton, "Login");

			wait.until(ExpectedConditions.urlContains("home")); // Wait for login to complete
			log.info("Login successful, home page loaded.");
			extentReport.logTestInfo("Login successful, home page loaded.");
		} catch (Exception e) {
			log.error("Error during login process.", e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void close(String Testname) {
		log.info("Closing browser for test: {}", Testname);
		extentReport.logTestInfo("Closing browser for test: " + Testname);
		driver.close();
		log.info("{} is completed and driver is closed.", Testname);
		extentReport.logTestInfo(Testname + " is completed and browser closed.");
	}

	public static void Logout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			log.info("Attempting to logout.");
			extentReport.logTestInfo("Attempting to logout.");

			WebElement LOButton = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]")));
			eleCheck(LOButton, "Logout", "Logout");
			buttonClick(LOButton, "Logout");

			log.info("Logout successful.");
			extentReport.logTestInfo("Logout successful.");
		} catch (Exception e) {
			log.error("Error during logout process.", e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void enterText(WebElement ele, String data, String obj) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(data);
				log.info("Data entered in {}: {}", obj, data);
				extentReport.logTestInfo("Data entered in " + obj + ": " + data);
			} else {
				log.warn("{} does not exist.", obj);
				extentReport.logTestFailed(obj + " does not exist.");
			}
		} catch (Exception e) {
			log.error("Error entering text in {}", obj, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void buttonClick(WebElement ele, String obj) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			if (ele.isDisplayed()) {
				ele.click();
				log.info("{} button clicked.", obj);
				extentReport.logTestInfo(obj + " button clicked.");
			} else {
				log.warn("{} button does not exist.", obj);
				extentReport.logTestFailed(obj + " button does not exist.");
			}
		} catch (Exception e) {
			log.error("Error clicking button {}", obj, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void clearField(WebElement field) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(field));
			field.clear();
			log.info("Cleared field.");
			extentReport.logTestInfo("Cleared field.");
		} catch (Exception e) {
			log.error("Error clearing field.", e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void checkTextError(WebElement ele, String actualText, String obj) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			String passText = ele.getText();
			log.debug("Error found is: {}", passText);
			extentReport.logTestInfo("Error found is: " + passText);
			if (ele.isDisplayed() && actualText.equals(passText)) {
				log.info("Correct error message displayed for {}.", obj);
				extentReport.logTestInfo("Correct error message displayed for " + obj);
			} else {
				log.warn("Wrong error message displayed for {}.", obj);
				extentReport.logTestFailed("Wrong error message displayed for " + obj);
			}
		} catch (Exception e) {
			log.error("Error checking text in {}", obj, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void checkTitle(WebElement foundText, String actualText, String obj) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			String kk = foundText.getText();
			log.debug("Found title: {}", kk);
			extentReport.logTestInfo("Found title: " + kk);
			wait.until(ExpectedConditions.titleIs(kk));
			if (kk.equals(actualText)) {
				log.info("{} was opened.", obj);
				extentReport.logTestInfo(obj + " was opened.");
			} else {
				log.warn("{} was not opened.", obj);
				extentReport.logTestFailed(obj + " was not opened.");
			}
		} catch (Exception e) {
			log.error("Error checking title for {}", obj, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void checkText(WebElement foundText, String actualText, String obj) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(foundText));
			String passText = foundText.getText();
			if (passText.equals(actualText)) {
				log.info("Correct {} is present.", obj);
				extentReport.logTestInfo("Correct " + obj + " is present.");
			} else {
				log.warn("{} is not present.", obj);
				extentReport.logTestFailed(obj + " is not present.");
			}
		} catch (Exception e) {
			log.error("Error checking text for {}", obj, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void correctNames(WebElement a, String b) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(a));
			String c = a.getText();
			if (c.equals(b)) {
				log.info("Correct element of {} is present.", b);
				extentReport.logTestInfo("Correct element of " + b + " is present.");
			} else {
				log.warn("Wrong element of {} is present.", b);
				extentReport.logTestFailed("Wrong element of " + b + " is present.");
			}
		} catch (Exception e) {
			log.error("Error checking names for {}", b, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void selectByValue(WebElement content, String a, String b) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(content));
			Select select = new Select(content);
			select.selectByValue(a);
			log.info("{} is selected.", b);
			extentReport.logTestInfo(b + " is selected.");
		} catch (Exception e) {
			log.error("Error selecting value {} for {}", a, b, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void checkTextContains(WebElement driver2, String expectedText, String b) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver2));
			String actualText = driver2.getText();
			if (actualText.contains(expectedText)) {
				log.info("{} contains the expected text.", b);
				extentReport.logTestInfo(b + " contains the expected text.");
			} else {
				log.warn("The expected string was NOT found in {}.", b);
				extentReport.logTestFailed("The expected string was NOT found in " + b);
			}
		} catch (Exception e) {
			log.error("Error checking if {} contains {}", b, expectedText, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void eleCheck(WebElement content, String a, String b) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(content));
			if (content.getText().equals(a)) {
				log.info("Correct element {} is present in the list.", b);
				extentReport.logTestInfo("Correct element " + b + " is present in the list.");
			} else {
				log.warn("{} is not present in list.", b);
				extentReport.logTestFailed(b + " is not present in list.");
			}
		} catch (Exception e) {
			log.error("Error checking element {}", b, e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void childCount(List<WebElement> bodyChildren, String tableName, int numToBeChecked) {
		int childCount = bodyChildren.size();
		if (childCount > numToBeChecked) {
			log.info("{} has more than {} elements.", tableName, numToBeChecked);
			extentReport.logTestInfo(tableName + " has more than " + numToBeChecked + " elements.");
		} else {
			log.warn("{} has less than {} elements.", tableName);
			extentReport.logTestFailed(tableName + " has less than " + numToBeChecked + " elements.");
			close("Test as conditions did not meet");
		}
	}

	public static void changeIframeToChild(WebElement ifElement) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ifElement));
			log.debug("Switched to child iframe.");
			extentReport.logTestInfo("Switched to child iframe.");
		} catch (Exception e) {
			log.error("Error switching to child iframe.", e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void changeIframeToParent(String parentOrDefaultFrames) {
		try {
			if (parentOrDefaultFrames.equals("parent")) {
				driver.switchTo().parentFrame();
				log.debug("Switched to parent frame.");
				extentReport.logTestInfo("Switched to parent frame.");
			} else if (parentOrDefaultFrames.equals("default")) {
				driver.switchTo().defaultContent();
				log.debug("Switched to default content.");
				extentReport.logTestInfo("Switched to default content.");
			}
		} catch (Exception e) {
			log.error("Error switching iframe context.", e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public static void testLeadsTabPresence() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement leadsTab = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Lead_Tab\"]")));
			eleCheck(leadsTab, "Lead_Tab", "leadsTab");
			log.info("Leads tab is present.");
			extentReport.logTestInfo("Leads tab is present.");
		} catch (Exception e) {
			log.error("Leads tab element not found on the page.", e);
			extentReport.logTestFailedWithException(e);
		}
	}

	public void takeScreenshot(String filepath) {
		TakesScreenshot screenCapture = (TakesScreenshot) driver;
		File src = screenCapture.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filepath);

		try {
			Files.copy(src.toPath(), destFile.toPath());
			log.info("Screenshot captured successfully and saved at {}", filepath);
			extentReport.logTestInfo("Screenshot captured successfully and saved at " + filepath);
		} catch (IOException e) {
			log.error("Problem occurred during screenshot capture.", e);
			extentReport.logTestFailedWithException(e);
		}
	}
}

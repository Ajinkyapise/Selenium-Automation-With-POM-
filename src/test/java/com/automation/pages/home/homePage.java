package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.basePage;

public class homePage extends basePage {
	WebDriver driver;

	@FindBy(xpath = "/html/head/title")
	WebElement title;
	@FindBy(id = "userNavButton")
	WebElement usname;
	@FindBy(xpath = "//*[@id=\"Opportunity_Tab\"]/a")
	WebElement OppoButton;

	public homePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void titleCheck() throws InterruptedException {
		checkTitle(title, "Home Page ~ Salesforce - Developer Edition", "Home");
	}

	public void userNameCheck(String username) throws InterruptedException {
		checkText(usname, username, "User Name");
	}

	public void menuOpen() {
		buttonClick(usname, "Menu");
	}

	public void oppobuttonClick() throws InterruptedException {
		buttonClick(OppoButton, "Opportunities");
	}

	public void logoutClick() {
		Logout();
	}

}

package com.automation.pages.oppoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.basePage;

public class oppoPage extends basePage {
	WebDriver driver;

	@FindBy(xpath = "/html/head/title")
	WebElement title;
	@FindBy(xpath = "//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a")
	WebElement OppoPipeButton;

	public oppoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void titleCheck() throws InterruptedException {
		checkTitle(title, "Home Page ~ Salesforce - Developer Edition", "Opportunities");
	}

	public void oppoPipelinebuttonClick() throws InterruptedException {
		buttonClick(OppoPipeButton, "Opportunities Pipeline");
	}

	public void oppoPipelinetitleCheck() throws InterruptedException {
		checkTitle(title, "Opportunity Pipeline ~ Salesforce - Developer Edition", "Opportunities Pipeline");
	}

	public void logoutClick() {
		Logout();
	}
}

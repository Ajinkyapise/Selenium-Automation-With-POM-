package com.automation.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.basePage;

public class loginPage extends basePage {

	WebDriver driver;
	@FindBy(id = "username")
	WebElement usersElement;
	@FindBy(id = "password")
	WebElement passwordElement;
	@FindBy(id = "Login")
	WebElement logiElement;
	@FindBy(id = "error")
	WebElement errorElement;
	@FindBy(id = "rememberUn")
	WebElement rememberMeElement;
	@FindBy(xpath = "/html/head/title")
	WebElement title;
	@FindBy(id = "idcard-identity")
	WebElement rememberedUserName;
	@FindBy(xpath = "//*[@id=\"error\"]")
	WebElement errorMessageWE;

	public loginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) throws InterruptedException {
		enterText(usersElement, username, "User Name");
	}

	public void enterPassword(String pass) throws InterruptedException {
		enterText(passwordElement, pass, "Password");
	}

	public void clearPassword() throws InterruptedException {
		clearField(passwordElement);
	}

	public void buttonClick() throws InterruptedException {
		buttonClick(logiElement, "Login Button");
	}

	public void TextCheck(String actualText) throws InterruptedException {
		checkText(errorElement, actualText, "PassWord Text");
	}

	public void rememberMeButton() {
		buttonClick(rememberMeElement, "Remember Me");
	}

	public void titleCheck() throws InterruptedException {
		checkTitle(title, "Home Page ~ Salesforce - Developer Edition", "Login");
	}

	public void rememberedUserNameCheck(String remUserName) {
		checkText(rememberedUserName, remUserName, "Rembered User Name Text");
	}

	public void errorCheck() {
		checkText(errorMessageWE,
				"Please check your username and password. If you still can't log in, contact your Salesforce administrator.",
				"Error Message");
	}

}

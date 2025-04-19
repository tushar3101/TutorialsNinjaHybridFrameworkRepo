package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="email")
	private WebElement emailAddressField;
	
	@FindBy(name="password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(className="alert-dismissible")
	private WebElement emailPasswordNotMatchingWarning;
	
	public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		 String warningText = emailPasswordNotMatchingWarning.getText();
		 return warningText;
	}
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordtext) {
		passwordField.sendKeys(passwordtext);
	}
	
	public void clickonLoginButton() {
		loginButton.click();
	}
	
	
	
}

package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	public AccountSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	private WebElement yourAccountHasBeenCreatedOption;
	
	public boolean getDisplayStatusOfyourAccountHasBeenCreatedOption() {
		boolean visible = yourAccountHasBeenCreatedOption.isDisplayed();
		return visible;
	}
	
}

package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(className="fa-search")
	private WebElement clickOnSearch;
	
	@FindBy(linkText="HP LP3065")
	private WebElement hpLaptop;
	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public void selectLoginOption() {
		loginOption.click();
	}
	
	public void selectRegisterOption() {
		registerOption.click();
	}
	
	public void enterProductIntoSearchBoxField(String productText) {
		searchBoxField.sendKeys(productText);
	}
	
	public void clickOnSearchButton() {
		clickOnSearch.click();
	}
	
	public Boolean hpLaptopResult() {
		return hpLaptop.isDisplayed();
	}
	
	
}

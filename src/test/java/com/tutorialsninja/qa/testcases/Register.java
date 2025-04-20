package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseClass.BaseClass;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends BaseClass {
	WebDriver driver;
	
	public Register() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("FirstName"));
		registerPage.enterLastName(dataProp.getProperty("LastName"));
		registerPage.enterEmailAddress(Utilities.generateRandomNumber()+"@gmail.com");
		registerPage.enterTelephoneNumber(dataProp.getProperty("TelephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPass"));
		registerPage.enterConfirmPassword(prop.getProperty("validPass"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
	    /*	
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("FirstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("LastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateRandomNumber()+"@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("TelephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPass"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.className("btn-primary")).click();
		*/
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		
		Boolean validate =  accountSuccessPage.getDisplayStatusOfyourAccountHasBeenCreatedOption();
		Assert.assertEquals(true, validate);
	
		
	}
	
@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllFields() throws InterruptedException {



		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("FirstName"));
		registerPage.enterLastName(dataProp.getProperty("LastName"));
		registerPage.enterEmailAddress(Utilities.generateRandomNumber()+"@gmail.com");
		registerPage.enterTelephoneNumber(dataProp.getProperty("TelephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPass"));
		registerPage.enterConfirmPassword(prop.getProperty("validPass"));
		registerPage.selectPrivacyPolicy();
	registerPage.clickOnContinueButton();
	AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
	
	Boolean validate =  accountSuccessPage.getDisplayStatusOfyourAccountHasBeenCreatedOption();
	Assert.assertEquals(true, validate);

	}
	

@Test(priority=3)

public void verifyRegisteringAnAccountWithExistingEmailAddress() throws InterruptedException {
	
	RegisterPage registerPage = new RegisterPage(driver);
	registerPage.enterFirstName(dataProp.getProperty("FirstName"));
	registerPage.enterLastName(dataProp.getProperty("LastName"));
	registerPage.enterEmailAddress(prop.getProperty("validEMail"));
	registerPage.enterTelephoneNumber(dataProp.getProperty("TelephoneNumber"));
	registerPage.enterPassword(prop.getProperty("validPass"));
	registerPage.enterConfirmPassword(prop.getProperty("validPass"));
	registerPage.selectPrivacyPolicy();
	registerPage.clickOnContinueButton();
	String error=registerPage.retrieveDuplicateEmailAddressWarning();
	Assert.assertEquals(error, "Warning: E-Mail Address is already registered!");
	
}

 	@Test(priority=4)
    public void verifyRegisteringAnAccountWithoutFillingAnyDetails() throws InterruptedException {
	
 		RegisterPage registerPage = new RegisterPage(driver);
 		registerPage.clickOnContinueButton();
	
	    //driver.findElement(By.xpath("//input[@value='Continue']")).click();
	String errorMsg = registerPage.retrievePrivacyPolicyWarning();
	Assert.assertEquals(errorMsg, "Warning: You must agree to the Privacy Policy!");
	
	String firstnameError = driver.findElement(By.xpath("//input[@name='firstname']/following-sibling::div")).getText();
	Assert.assertEquals(firstnameError, "First Name must be between 1 and 32 characters!");
	

	
}

	@Test(priority=5)
	public void verifyRegisteringAnAccountInvalidPhoneNumber() throws InterruptedException {

//comment13


		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("FirstName"));
		registerPage.enterLastName(dataProp.getProperty("LastName"));
		registerPage.enterEmailAddress(Utilities.generateRandomNumber()+"@gmail.com");
		registerPage.enterTelephoneNumber("abcde");
		registerPage.enterPassword(prop.getProperty("validPass"));
		registerPage.enterConfirmPassword(prop.getProperty("validPass"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);

		Boolean validate =  accountSuccessPage.getDisplayStatusOfyourAccountHasBeenCreatedOption();
		Assert.assertEquals(true, validate,"Recent Error");

	}



}

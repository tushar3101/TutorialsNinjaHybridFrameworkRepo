package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseClass.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends BaseClass{

	public Login() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	void setup() {
		driver = initializeBrowser(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.selectLoginOption();
		
	}
	
	@AfterMethod
	void tearDown() {
	driver.quit();
	}
	
	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickonLoginButton();
		
		//driver.findElement(By.name("email")).sendKeys(email);
		//driver.findElement(By.name("password")).sendKeys(password);	  
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		//WebElement textt = driver.findElement(By.linkText("Edit your account information"));
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption());
		
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData(){
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
		
	}
	
	@Test(priority=2)

	public void verifyLoginWithInValidCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateRandomNumber()+"gmail.com");
		
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickonLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.", "Error2");
		

 }
	
	@Test(priority=3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress("tuarpixel"+Utilities.generateRandomNumber()+"@gmail.com");
		loginPage.enterPassword(prop.getProperty("validPass"));
		loginPage.clickonLoginButton();
				
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.", "Error2");
		

 }
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInValidPassword() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validEMail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickonLoginButton();
		
		//driver.findElement(By.name("email")).sendKeys(prop.getProperty("validEMail"));
		//driver.findElement(By.name("password")).sendKeys(dataProp.getProperty("invalidPassword")); 	
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.", "Error2");
		

 }
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
	
		LoginPage loginPage = new LoginPage(driver);
		
		driver.findElement(By.name("email")).sendKeys("");
		driver.findElement(By.name("password")).sendKeys("");
		loginPage.clickonLoginButton();
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.", "Error2");
		

 }

	
	
	
	
}

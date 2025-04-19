package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.baseClass.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class Search extends BaseClass {

	WebDriver driver;
	
	public Search() {
		super();
	}
	
	@BeforeMethod
	void setup() {
		driver = initializeBrowser(prop.getProperty("browser"));
		SearchPage searchPage = new SearchPage(driver);
		searchPage.clickSearchElement();
	}
	
	@AfterMethod
	void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		homePage.clickOnSearchButton();
		Assert.assertTrue(homePage.hpLaptopResult());
	}
	
	////div[@id='content']/h2/following-sibling::p
	@Test(priority=2)
	void verifySearchWithInvalidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		homePage.clickOnSearchButton();
		
		SearchPage searchPage = new SearchPage(driver);
		//driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.className("fa-search")).click();
		String actmsg =  searchPage.retrieveNoProductMessageText();
		System.out.println(actmsg);
		Assert.assertEquals(actmsg, dataProp.getProperty("noProductTextInSearchResults"),"error");
	}
	
	@Test(priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct"})
	void verifySearchWithoutAnyPrduct() {
		HomePage homePage = new HomePage(driver);
		homePage.clickOnSearchButton();
		SearchPage searchPage = new SearchPage(driver);
		String actmsg = searchPage.retrieveNoProductMessageText();
		System.out.println(actmsg);
		Assert.assertEquals(actmsg, dataProp.getProperty("noProductTextInSearchResults"),"error");
	}
}

package com.orangeHRM.testCases;

import org.testng.annotations.Test;

import com.orangeHRM.config.BaseClass;
import com.orangeHRM.pageObjects.HomePage;
import com.orangeHRM.pageObjects.LoginPage;
import com.orangeHRM.utils.Util;

public class Test_LoginTest extends BaseClass{
	
	@Test
	public void validateLoginPage() {
		LoginPage log = new LoginPage(driver);
		log.isPageOpened();
		log.logInToPage(Util.readProperty("username"), Util.readProperty("password"));
		HomePage home = new HomePage(driver);
		home.isPageOpened();
		home.logoutOfThePage();
		log.isPageOpened();
	}
	
	@Test
	public void validateInvalidCredsLoginPage() {
		LoginPage log = new LoginPage(driver);
		log.isPageOpened();
		log.logInToPage("QaXperts", Util.readProperty("password"));
		HomePage home = new HomePage(driver);
		home.isPageOpened();
		home.logoutOfThePage();
		log.isPageOpened();
	}

}

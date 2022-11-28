package com.orangeHRM.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeHRM.reports.Report;
import com.orangeHRM.utils.Util;

public class LoginPage {
	String expectedTitle = "OrangeHRM";
	WebDriver driver;
	
	
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	private WebElement input_userName;
	
	@FindBy(name="password")
	private WebElement input_passWord;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement btn_Login;
	
	public void isPageOpened() {
		String actualTitle = driver.getTitle();
		if(expectedTitle.equalsIgnoreCase(actualTitle)) {
			Util.waitForElement(driver, input_userName);
			Report.passTest(driver, "Login Page is successfully opened!!!!");
		} else {
			Report.failTest(driver, "Login Page is not successfully opened!!!!");
		}
	}
	
	
	public void logInToPage(String username, String password) {
		Util.sendKeys(driver, input_userName, username, "User Name");
		Util.sendKeys(driver, input_passWord, password, "Password");
		Util.click(driver, btn_Login, "Log in");
	}
	
	

}

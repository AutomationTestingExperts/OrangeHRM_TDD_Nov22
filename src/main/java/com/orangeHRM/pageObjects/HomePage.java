package com.orangeHRM.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeHRM.reports.Report;
import com.orangeHRM.utils.Util;

public class HomePage {
	
	String expectedTitle = "Dashboard";
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h6")
	private WebElement lbl_header;
	
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	private WebElement lnk_logoutMenu;
	
	@FindBy(linkText="Logout")
	private WebElement lnk_Logout;
	
	public void isPageOpened() {
		try {
		String actualTitle = Util.waitForElement(driver, lbl_header).getText();
		if(expectedTitle.equalsIgnoreCase(actualTitle)) {
			Report.passTest(driver, "Home Page is successfully opened!!!!");
		} else {
			Report.failTest(driver, "Home Page is not successfully opened!!!!");
		}
		}catch (Exception e) {
			Report.failTest(driver, "Home Page is not successfully opened due to : "+e.getMessage());
		}
	}
	
	public void logoutOfThePage(){
		Util.click(driver, lnk_logoutMenu, "Logout Menu");
		Util.click(driver, lnk_Logout, "Logout");
	}

}

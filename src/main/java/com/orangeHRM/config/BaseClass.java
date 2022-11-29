package com.orangeHRM.config;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;
import com.orangeHRM.reports.Report;
import com.orangeHRM.utils.Util;

public class BaseClass {

	public WebDriver driver;
	public static ExtentTest report;
	String reportsPath = "", htmlPath = "";

	@BeforeSuite
	public void beforeSuite() {
		reportsPath = System.getProperty("user.dir") + "//Reports//" + Util.getFolderName();

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		String browser = Util.readProperty("browser");
		String url = Util.readProperty("url");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.driver.chromedriver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.driver.geckodriver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.driver.edgedriver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			System.setProperty("webdriver.driver.safaridriver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new SafariDriver();
		}
		htmlPath = reportsPath + "\\" + method.getName() + "_" + Util.getRandomNumWithCurrentDate() + ".html";
		report = Report.startReport(htmlPath, method.getName(), "");
		report.info("************* Execution started ******************");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		System.out.println(browser + " was launched, and '" + url + "' opened.");
	}

	@AfterMethod
	public void afterMethod() {
		if (driver != null) {
			driver.quit();
		}
		Report.getReporter(htmlPath).flush();
		report.info("**************** Execution completed ****************");
	}

	@AfterSuite
	public void afterSuite() {

	}

}

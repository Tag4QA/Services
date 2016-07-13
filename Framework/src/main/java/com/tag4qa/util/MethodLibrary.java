package com.tag4qa.util;

/**
 * Library methods of the automation framework
 * @author mperumal
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.tag4qa.pages.HomePage;
import com.tag4qa.pages.LoginPage;

public class MethodLibrary extends BrowserLaunch {

	private final Logger LOGGER = Logger.getLogger(MethodLibrary.class);
	private static final MethodLibrary METHOD_LIBRARY_INSTANCE = new MethodLibrary();
	private LoginPage loginpage_instance = LoginPage.getInstance();
	private HomePage homepage_instance = HomePage.getInstance();

	@Parameters("Browser")
	@BeforeTest
	public void browserInvoke(String browser) {
		LOGGER.info("Retreived the browser parameter from the TestNG.xml file.");
		configBrowser(browser);
	}

	@BeforeClass
	public void appLogin() {
		try {
			launchApplication(ConfigUtil.envConfig().getProperty("Base_URL"));
			// Verify login page title
			String loginPageTitle = loginpage_instance.getLoginTitle();
			Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
			// login to application
			loginpage_instance.loginToGuru99(ConfigUtil.envConfig().getProperty("Username"),
					ConfigUtil.envConfig().getProperty("Password"));
			// Verify home page
			Assert.assertTrue(homepage_instance.getHomePageDashboardUserName().toLowerCase()
					.contains("manger id : " + ConfigUtil.envConfig().getProperty("Username")));
		} catch (Exception e) {
			LOGGER.error("Error in Before Class: " + e.getMessage());
		}
	}

	@AfterClass
	public void appLogout() {
		try {
			homepage_instance.logoutApplication();
			String loginPageTitle = loginpage_instance.getLoginTitle();
			Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
		} catch (Exception e) {
			LOGGER.error("Error in After Class: " + e.getMessage());
		}
	}

	/**
	 * Enter value to TextBox/ TextArea
	 * 
	 */
	public void enterValue(By by, String value) {
		LOGGER.debug("Enter the text value:" + value);
		waitUntilElementPresence(by);
		getEventFiringWebDriver().findElement(by).clear();
		getEventFiringWebDriver().findElement(by).sendKeys(value);
	}

	/**
	 * Click on button
	 * 
	 */
	public void click(By by) {
		LOGGER.debug("Click on the field: " + by.toString());
		waitUntilElementPresence(by);
		getEventFiringWebDriver().findElement(by).click();
	}

	/**
	 * Get text of the field
	 * 
	 */
	public String getText(By by) {
		LOGGER.debug("Get text of the field: " + by.toString());
		waitUntilElementPresence(by);
		return getEventFiringWebDriver().findElement(by).getText();
	}

	/**
	 * Invoke the application base URL
	 * 
	 * @param applicationURL
	 */
	public void launchApplication(String applicationURL) {
		getEventFiringWebDriver().get(applicationURL);
	}

	/**
	 * Accept the JavaScript alert
	 * 
	 */
	public void acceptAlert() {
		Alert alert = getEventFiringWebDriver().switchTo().alert();
		alert.accept();
	}

	/**
	 * @return MethodLibrary instance
	 * 
	 */
	public static MethodLibrary getInstance() {
		return METHOD_LIBRARY_INSTANCE;
	}

	/**
	 * Verify element presense
	 * 
	 * @return boolean
	 */
	public boolean isElementDisplayed(By by) {
		waitUntilElementPresence(by);
		return getEventFiringWebDriver().findElement(by).isDisplayed();
	}

	/**
	 * Wait until the given element is present in the DOM
	 * 
	 */
	public void waitUntilElementPresence(By by) {
		getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
	}

}

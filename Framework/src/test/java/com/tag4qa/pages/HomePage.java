package com.tag4qa.pages;

/**
 * Page object properties of the application Home page 
 *  @author mperumal
 */

import org.openqa.selenium.By;

import com.tag4qa.util.MethodLibrary;

public class HomePage extends MethodLibrary {

	private static final HomePage HOME_PAGE_INSTANCE = new HomePage();

	public final By homePageUserName = By.xpath("//table//tr[@class='heading3']");
	public final By logout = By.linkText("Log out");

	// Get the User name from Home Page
	public String getHomePageDashboardUserName() {
		return getText(homePageUserName);
	}

	public void clickLogOut() {
		click(logout);
	}

	public void logoutApplication() {
		clickLogOut();
		acceptAlert();
	}

	/**
	 * 
	 * @return HomePage instance
	 */
	public static HomePage getInstance() {
		return HOME_PAGE_INSTANCE;
	}
}

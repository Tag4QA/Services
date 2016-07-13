package com.tag4qa.pages;

/**
 * Page object properties of the application Login page 
 *  @author mperumal
 */
import org.openqa.selenium.By;

import com.tag4qa.util.MethodLibrary;

public class LoginPage extends MethodLibrary {

	private static final LoginPage LOGIN_PAGE_INSTANCE = new LoginPage();
	public final By user99GuruName = By.name("uid");
	public final By password99Guru = By.name("password");
	public final By titleText = By.className("barone");
	public final By login = By.name("btnLogin");

	// Set user name in textbox
	public void setUserName(String strUserName) {
		enterValue(user99GuruName, strUserName);
	}

	// Set password in password textbox
	public void setPassword(String strPassword) {
		enterValue(password99Guru, strPassword);
	}

	// Click on login button
	public void clickLogin() {
		click(login);
	}

	// Get the title of Login Page
	public String getLoginTitle() {
		return getText(titleText);
	}

	/**
	 * This POM method will be exposed in test case to login in the application
	 * 
	 * @param strUserName
	 * @param strPasword
	 * @return
	 */
	public void loginToGuru99(String strUserName, String strPasword) {
		// Fill user name
		this.setUserName(strUserName);
		// Fill password
		this.setPassword(strPasword);
		// Click Login button
		this.clickLogin();

	}

	/**
	 * @return LoginPage instance
	 * 
	 */
	public static LoginPage getInstance() {
		return LOGIN_PAGE_INSTANCE;
	}
}

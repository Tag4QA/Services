package com.tag4qa.pages;

/**
 * Page object properties of the application Add Customer Success page 
 * @author mperumal
 */

import org.openqa.selenium.By;

import com.tag4qa.util.MethodLibrary;

public class AddCustomerSuccessPage extends MethodLibrary {

	private final static AddCustomerSuccessPage ADD_CUSTOMER_SUCCESS_PAGE = new AddCustomerSuccessPage();
	public final By customerSuccessMessage = By.xpath("//p[contains(text(),'Customer Registered Successfully!!!')]");
	public final By verifyCustName = By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td");
	public final By verifyCustGender = By.xpath("//td[contains(text(),'Gender')]/following-sibling::td");
	public final By verifyCustDOB = By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td");
	public final By verifyCustAddress = By.xpath("//td[contains(text(),'Address')]/following-sibling::td");
	public final By verifyCustCity = By.xpath("//td[contains(text(),'City')]/following-sibling::td");
	public final By verifyCustState = By.xpath("//td[contains(text(),'State')]/following-sibling::td");
	public final By verifyCustPin = By.xpath("//td[contains(text(),'Pin')]/following-sibling::td");
	public final By verifyCustMobileNo = By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td");
	public final By verifyCustEmail = By.xpath("//td[contains(text(),'Email')]/following-sibling::td");
	public final By customerCompletionContinue = By.linkText("Continue");

	// Retrieve and Returns the customer name to verify from the customer
	// completion page
	public String verifyCustName() {
		return getText(verifyCustName);
	}

	// Retrieve and Returns the customer gender to verify from the customer
	// completion page
	public String verifyCustGender() {
		return getText(verifyCustGender);
	}

	// Retrieve and Returns the customer DOB to verify from the customer
	// completion page
	public String verifyCustDOB() {
		return getText(verifyCustDOB);
	}

	// Retrieve and Returns the customer Address to verify from the customer
	// completion page
	public String verifyCustAddress() {
		return getText(verifyCustAddress);
	}

	// Retrieve and Returns the customer City to verify from the customer
	// completion page
	public String verifyCustCity() {
		return getText(verifyCustCity);
	}

	// Retrieve and Returns the customer State to verify from the customer
	// completion page
	public String verifyCustState() {
		return getText(verifyCustState);
	}

	// Retrieve and Returns the customer Pin to verify from the customer
	// completion page
	public String verifyCustPin() {
		return getText(verifyCustPin);
	}

	// Retrieve and Returns the customer Mobile No to verify from the customer
	// completion page
	public String verifyCustTelephoneNo() {
		return getText(verifyCustMobileNo);
	}

	// Retrieve and Returns the customer Email to verify from the customer
	// completion page
	public String verifyCustEmail() {
		return getText(verifyCustEmail);
	}

	// Navigate to home page by click on continue link
	public void navigateHomePage() {
		click(customerCompletionContinue);
	}

	/**
	 * 
	 * @return AddCustomerSuccessPage instance
	 */
	public static AddCustomerSuccessPage getInstance() {
		return ADD_CUSTOMER_SUCCESS_PAGE;
	}
}

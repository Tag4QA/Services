package com.tag4qa.pages;

/**
 * Page object properties of the application Add Customer page 
 *  @author mperumal
 */

import org.openqa.selenium.By;

import com.tag4qa.util.MethodLibrary;

public class AddCustomerPage extends MethodLibrary {

	public final By customerName = By.name("name");
	public final By gender = By.xpath("//input[@name='rad1'][@value='m']");
	public final By dob = By.name("dob");
	public final By address = By.xpath("//td[contains(text(),'Address')]/following-sibling::td/textarea");
	public final By city = By.name("city");
	public final By state = By.name("state");
	public final By pinNumber = By.name("pinno");
	public final By telephoneNumber = By.name("telephoneno");
	public final By emailId = By.name("emailid");
	public final By password = By.name("password");
	public final By submit = By.name("sub");
	public final By newCustomerMenu = By.linkText("New Customer");
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

	// Set customer name in text box
	public void setCustomerName(String custName) {
		enterValue(customerName, custName);
	}

	// Select gender in text box
	public void selectGender() {
		click(gender);
	}

	// Set DOB in text box
	public void setDOB(String dateOfBirth) {
		enterValue(dob, dateOfBirth);
	}

	// Set Address in text box
	public void setAddress(String custAddress) {
		enterValue(address, custAddress);
	}

	// Set City in text box
	public void setCity(String custCity) {
		enterValue(city, custCity);
	}

	// Set State in text box
	public void setState(String custState) {
		enterValue(state, custState);
	}

	// Set pin number in text box
	public void setPinNumber(String custPinNo) {
		enterValue(pinNumber, custPinNo);
	}

	// Set telephone number in text box
	public void setTelephoneNumber(String custPhoneNo) {
		enterValue(telephoneNumber, custPhoneNo);
	}

	// Set customer name in text box
	public void setEmail(String custEmail) {
		enterValue(emailId, custEmail);
	}

	// Set customer name in text box
	public void setPassword(String custPassword) {
		enterValue(password, custPassword);
	}

	// Submit the new customer form
	public void submitForm() {
		click(submit);
	}

	// Navigate to new customer form
	public void newCustomerForm() {
		click(newCustomerMenu);
	}

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

	/**
	 * To add a new bank customer
	 * 
	 */
	public void addBankCustomer(String custName, String dateOfBirth, String custAddress, String custCity,
			String custState, String custPinNo, String custPhoneNo, String custEmail, String custPassword) {
		setCustomerName(custName);
		selectGender();
		setAddress(custAddress);
		setDOB(dateOfBirth);
		setCity(custCity);
		setState(custState);
		setPinNumber(custPinNo);
		setTelephoneNumber(custPhoneNo);
		setEmail(custEmail);
		setPassword(custPassword);
		submitForm();
	}

}

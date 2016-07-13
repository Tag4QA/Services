package com.tag4qa.tests;

/**
 * Add customers to the bank
 *  @author mperumal
 */

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tag4qa.pages.AddCustomerPage;
import com.tag4qa.pages.AddCustomerSuccessPage;

public class TestAddCustomer extends AddCustomerPage {

	private final AddCustomerSuccessPage customer_success_page = AddCustomerSuccessPage.getInstance();

	@Test(priority = 0, description = "Add a new customer", dataProvider = "NewCustomer")
	public void addCustomer(String name, String dob, String address, String city, String state, String pin,
			String mobile, String email, String password) {
		newCustomerForm();
		addBankCustomer(name, dob, address, city, state, pin, mobile, email, password);
		// Verify the added customer details
		Assert.assertTrue(customer_success_page.isElementDisplayed(customer_success_page.customerSuccessMessage),
				"Customer is failed to add.");
		Assert.assertEquals(customer_success_page.verifyCustName(), name, "Customer name is mismatched.");
		Assert.assertEquals(customer_success_page.verifyCustCity(), city, "Customer city is mismatched.");
		Assert.assertEquals(customer_success_page.verifyCustAddress(), address, "Customer address is mismatched.");
		Assert.assertEquals(customer_success_page.verifyCustState(), state, "Customer state is mismatched.");
		Assert.assertEquals(customer_success_page.verifyCustPin(), pin, "Customer pin is mismatched.");
		Assert.assertEquals(customer_success_page.verifyCustTelephoneNo(), mobile,
				"Customer mobile number is mismatched.");
		Assert.assertEquals(customer_success_page.verifyCustEmail(), email, "Customer email is mismatched.");
		// Navigate back to homepage
		customer_success_page.navigateHomePage();
	}

	@DataProvider(name = "NewCustomer")
	public static Object[][] custDetails() {
		return new Object[][] {
				{ "ManiPerumal", "08/07/1991", "Chennais", "Chennais", "TNP", "600002", "9854685241", "manip@test.com",
						"123#%" },
				{ "Manisrini", "08/07/1989", "MKMS", "ChennaiF", "STN", "600301", "9984856924", "manis@test.com",
						"123#%" } };

	}
}

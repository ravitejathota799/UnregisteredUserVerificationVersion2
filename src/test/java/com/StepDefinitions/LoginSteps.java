package com.StepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.Factory.BaseClass;
import com.change2naturalfoods.userverification.UserVerification;
import com.pageObjects.Locators;
import com.utils.DataReader;
import com.utils.ExcelUtility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps{
	WebDriver driver;
	Locators loc = new Locators(BaseClass.getDriver());
	UserVerification uv;
	String file = System.getProperty("user.dir") + "/src/test/resources/MiniProject.xlsx"; // Getting the file path from
	List<HashMap<String, String>> datamap; //Data driven																						// the current directory
	
	
	
	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		BaseClass.getLogger().info("Goto my account-->Click on Login.. ");
		loc.setNavigationLink();
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String username, String password) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		BaseClass.getLogger().info("Entering user name and password");
		BaseClass.getLogger().debug("application logs........."); // logging
		BaseClass.getLogger().info("*** Starting test ****");
		
		//new code for Test driven development
		int idx = (ExcelUtility.getRowCount(file, "TestCase"))-1;
		datamap=DataReader.data(file, "TestCase");
		String email = datamap.get(idx).get("UserName");
		String passwd = datamap.get(idx).get("Password");
		String er = datamap.get(idx).get("ExceptedResult");
		loc.setEmail(email);
		loc.setPassword(passwd);
				
		/*Previous code for Test driven development
		int rows = ExcelUtility.getRowCount(file, "TestCase"); // getting the row count from test case sheet
		for (int i = 1; i <= rows; i++) {
			loc.setNavigationLink(); // setting up the navigation link
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles()); // handling multiple windows
			driver.switchTo().window(tabs.get(1)); // switching the tab
			loc.setEmail(ExcelUtility.getCellData(file, "TestCase", i, 0)); // getting the email field value from the
																			// excel file
			BaseClass.getLogger().info("*** Entered Email ID ***");
			loc.setPassword(ExcelUtility.getCellData(file, "TestCase", i, 1)); // getting the password field value from
																				// the excel file
			BaseClass.getLogger().info("*** Entered password ***");
			String s = loc.getError(); // actual result value
			String expectedResult = "Error: Email or password is incorrect!"; // expected result value
			try {
				Assert.assertEquals(s, expectedResult); // Checking the actual and expected results
				ExcelUtility.setCellData(file, "TestCase", i, 3, "Failed"); // making the cell as failed
				ExcelUtility.fillGreenColor(file, "TestCase", i, 2); // making the cell background color as green
				ExcelUtility.fillGreenColor(file, "TestCase", i, 3); // making the cell background color as green
				System.out.println("executed successfully and file Written Succefully"); // printing the output in the
																							// console
			} catch (AssertionError e) {
				System.out.println("Assertion Error" + e); // catching the exception error message
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

		}*/

	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		// Write code here that turns the phrase above into concrete actions
		loc.clickLogin();
	}

	@Then("user capture the error")
	public void user_capture_the_error() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		String s = loc.getError();	 //actual result value
		String expectedResult = "Error: Email or password is incorrect!";	 //expected result value
		int idx = (ExcelUtility.getRowCount(file, "TestCase"))-1;
		try {
			Assert.assertEquals(s, expectedResult); 	// Checking the actual and expected results
			ExcelUtility.setCellData(file, "TestCase", idx, 3, "Failed"); 	// making the cell as failed
			ExcelUtility.fillGreenColor(file, "TestCase", idx, 2);	 // making the cell background color as green
			ExcelUtility.fillGreenColor(file, "TestCase", idx, 3);	 // making the cell background color as green
			System.out.println("executed successfully and file Written Succefully"); 	// printing the output in the  console
		} catch (AssertionError e) {
			System.out.println("Assertion Error" + e);	 // catching the exception error message
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}

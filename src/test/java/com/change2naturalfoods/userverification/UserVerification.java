
package com.change2naturalfoods.userverification;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pageObjects.Locators;
import com.utils.DriverSetup;
import com.utils.ExcelUtility;

@Listeners(com.utils.ExtentReportManager.class)
public class UserVerification extends DriverSetup {

	String file = System.getProperty("user.dir") + "/src/test/resources/MiniProject.xlsx"; // Getting the file path from the current directory
	
	@Test
	void verifyError() throws IOException, InterruptedException {
		logger.debug("application logs.........");		//logging 
		logger.info("*** Starting test ****");
		Locators l = new Locators(driver);
		int rows = ExcelUtility.getRowCount(file, "TestCase"); // getting the row count from test case sheet
		for (int i = 1; i <= rows; i++) {
			l.setNavigationLink();	 //setting up the navigation link
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());	 // handling multiple windows
			driver.switchTo().window(tabs.get(1));	 // switching the tab
			l.setEmail(ExcelUtility.getCellData(file, "TestCase", i, 0)); 	//getting the email field value from the excel file
			logger.info("*** Entered Email ID ***");
			l.setPassword(ExcelUtility.getCellData(file, "TestCase", i, 1)); 	//getting the password field value from the excel file
			logger.info("*** Entered password ***");
			l.clickLogin();	 //clicking on the login button
			logger.info("*** Clicked on login button ***");
			//new driverSetup().captureScreen(driver,"Error");
			String s = l.getError();	 //actual result value
			String expectedResult = "Error: Email or password is incorrect!";	 //expected result value
			try {
				Assert.assertEquals(s, expectedResult); 	// Checking the actual and expected results
				ExcelUtility.setCellData(file, "TestCase", i, 3, "Failed"); 	// making the cell as failed
				ExcelUtility.fillGreenColor(file, "TestCase", i, 2);	 // making the cell background color as green
				ExcelUtility.fillGreenColor(file, "TestCase", i, 3);	 // making the cell background color as green
				System.out.println("executed successfully and file Written Succefully"); 	// printing the output in the  console
			} catch (AssertionError e) {
				System.out.println("Assertion Error" + e);	 // catching the exception error message
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			

		}

	}

}

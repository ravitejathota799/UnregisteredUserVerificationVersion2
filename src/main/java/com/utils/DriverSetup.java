package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverSetup {

	public static WebDriver driver;
	public Logger logger;

	@BeforeClass
	@Parameters({ "browser", "url" })
	public void getWebDriver(String br, String url) {
		logger = LogManager.getLogger(this.getClass()); 	//getting for the class 
		try {
			switch (br.toLowerCase()) {	 	 //converting the br value to lower case and using switch to select browser
			case "chrome":
				driver = new ChromeDriver(); 	//creating an instance for the chrome driver
				break;
			case "edge":
				driver = new EdgeDriver(); 	 	//creating an instance for the chrome driver
				break;
			default:
				throw new IllegalArgumentException("Invalid browser parameter: " + br);	 	//throw exception to give browser name properly
			}
			driver.manage().window().maximize(); // maximizing the window
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // waiting the page for seconds
			driver.get(url); // navigating to the web site

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		

	}
	@AfterClass
	public static void closeBrowser() {
		// TODO Auto-generated method stub
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());		//getting time stamp to create a new image whenever it is run

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;	 		 //creating an instance of taking  screenshot and casting to driver type
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);	 	 //capturing the screenshot using getScreenShot method
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + tname + "_" + timeStamp + ".png";	  	// storing it in the given folder in the current directory
		System.out.println(destination);	 	//printing the path 

		try {
			FileUtils.copyFile(source, new File(destination)); 		//copying the file to the given path
		} catch (Exception e) {
			e.getMessage();	 	//printing exception message
		}
		
		return destination; 		//returning the path 

	}
}

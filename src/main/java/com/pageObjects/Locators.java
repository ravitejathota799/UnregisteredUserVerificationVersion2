package com.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Locators extends BasePage{

	

	public Locators(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//a[@class='nav-link -before']")
	WebElement linkLocator;
	
	@FindBy(xpath="//input[@id='email' and @type='email']")
	WebElement emailId;

	@FindBy(xpath="//input[@id='password' and @type='password' and @class='form-control field-validate']")
	WebElement password;

	@FindBy(xpath="//button[normalize-space()='Login'][@type='submit']")
	WebElement loginButton;

	@FindBy(xpath="//div[contains(@class,'fade show')]")
	WebElement error;

	public void setNavigationLink() {
		String clickLink=Keys.chord(Keys.CONTROL, Keys.ENTER);
		linkLocator.sendKeys(clickLink);
	}

	public void setEmail(String email) {
		emailId.sendKeys(email);
	}
	
	public void setPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	
	public String getError() {
		try {
			String s = error.getText();
			s=s.substring(0,s.length()-2);
			System.out.println(s);
			return(s);
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	}
	

}

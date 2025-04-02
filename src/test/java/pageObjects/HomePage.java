package pageObjects;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
//1. constructor
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//2. Locator
	
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement lnkmyAccount;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")
	WebElement lnkLogin;
	
	
	//3.Action methods
	
	public  void clickMyAccount()
	{
		lnkmyAccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}

}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	//constructor
	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	//Locator
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgheading;
	
	@FindBy(xpath = "//a[text()='Logout'][@class=\"list-group-item\"]")
	WebElement lnkLogout;
	
	//method
	
	public boolean isMyAccountPageExists()
	{
		try
		{
		return(msgheading.isDisplayed());
		}
		
	
		catch (Exception e) 
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}
}

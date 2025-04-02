package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{

	@Test(groups = {"Sanity","Master"})
	public void verify_Login()
	{
		logger.info(".......TC_002_LoginTest Test started..........");
		
		try
		{
		//Home page
		HomePage hobj=new HomePage(driver);
		hobj.clickMyAccount();
		hobj.clickLogin();
		
		//login page
		LoginPage lobj=new LoginPage(driver);
		lobj.setEmail(p.getProperty("email"));
		lobj.setPassword(p.getProperty("password"));
		lobj.clickLogin();
		
		//MyAccount
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("........TC_002_LoginTest Test finished");
		
	}
}

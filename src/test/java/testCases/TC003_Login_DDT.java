package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_Login_DDT extends BaseClass
{

	/*Conditions to be checked
	 * Data is valid - login success - test pass - logout
	 *              - login fail - test fail 
	 *Data is invalid - login success - test fail - logout
	 *                - Login fail - test pass             
	 */
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void verify_LoginDDT(String email, String password, String exp)
	{
		logger.info(".......TC003_Login_DDT Test started......");
		try
		{
		//login
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		//My Account
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExists();
		

		/*Conditions to be checked
		 * Data is valid - login success - test pass - logout
		 *              - login fail - test fail 
		 *Data is invalid - login success - test fail - logout
		 *                - Login fail - test pass             
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch (Exception e) 
		{
			Assert.fail();
		}
		
		logger.info(".........TC003_Login_DDT finished testing........");
	
	}
	
}

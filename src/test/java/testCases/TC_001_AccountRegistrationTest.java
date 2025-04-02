package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	
	
	@Test(groups = {"Regression","Master"})
	public void verify_AccountRegistration()
	{
		logger.info("......Starting TC_001_AccountRegistrationTest.......... ");
		try 
		{
		HomePage hobj=new HomePage(driver);
		hobj.clickMyAccount();
		logger.info("......Clicked MyAccount Link........");
		
		hobj.clickRegister();
		logger.info("......Clicked Register Link........");
		
		//getting random inputs from user-defined methods which are created below
		AccountRegistrationPage regobj=new AccountRegistrationPage(driver);
		
		logger.info(".....Providing user details.......");
		regobj.setFirstName(randomString());
		regobj.setLastName(randomString());
		regobj.setEmail(randomString()+"@gmail.com");
		regobj.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		
		regobj.setPassowrd(password);
		regobj.setConfirmPassword(password);
		regobj.setPrivacyPolicy();
		regobj.clickContinue();
		
		//validation of message
		logger.info("....Validating expected message.....");
		String confMsg = regobj.getConfirmationMsg();
		Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error(".....Test failed.....");
			logger.debug(".....Debug logs......");
			Assert.fail();
		}
	}
	
	
}

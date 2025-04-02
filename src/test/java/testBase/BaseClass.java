package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
public static WebDriver driver;
public Logger  logger;
public Properties p;

	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void  setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		FileInputStream file=new FileInputStream(".\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		{
			switch (br.toLowerCase()) {
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name...");
			return;
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//driver.get("https://tutorialsninja.com/demo/");
		//getting url from properties file
		driver.get(p.getProperty("appURL"));
		
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	

	
	//user defined method to create random test data as alphabets
	
	public String randomString()
	{
		String generatedSTring = RandomStringUtils.randomAlphabetic(5);
		return generatedSTring;
	}
	
	//user defined method to create random test data as numbers
	
	public String randomNumber()
	{
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}
	
	//user defined method to create random test data as combination of num and alphabets
	public String randomAlphaNumeric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNum = RandomStringUtils.randomNumeric(5);
		String genereatedAlphaNum = generatedString+generatedNum;
		return genereatedAlphaNum;
	}
	
	public String captureScreenshot(String tname)
	{
		if (driver == null) {
	        System.out.println("❌ Screenshot skipped: WebDriver is null.");
	        return "No Screenshot - WebDriver is null.";
	    }
		try {
		String timestamp=LocalDateTime.now().toString().replace(':', '-');
		TakesScreenshot ts= (TakesScreenshot)driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		}
		catch (Exception e) {
			System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
	        return "No Screenshot - Error Occurred.";
		}
	}
}

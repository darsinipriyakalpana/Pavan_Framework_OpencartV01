package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviders 
{
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\Login_testData.xlsx";
		
		
		
		ExcelUtils excobj=new ExcelUtils(path);
		int rows = excobj.getRowCount("Sheet1");
		int cells = excobj.getCellCount("Sheet1", rows);
		String[][] logindata=new String[rows][cells] ;
		for(int r=1; r<=rows;r++)
		{
			for(int c=0; c<cells; c++)
			{
				logindata[r-1][c]=excobj.getCellData("Sheet1", r, c);
			}
		}
	
		return logindata; //returning 2D array
		
	}
}

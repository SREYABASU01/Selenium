package testpackage_DataDrivenTestingFramework;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass{
	
	@Test(dataProvider = "testdata")
	public void logintest(String username, String password, String expurl)
	{
		w.get("https://www.saucedemo.com/");
		l.performLogin(username, password, expurl);
	}
	
	
	@DataProvider(name= "testdata")
	public Object[][] readdata() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/login.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		int rowcount = sh.getLastRowNum();
		int colcount= sh.getRow(0).getLastCellNum();
		
		Object obj[][] = new Object[rowcount][colcount];
		for(int i=0; i<rowcount; i++)
		{
			XSSFRow row = sh.getRow(i+1);
			for(int j=0; j<colcount; j++)
			{
				obj[i][j]= row.getCell(j).getStringCellValue();
			}
		}
		return obj;
	}

}

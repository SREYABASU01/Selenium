package testpackage_DataDrivenTestingFramework;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Greenkart extends BaseClass{
	
	@Test
	public void endtoendflow() throws Exception 
	{
		w.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
//		FileInputStream fis = new FileInputStream("./data/login.xlsx");
//		XSSFWorkbook wb = new XSSFWorkbook(fis);
//		XSSFSheet sh = wb.getSheetAt(1);
//		int rowcount = sh.getLastRowNum();
//		for(int i=0; i<rowcount; i++)
//		{
//			XSSFRow row = sh.getRow(i+1);
//			String veg= row.getCell(0).getStringCellValue();
//			w.findElement(By.xpath("//input[@type='search']")).sendKeys(veg);
//			System.out.println(veg);
//			w.findElement(By.xpath("//button[contains(text(), 'ADD TO CART')]")).click();
//			w.findElement(By.xpath("//input[@type='search']")).clear();
//		}
		
		d.addtokart();
		c.checkoutoperation();

	}
	

}

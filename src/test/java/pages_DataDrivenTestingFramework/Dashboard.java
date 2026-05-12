package pages_DataDrivenTestingFramework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	
	WebDriver w;
	@FindBy(xpath = "//input[@type='search']")WebElement search;
	@FindBy(xpath = "//button[contains(text(), 'ADD TO CART')]")WebElement add;
	
	public Dashboard(WebDriver w2)
	{
		this.w = w2;
		PageFactory.initElements(w, this);
	}
	
	public void addtokart() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/login.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(1);
		int rowcount = sh.getLastRowNum();
		for(int i=0; i<rowcount; i++)
		{
			XSSFRow row = sh.getRow(i+1);
			String veg = row.getCell(0).getStringCellValue();
			//w.findElement(By.xpath("//input[@type='search']")).sendKeys(veg);
			//System.out.println(veg);
			//w.findElement(By.xpath("//button[contains(text(), 'ADD TO CART')]")).click();
			//w.findElement(By.xpath("//input[@type='search']")).clear();
			
			search.sendKeys(veg);
			System.out.println(veg);
			add.click();
			search.clear();
		}
	}

}

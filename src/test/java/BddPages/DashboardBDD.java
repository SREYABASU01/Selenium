package BddPages;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardBDD {
	
	WebDriver w2;
	@FindBy(xpath= "//input[@type='search']")WebElement search;
	@FindBy(xpath= "//button[contains(text(), 'ADD TO CART')]")WebElement addtocart;

	public DashboardBDD(WebDriver w) {
		this.w2=w;
		PageFactory.initElements(w, this);
	}

	public void dashboard() throws Exception {
		XSSFWorkbook wb =  new XSSFWorkbook("/data/login.xlsx");
	    XSSFSheet sh = wb.getSheetAt(1);
	    int rowcount = sh.getLastRowNum();
	    for(int i=0; i<rowcount; i++)
	    {
	    XSSFRow row = sh.getRow(i+1);
	    String veg = row.getCell(0).getStringCellValue();
		search.sendKeys(veg);
		System.out.println(veg);
		WebDriverWait wait = new WebDriverWait(w2, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(addtocart));
		addtocart.click();
		search.clear();
	    }
	}

}

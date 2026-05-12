package TestngDemo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestngAnnotaions {

	WebDriver driver;
	
	@BeforeMethod(alwaysRun= true)
	public void launch()
	{
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--incognito");
		driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Parameters("url")
	@Test(priority=1, groups= {"sanity","smoke"}, retryAnalyzer= Retry.class)
	public void navigateTo(String url1) //url1 is a placeholder where the value of the parameter url is stored 
	{
//		driver.navigate().to("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		//String acturl= "https://rahulshettyacademy.com/"; //This line is for checking Assertion and retryAnalyzer
		String acturl= "https://rahulshettyacademy.com/seleniumPractise/#/";
		String expurl= driver.getCurrentUrl();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(acturl, expurl);
		driver.navigate().to(url1);
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		System.out.println("Navigation completed successfully.");
		sa.assertAll();
	}
	
	@Test(priority=2, groups= {"smoke"})
	public void alertMessage()
	{
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.cssSelector("input#name")).sendKeys("sreya");
		driver.findElement(By.cssSelector("input#confirmbtn")).click();
		
		Alert a = driver.switchTo().alert();
		String alertText = a.getText();
		System.out.println("Alert Message: "+alertText);
		a.accept();
		System.out.println("Alert action completed successfully.");
	}
	
	@AfterMethod
	public void close()
	{
		driver.quit();
	}
}

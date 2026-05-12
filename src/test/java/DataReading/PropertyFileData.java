package DataReading;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PropertyFileData {
	WebDriver driver;
	Properties p;
	
	@BeforeMethod
	public void launch() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/config.properties");
		p = new Properties();
		p.load(fis);
		String browsername = p.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.addArguments("--incognito");
			driver = new ChromeDriver(op);
		}
		else
		{
			EdgeOptions ep = new EdgeOptions();
			ep.addArguments("--inprivate");
			driver = new EdgeDriver(ep);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@Test
	public void login()
	{
		driver.get(p.getProperty("url"));
		String user= p.getProperty("username");
		String password= p.getProperty("password");
		driver.findElement(By.cssSelector("input#user-name")).sendKeys(user);
		driver.findElement(By.cssSelector("input#password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#login-button")).click();
		String expurl= "https://www.saucedemo.com/inventory.html";
		String acturl= driver.getCurrentUrl();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(acturl, expurl);
	}
	
	@AfterMethod
	public void close()
	{
		driver.quit();
	}
}

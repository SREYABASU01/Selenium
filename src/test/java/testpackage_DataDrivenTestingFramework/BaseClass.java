package testpackage_DataDrivenTestingFramework;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import pages_DataDrivenTestingFramework.Checkout;
import pages_DataDrivenTestingFramework.Dashboard;
import pages_DataDrivenTestingFramework.Login;

public class BaseClass {
	
	public WebDriver w;
	Dashboard d;
	Checkout c;
	Login l;
	
	@BeforeTest
	public void launch() throws Exception
	{
		FileInputStream fis = new FileInputStream("./data/config.properties");
		Properties p = new Properties();
		p.load(fis);
		String browsername = p.getProperty("browser");
		
		if(browsername.equalsIgnoreCase("chrome"))
		{
			ChromeOptions op = new ChromeOptions();
			op.setBinary("C:\\Users\\Sreya Basu\\Downloads\\chrome-win64\\chrome.exe");
			op.addArguments("--incognito");
			w = new ChromeDriver(op);
		}
		
		else
		{
			EdgeOptions eo = new EdgeOptions();
			eo.addArguments("--inprivate");
			w = new EdgeDriver(eo);
		}
		
		w.manage().window().maximize();
		w.manage().deleteAllCookies();
		w.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		d = new Dashboard(w);
		c= new Checkout(w);
		l= new Login(w);
	}
	
	//Adding changes for git by person A
	//Adding changes for git by person B
	@AfterTest
	public void end()
	{
		w.quit();
	}
	
}

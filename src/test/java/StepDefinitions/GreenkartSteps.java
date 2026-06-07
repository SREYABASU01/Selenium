package StepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import BddPages.CheckoutBDD;
import BddPages.DashboardBDD;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testpackage_DataDrivenTestingFramework.BaseClass;

public class GreenkartSteps{
	WebDriver w;
	DashboardBDD d;
	CheckoutBDD ch;
	
	@Given("The user is on the Greenkart site")
	public void the_user_is_on_the_greenkart_site() throws Exception
	{
	//w.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	FileInputStream fis = new FileInputStream("./data/greenkartconfig.properties");
	Properties p = new Properties();
	p.load(fis);
	String browser = p.getProperty("browser");
	
	if(browser.equalsIgnoreCase("chrome"))
	{
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--incognito");
		w= new ChromeDriver(op);
	}
	else
	{
	EdgeOptions ed = new EdgeOptions();
	ed.addArguments("--inprivate");
	w = new EdgeDriver(ed);
	}
	
	w.manage().window().maximize();
	w.manage().deleteAllCookies();
	w.get(p.getProperty("url"));
	}
	
	@When("User adds vegetables in the cart")
	public void user_adds_vegetables_in_the_cart() throws Exception {
	    d = new DashboardBDD(w);
	    ch = new CheckoutBDD(w);
	    d.dashboard();
	}
	@When("Clicks on the checkout button")
	public void clicks_on_the_checkout_button() {
		ch.checkouts();
	}
	@When("checkout is completed")
	public void checkout_is_completed() {
	    ch.checkoutcomplete();
	}
	@Then("User gets a successful message")
	public void user_gets_a_successful_message() {
	   ch.successmessage();
	}
}

package com.sreya.selenium.SITSeleniumPrograms;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;


public class FirstSelenium {
	static WebDriver driver;

	public void openBrowser() 
	{
        String browser;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your choice: ");
        browser = sc.next();
        
        if(browser.equalsIgnoreCase("Chromium"))
        {
        	ChromeOptions op = new ChromeOptions();
        	op.setBinary("C:\\Users\\Sreya Basu\\Downloads\\chrome-win64\\chrome.exe");
        	op.addArguments("--incognito");
        	driver = new ChromeDriver(op);
        }
        else
        {
        	EdgeOptions op = new EdgeOptions();
        	op.addArguments("--inprivate");
        	driver = new EdgeDriver(op);
        }
        
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        //driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        
	}
	
	public void navigateTo()
	{
		driver.navigate().to("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		System.out.println("Navigation completed successfully.");
	}
	
	public void waitFor()
	{
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		driver.findElement(By.id("btn1")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement text= driver.findElement(By.xpath("//h3[text()=\"Show the textboxes here:\"]//input[@id=\"txt1\"]"));
		text.click();
		text.sendKeys("sreya");
		System.out.println("Performed wait and sendKeys Operation.");
	}
	
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
	
	public void dropdown() throws Exception
	{
		driver.get("https://www.amazon.in/");
		Thread.sleep(5000);
		WebElement dp= driver.findElement(By.cssSelector("#searchDropdownBox"));
		dp.click();
		
		Select s = new Select(dp);
		
		s.selectByIndex(0);
		String index= s.getFirstSelectedOption().getText();
		System.out.println("Value of DropDown by Index: "+index);
		
		s.selectByValue("search-alias=amazon-devices");
		String value = s.getFirstSelectedOption().getText();
		System.out.println("Value of DropDown by Value: "+value);
//		Thread.sleep(5000);
//		s.selectByVisibleText("Amazon Fashion");
//		String visibleText = s.getFirstSelectedOption().getText();
//		System.out.println("Value of DropDown by VisibleText: "+visibleText);
//		
		System.out.println("DropDown action completed successfully.");
	}
	
	public void mousehover() throws Exception
	{
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		Actions ac = new Actions(driver);
		WebElement mh = driver.findElement(By.xpath("//button[@id='mousehover']"));
		ac.moveToElement(mh).build().perform();
		
		WebElement top = driver.findElement(By.xpath("//a[@href='#top']"));
		ac.moveToElement(top).click().build().perform();
		System.out.println(driver.getCurrentUrl());
		
		ac.moveToElement(mh).build().perform();
		WebElement reload= driver.findElement(By.xpath("//a[contains(text(), 'Reload')]"));
		ac.moveToElement(reload).click().build().perform();
		System.out.println(driver.getCurrentUrl());
	}
	
	public void dragdrop() throws Exception
	{
		driver.get("https://demoqa.com/droppable");
		Actions ac = new Actions(driver);
		WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop = driver.findElement(By.xpath("//div/p[contains(text(), 'Drop Here')]"));
		System.out.println(drop.getText());
		Thread.sleep(2000);
		ac.dragAndDrop(drag, drop).build().perform();
		System.out.println(drop.getText());
	}
	
	public void fileupload()
	{
		driver.get("https://demoqa.com/automation-practice-form");
		driver.findElement(By.xpath("//input[@id='uploadPicture']")).sendKeys("C:\\Users\\Sreya Basu\\Documents");
	}
	
	public void fileuploadrobot() throws Exception
	{
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(2000);
		WebElement username= driver.findElement(By.xpath("//div/input[@name='username']"));
		WebElement password= driver.findElement(By.xpath("//input[@name='password']"));
		username.sendKeys("Admin");
		password.sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		//Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(@href, 'recruitment')]")))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'recruitment')]"))).click();
		//driver.findElement(By.cssSelector(".oxd-main-menu-item active")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=' Add ']"))).click();	
		driver.findElement(By.xpath("//button[text()=' Add ']")).click();
		WebElement fileupload= driver.findElement(By.xpath("//div[text()='No file selected']"));
		System.out.println(fileupload.getText());
		fileupload.click();
		//File path ctrl+C
		StringSelection filePath = new StringSelection("C:\\Users\\Sreya Basu\\Documents\\DemoFile.txt");
		//Copy file to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
		
		//Create Robot object
		Robot robot = new Robot();
		robot.delay(2000);
		
		//Press ctrl+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Release both V and Ctrl
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		
		//Press ENTER
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		System.out.println(driver.findElement(By.cssSelector(".oxd-file-input-div")).getText());
		
	}

	public void list()
	{
		driver.get("https://in.indeed.com/");
		driver.findElement(By.xpath("//input[contains(@placeholder, 'City')]")).sendKeys("in");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-u74ql7 eu4oa1w0']/div/a/span")));
		List<WebElement> countrylist= driver.findElements(By.xpath("//div[@class='css-u74ql7 eu4oa1w0']/div/a/span"));
		for(WebElement temp : countrylist)
		{
			String countryName= temp.getText();
			System.out.println(countryName);
			if(countryName.equalsIgnoreCase("indore, madhya pradesh"))
			{
				System.out.println(countryName);
				temp.click();
				break;
			}
		}
	}
	
	public void listPracticePage()
	{
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.cssSelector("input#autocomplete")).sendKeys("in");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ui-menu-item']/div[contains(text(),'in')]")));
		List<WebElement> countryNameList= driver.findElements(By.xpath("//li[@class='ui-menu-item']/div"));
		//List<WebElement> countryNameList= driver.findElements(By.xpath("//li[@class='ui-menu-item']/div[contains(text(),'in')]"));
		for(WebElement temp : countryNameList)
		{
			String countryName= temp.getText();
			//System.out.println(countryName);
			if(countryName.equalsIgnoreCase("India"))
			{
				System.out.println(countryName);
				temp.click();
				break;
			}
			
		}
	}

	public void displayAllLaptopNamesAmazon()
	{
		driver.get("https://www.amazon.in/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
		driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("iphone 17");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		List<WebElement> phoneList = driver.findElements(By.xpath("//div[@class='puisg-col-inner']//div[contains(@class, 'a-spacing-top-small')]"));
		for(WebElement temp: phoneList)
		{
			//String phoneTitle= temp.getText();
			String phoneTitles= temp.findElement(By.xpath("div[1]")).getText();
			System.out.println(phoneTitles);
		}
	}
	
	public void table()
	{
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		List<WebElement> courseList= driver.findElements(By.xpath("//div//table[@name='courses']//tr//td[2]"));
		for(WebElement temp: courseList)
		{
			String courseName= temp.getText();
			String price= temp.findElement(By.xpath("following-sibling::td[1]")).getText();
			if(courseName.contains("JMETER"))
			{
				System.out.println("Course Name: "+courseName);
				System.out.println("Price: "+price);
			}
		}
	}
	public static void main(String[] args) throws Exception 
	{
		FirstSelenium obj = new FirstSelenium();
		obj.openBrowser();
		//obj.navigateTo();
		//obj.waitFor();
		//obj.alertMessage();
		//obj.dropdown();
		//obj.mousehover();
		//obj.dragdrop();
		//obj.fileupload();
		//obj.fileuploadrobot();
		//obj.list();
		//obj.listPracticePage();
		//obj.displayAllLaptopNamesAmazon();
		obj.table();
	}
	
}

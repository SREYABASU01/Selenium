package pages_DataDrivenTestingFramework;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login {
	WebDriver w;
	
	@FindBy(xpath= "//input[@placeholder='Username']")WebElement user;
	@FindBy(css= "#password")WebElement pass;
	@FindBy(css= "#login-button")WebElement login;
	
	public Login(WebDriver w2) {
		this.w = w2;
		PageFactory.initElements(w, this);
	}

	public void performLogin(String username, String password, String expurl)
	{
		user.sendKeys(username);
		pass.sendKeys(password);
		login.click();
//		WebDriverWait wait = new WebDriverWait(w, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.urlContains(expurl));
		
//		try {
//			Robot robot = new Robot();
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Pop up did not occur");
//		}
		
		String acturl= w.getCurrentUrl();
		assertEquals(acturl, expurl);
		System.out.println(username+" "+password+" "+expurl);
	}

}

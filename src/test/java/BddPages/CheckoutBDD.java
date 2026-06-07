package BddPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class CheckoutBDD {
	
	WebDriver w2;
	
	@FindBy(xpath= "//a[@class='cart-icon']")WebElement checkoutbag;
	@FindBy(xpath= "//button[contains(text(),'PROCEED TO CHECKOUT')]")WebElement proceedtocheckout;
	@FindBy(xpath= "//button[contains(text(),'Place Order')]")WebElement placeorder;
	@FindBy(xpath= "//select")WebElement selectcountry;
	@FindBy(css = ".chkAgree")WebElement checkbox;
	@FindBy(xpath= "//button[contains(text(),'Proceed')]")WebElement proceed;
	@FindBy(xpath= "//span[contains(text(),'Thank you, your order has been placed successfully ')]")WebElement successmsg;
	
	
	public CheckoutBDD(WebDriver w) {
		this.w2= w;
		PageFactory.initElements(w, this);
	}

	public void checkouts()
	{
		checkoutbag.click();
		proceedtocheckout.click();
		WebDriverWait wait = new WebDriverWait(w2, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(placeorder));
		placeorder.click();
	}
	
	public void checkoutcomplete()
	{
		WebDriverWait wait = new WebDriverWait(w2, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(selectcountry));
		Select s = new Select(selectcountry);
		s.selectByVisibleText("India");
		checkbox.click();
		proceed.click();
	}
	
	public void successmessage()
	{
		WebDriverWait wait = new WebDriverWait(w2, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(successmsg));
		//String exp= "Thank you, your order has been placed successfully ";
		String act = successmsg.getText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(act.contains("Thank you"));
		sa.assertAll();
	}

}

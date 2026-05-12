package pages_DataDrivenTestingFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Checkout {
	
	WebDriver w;
	
	@FindBy(xpath= "//a[@class='cart-icon']")WebElement carticon;
	@FindBy(xpath= "//button[contains(text(),'PROCEED TO CHECKOUT')]")WebElement proceedtocheckout;
	@FindBy(css= ".promoCode")WebElement promo;
	@FindBy(xpath= "//button[contains(text(),'Apply')]")WebElement apply;
	@FindBy(xpath= "//button[contains(text(),'Place Order')]")WebElement placeorder;
	@FindBy(xpath= "//Select")WebElement select;
	@FindBy(css= ".chkAgree")WebElement checkbox;
	@FindBy(xpath= "//button[contains(text(),'Proceed')]")WebElement proceed;
	
	public Checkout(WebDriver w2) {
		this.w = w2;
		PageFactory.initElements(w, this);
	}

	public void checkoutoperation()
	{
		carticon.click();
		proceedtocheckout.click();
		placeorder.click();
		Select s = new Select(select);
		s.selectByVisibleText("India");
		checkbox.click();
		proceed.click();
		
	}

}

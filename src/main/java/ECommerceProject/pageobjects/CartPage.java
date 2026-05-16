package ECommerceProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ECommerceProject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	@FindBy(css = ".totalRow button")
	WebElement chkoutEle; 
    @FindBy(css=".cartSection h3")
    private List<WebElement> cartProducts;
	public boolean verifyProductDisplay(String productName) {
		
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout(){
		chkoutEle.click();
		return new CheckOutPage(driver);
				
	}
	
}

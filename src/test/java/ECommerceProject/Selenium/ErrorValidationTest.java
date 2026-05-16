package ECommerceProject.Selenium;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ECommerceProject.Selenium.TestComponents.BaseTest;
import ECommerceProject.Selenium.TestComponents.Retry;
import ECommerceProject.pageobjects.CartPage;
import ECommerceProject.pageobjects.CheckOutPage;
import ECommerceProject.pageobjects.ConfirmationPage;
import ECommerceProject.pageobjects.LandingPage;
import ECommerceProject.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		landingPage.login("sugandhasinha07@gmail.com", "Divisha@12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	
@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		// LandingPage landingPage=launchApplication();

		ProductCatalogue productCatalogue = landingPage.login("sugandhasinhadiv@gmail.com", "Divisha@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL33");
		Assert.assertFalse(match);
		

	}
}

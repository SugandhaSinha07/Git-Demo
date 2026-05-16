package ECommerceProject.Selenium;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ECommerceProject.Selenium.TestComponents.BaseTest;
import ECommerceProject.pageobjects.CartPage;
import ECommerceProject.pageobjects.CheckOutPage;
import ECommerceProject.pageobjects.ConfirmationPage;
import ECommerceProject.pageobjects.LandingPage;
import ECommerceProject.pageobjects.OrderPage;
import ECommerceProject.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		// LandingPage landingPage=launchApplication();

		ProductCatalogue productCatalogue = landingPage.login(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	// To verify if ADIDAS ORIGINAL is displaying on Orders page
	public void orderHistoryTest() {
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.login("sugandhasinha07@gmail.com", "Divisha@123");
		OrderPage orderspage = productCatalogue.goToOrderPage();
		Boolean match = orderspage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

	}
	public String getScreenShot(String testCaseName) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new  File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty  ("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	@DataProvider
	public Object[][] getData() throws IOException
	{	
		 
		List<HashMap<String, String>> data = getJsonDataToMap(
			    System.getProperty("user.dir") + "//src//test//java//ECommerceProject//data//PurchaseOrder.json"
			);
		  return new Object[][] {
		        { data.get(0) },
		        { data.get(1) }
		    };
		
	}
}

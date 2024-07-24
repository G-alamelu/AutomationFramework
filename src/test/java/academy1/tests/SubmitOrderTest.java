package academy1.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import academy1.TestComponents.BaseTest;
import academy1.pageobjects.CartPage;
import academy1.pageobjects.CheckoutPage;
import academy1.pageobjects.ConfirmationPage;
import academy1.pageobjects.OrderPage;
import academy1.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	// public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData" , groups= {"Purchase"})
	//public void submitOrder(String email, String password, String productName) throws Exception {
	public void submitOrder(HashMap<String ,String> input) throws Exception {

		/*
		 * LandingPage landingPage = new LandingPage(driver); landingPage.goTo();
		 * LandingPage landingPage = launchApplication();
		 */

		//ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		WebElement element = driver.findElement(By.cssSelector(".totalRow button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		WebElement element1 = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
		WebElement element2 = driver.findElement(By.cssSelector(".action__submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element2);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		/*
		 * WebElement element = driver.findElement(By.cssSelector(".totalRow button"));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * element);
		 * driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys
		 * ("india"); WebElement element1 =
		 * driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * element1); WebElement element2 =
		 * driver.findElement(By.cssSelector(".action__submit")); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].click();", element2);
		 */
		// String confirmMessage =
		// driver.findElement(By.cssSelector(".hero-primary")).getText();

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("alamelu.g@gmail.com", "Alamelu24");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		//commented as the data is driven from JSON file 
		
		/* HashMap<Object,Object> map = new HashMap<Object,Object>(); 
		 map.put("email", "alamelu.g@gmail.com"); 
		 map.put("password", "Alamelu24");
		 map.put("product",  "ZARA COAT 3");
		 
		 HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		 map1.put("email", "alamelu.g@gmail.com"); 
		 map1.put("password", "Alamelu24");
		 map1.put("product", "ADIDAS ORIGINAL");*/
		 		
		//return new Object[][] { { "alamelu.g@gmail.com", "Alamelu24", "ZARA COAT 3" }, { "alamelu.g@gmail.com", "Alamelu24", "ADIDAS ORIGINAL" } };
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//academy1//data//PurchaseOrder.json");
		// return new Object[][] {{map},{map1}};
		return new Object[][] {{data.get(0)}, {data.get(1)}};

	}

	


}

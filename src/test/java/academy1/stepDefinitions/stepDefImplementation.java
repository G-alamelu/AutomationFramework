package academy1.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import academy1.TestComponents.BaseTest;
import academy1.pageobjects.CartPage;
import academy1.pageobjects.CheckoutPage;
import academy1.pageobjects.ConfirmationPage;
import academy1.pageobjects.LandingPage;
import academy1.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("I add the (.+) from cart$")
	public void add_cart(String productname) throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
	}

	@And("Checkout (.+) and submit the order$")
	public void submit_order(String productname) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);
		WebElement element = driver.findElement(By.cssSelector(".totalRow button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		WebElement element1 = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
		WebElement element2 = driver.findElement(By.cssSelector(".action__submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element2);
		confirmationPage = checkoutPage.submitOrder();

	}

	@Then("{string} message is displayed on the confirmation page")
	public void message(String msg) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(msg));

	}
}

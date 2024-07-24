package academy1.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import academy1.TestComponents.BaseTest;
import academy1.TestComponents.Retry;
import academy1.pageobjects.CartPage;
import academy1.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"} ,retryAnalyzer=Retry.class)
	//@Test
	public void LoginErrorValidation() throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";
			
		landingPage.loginApplication("alamelu.g@gmail.com", "Aamelu24");
		System.out.println (landingPage.getErrorMessage());
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
		

	}
	

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("alamelu.g@gmail.com", "Alamelu24");
		//landingPage.getErrorMessage();
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}

	
	

}


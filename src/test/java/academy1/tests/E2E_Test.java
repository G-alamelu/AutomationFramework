package academy1.tests;

	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	import org.openqa.selenium.support.ui.ExpectedConditions;

	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;



	public class E2E_Test {

		public static void main(String[] args) throws Exception {
			// TODO Auto-generated method stub
			WebDriver driver = new ChromeDriver();
			String name = "ZARA COAT 3";
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://rahulshettyacademy.com/client");
			//landingPage obj = new landingPage(driver);
			driver.findElement(By.id("userEmail")).sendKeys("alamelu.g@gmail.com");
			driver.findElement(By.id("userPassword")).sendKeys("Alamelu24");
			driver.findElement(By.id("login")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
			// limiting the scope
			WebElement actual = products.stream()
					.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(name)).findFirst()
					.orElse(null);
			actual.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
			List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
			// cartproducts.stream().filter(s->s.getText().equalsIgnoreCase(name));
			Boolean match = cartproducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(name));
			Assert.assertTrue(match);
			WebElement element = driver.findElement(By.cssSelector(".totalRow button"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("india");
			WebElement element1 = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
			WebElement element2 = driver.findElement(By.cssSelector(".action__submit"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element2);
			String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		}

	}



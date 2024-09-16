package magento;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class MyTest {

	
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	@BeforeTest
	public void my_setup() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
	}
	

	
	@Test(priority = 1)
	public void log_in() {
		WebElement link = 	driver.findElement(By.linkText("Sign In"));
		link.click();
		
		 WebElement email = driver.findElement(By.id("email"));
		 email.sendKeys("anwaralthwaib71@gmail.com");
		 
		   WebElement password = driver.findElement(By.id("pass"));
		     password.sendKeys("sosos1234as#");
		 
		     WebElement button = driver.findElement(By.id("send2"));
		     button.click();
	}
	
	@Test(priority = 2)
	public void add_to_cart() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement Women = wait.until(ExpectedConditions.elementToBeClickable(By.id("ui-id-4")));
		Women.click();

	    WebElement tees = driver.findElement(By.cssSelector("div[class='sidebar sidebar-main'] li:nth-child(3) a:nth-child(1)"));
	    tees.click();

	     List<WebElement> items = driver.findElements(By.cssSelector(".item.product.product-item a.product-item-link"));
	    
	    for (int i = 0; i < 3; i++) {
	        
	        String itemLink = items.get(i).getAttribute("href");
	        
	     
	        driver.navigate().to(itemLink);
	        
	        Thread.sleep(2000);  

	       
	        List<WebElement> add_to_size = driver.findElements(By.cssSelector(".swatch-option.text"));
	        int rand20 = rand.nextInt(add_to_size.size());
	        add_to_size.get(rand20).click();

	     
	        List<WebElement> add_to_color = driver.findElements(By.cssSelector(".swatch-option.color"));
	        int rand200 = rand.nextInt(add_to_color.size());
	        add_to_color.get(rand200).click();

	        
	        WebElement add_to_cart = driver.findElement(By.id("product-addtocart-button"));
	        add_to_cart.click();

	        Thread.sleep(2000);  

	      
	        driver.navigate().back();
	        Thread.sleep(2000);

	        // بعد العودة، قم بتحديث قائمة العناصر إذا احتجت
	        items = driver.findElements(By.cssSelector(".item.product.product-item a.product-item-link"));
	    }
	    
	   
	    	
	    }
	
	 @Test(priority = 3)
	    public void  itemSum () {
		 
		 double expetedsumitem = 70;
		 
		 WebElement item =  driver.findElement(By.cssSelector(".action.showcart"));
		 item.click();
		 
		
		  WebElement priceactual = driver.findElement(By.cssSelector("span[data-bind='html: cart().subtotal_excl_tax'] span[class='price']"));
		  String priceAsString =   priceactual.getText().replace("$", "");
		  
		  double priceasdouble = Double.parseDouble(priceAsString);
		
		 
		 Assert.assertEquals(priceasdouble, expetedsumitem);
		 
		WebElement closeCart = driver.findElement(By.id("btn-minicart-close"));
		closeCart.click();
	}


	
	
}

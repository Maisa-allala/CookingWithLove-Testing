package UITesting;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class SellerTest{
	WebDriver driver;
	SellerDatabaseSetup sellerDbSetup;


	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		sellerDbSetup = new SellerDatabaseSetup(); 

	}


	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@BeforeClass
	public void setUpAdminDatabase() {
		try {

			sellerDbSetup.setupAdminData();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void tearDownAdminDatabase() {
		try {

			sellerDbSetup.tearDownAdminData();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void uster() {

	}

	@Test(priority = 1)
	public void SellerLogin() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		WebElement loginButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Login']"));
		Thread.sleep(2000);
		loginButton.click();

		AssertJUnit.assertTrue(
		driver.getCurrentUrl().contains("http://localhost/cookingwithlove/seller/SellerMainPage.php"));
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}

	@Test(priority = 2)
	public void SellerAddDish() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Add New Dish")).click();
		driver.findElement(By.name("dis_name")).sendKeys("TIRAMISU");
		driver.findElement(By.name("dish_detailes")).sendKeys("TIRAMISU");
		driver.findElement(By.name("food_price")).sendKeys("5");
		WebElement dropdown = driver.findElement(By.id("dis_catagory"));
		Select select = new Select(dropdown);
		select.selectByVisibleText("Desserts & Sweets");
		WebElement dropdown2 = driver.findElement(By.id("food_status"));
		Select select2 = new Select(dropdown2);
		select2.selectByVisibleText("Available");
		WebElement fileInput = driver.findElement(By.id("Upload_pic"));
		String projectDir = System.getProperty("user.dir");
		String imagePath = projectDir + "\\src\\Uploads\\TIRAMISU.jpg";
		fileInput.sendKeys(imagePath);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Add Dish']")).click();
		
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		Thread.sleep(2000);
		alert.accept();

		AssertJUnit.assertTrue(alertMessage.contains("Dish has been added successfully."));
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test(priority = 3)
	public void SellerUpdateDish() throws InterruptedException {
	    driver.get("http://localhost/cookingwithlove/login.php");
	    driver.findElement(By.id("username")).sendKeys("samarkal");
	    driver.findElement(By.id("password")).sendKeys("Project123!");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Current Dishes")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.className("card-link")).click();
	    driver.findElement(By.name("dis_name")).clear();
	    driver.findElement(By.name("dis_name")).sendKeys("Cheesecake");
	    driver.findElement(By.name("dish_detailes")).clear();
	    driver.findElement(By.name("dish_detailes")).sendKeys("Blueberry Cheesecake");
	    driver.findElement(By.name("food_price")).clear();
	    driver.findElement(By.name("food_price")).sendKeys("4");
		String projectDir = System.getProperty("user.dir");
		String imagePath = projectDir + "\\src\\Uploads\\cheesecake.jpg";
	    driver.findElement(By.id("Upload_pic")).sendKeys(imagePath);
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@type='submit' and @value='Update Dish']")).click();

	    Alert alert = driver.switchTo().alert();
	    String alertMessage = alert.getText();
	    Thread.sleep(2000);
	    alert.accept();

	    AssertJUnit.assertTrue(alertMessage.contains("Dish has been Updated."));

	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Log out")).click();
	}

	@Test(priority = 4)
	public void SellerDeleteDish() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Current Dishes")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("card-link")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Delete Dish']")).click();

		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		Thread.sleep(2000);
		alert.accept();

		AssertJUnit.assertTrue(alertMessage.contains("Dish has been Deleted."));
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}

	@Test(priority = 10)
	public void SellerEmptyDishesMessage() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Current Dishes")).click();
		List<WebElement> cardLinkElements = driver.findElements(By.className("card-link"));

	   while (!cardLinkElements.isEmpty()) {
		    WebElement cardLink = cardLinkElements.get(0); 
		    Thread.sleep(2000);
		    cardLink.click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//input[@type='submit' and @value='Delete Dish']")).click();

		    Alert alert = driver.switchTo().alert();
		    Thread.sleep(2000);
		    alert.accept();

		    cardLinkElements = driver.findElements(By.className("card-link"));    
		}

		AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[text()='No dishes found.']")).getText(), "No dishes found.");
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}

	@Test(priority = 5)
	public void SellerCompleteActiveOrder() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Active Orders")).click();
		
		WebElement orderContainer = driver.findElement(By.className("order-container"));
		List<WebElement> orderRows = orderContainer.findElements(By.tagName("tr"));
		
		while (!orderRows.isEmpty()) {
	        Thread.sleep(2000);
			WebElement row = orderRows.get(0);
			row.findElement(By.xpath("//tr[1]/td[2]")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//input[@type='submit' and @value='Mark as Complete']")).click();
		    Thread.sleep(2000);
		   
		    Alert alert = driver.switchTo().alert();
		    Thread.sleep(2000);
			String alertMessage = alert.getText();
		    alert.accept();
		    
			AssertJUnit.assertTrue(alertMessage.contains("Order has been Marked as Completed successfully."));
			
		    orderContainer = driver.findElement(By.className("order-container"));
		    orderRows = orderContainer.findElements(By.tagName("tr"));
		}
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}
	 
	
	@Test(priority = 6)
	public void SellerEmptyActiveOrders() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Active Orders")).click();

		AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[text()='No Orders found.']")).getText(), "No Orders found.");
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test(priority = 7)
	public void SellerMarkOrderPickedUp() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Ready for Pickup")).click();
		
		WebElement orderContainer = driver.findElement(By.className("order-container"));
		List<WebElement> orderRows = orderContainer.findElements(By.tagName("tr"));
        Thread.sleep(2000);
		WebElement row = orderRows.get(0); 
		row.findElement(By.xpath("//tr[1]/td[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit' and @value='Mark as Picked Up']")).click();
		
	    Alert alert = driver.switchTo().alert();
	    Thread.sleep(2000);
		String alertMessage = alert.getText();
	    alert.accept();
	    
		AssertJUnit.assertTrue(alertMessage.contains("Order has been Marked as Picked Up."));
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}
	 
	@Test(priority = 8)
	public void SellerViewCompletedOrder() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Completed Orders")).click();
		
		WebElement orderContainer = driver.findElement(By.className("order-container"));
		List<WebElement> orderRows = orderContainer.findElements(By.tagName("tr"));
        Thread.sleep(2000);
		WebElement row = orderRows.get(0); 
		row.findElement(By.xpath("//tr[1]/td[2]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test(priority = 9)
	public void SellerViewCancelledOrders() throws InterruptedException {
		driver.get("http://localhost/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("samarkal");
		driver.findElement(By.id("password")).sendKeys("Project123!");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Cancelled Orders")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Log out")).click();
	}


}

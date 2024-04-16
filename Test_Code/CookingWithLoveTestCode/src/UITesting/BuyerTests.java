package UITesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BuyerTests {

	private WebDriver driver;
	
	@BeforeClass
	public void setUp() throws SQLException, InterruptedException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// add data to database
		addBuyerTestData();
	}

	@AfterClass
	public void tearDown() throws SQLException {
		//clear database
		clearBuyerTestData();
		if (driver != null) {
			driver.quit();
		}
	}
	
	public void addBuyerTestData() throws SQLException, InterruptedException {
		// check if user by name of CoachEpp exists, if not create user
		String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
        Statement s = con.createStatement();
        
        // Insert test buyer into user_account table
        // Execute the INSERT statement
        s.executeUpdate("INSERT INTO user_account (accountID, userName, firstName, lastName, DOB, password, accountType, email, phone, street, zip, is_active, accountRequestedDate, accountActiveDate) " +
                   "VALUES ('7', 'CoachEpp', 'Malik', 'Epperson', '1984-10-03', '$2y$10$gUCpdSMYNEBX.i1J8dFVsuH6Kj1tHRduMZOmPdYjSskzFJAXK9NQe', 'Buyer', 'malikepp@umich.edu', '412-555-1212', '215 Mckenzie Dr', '15235', 'Y', '2024-03-17', '2024-03-19')");
                
        // Insert test data into dishes table
        // Execute the INSERT statement
        s.executeUpdate("INSERT INTO dishes (dishID, dishName, description, price, status, dishType, accountId, publishDate, photoPath, is_published, DishRequestedDate) " +
                   "VALUES ('64', 'Greek Salad', '2 Tomatoes', '4.00', 'available', 'Salad', '7', '2023-12-04', 'Uploads/GreekSalad.jpeg', 'Y', '2023-12-01')");
        
        // Insert test data into orders table
        // Execute the INSERT statement
        s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountId, delivery_complete, order_complete) " +
                   "VALUES ('102', '2024-01-06', 'PickUp', '24.00', 'N', '7', 'Y', 'N')");
        s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountId, delivery_complete, order_complete) " +
                "VALUES ('104', '2024-03-06', 'PickUp', '24.00', 'Y', '7', 'N', 'N')");
        
	}
	
	public void clearBuyerTestData() throws SQLException {
		// Remove all buyer test data from tables
		String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
        Statement s = con.createStatement();
        
        // clear orders table
        s.executeUpdate("Delete FROM orders");
        
        // clear dishes table
        s.executeUpdate("Delete FROM dishes");
        
        // clear user_account table
        s.executeUpdate("Delete FROM user_account");
                
		
	}
	
	@Test
	public void addToCartTest() throws InterruptedException
	{
		// open login page locally
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Search on buyer main page to get to list of dishes
		driver.findElement(By.linkText("Search")).click();
		// get the list of all the "Add To Cart" buttons on the page
		List<WebElement> addToCartButtons = driver.findElements(By.className("cart_button"));
		// select the first "Add to Cart" button which is the $4.00 Greek Salad
		addToCartButtons.get(0).click();
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "Item added to cart successfully.");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test
	public void updateCartTest() throws InterruptedException
	{
		// open login page locally and login
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Search on buyer main page to get to list of dishes
		driver.findElement(By.linkText("Search")).click();
		// get the list of all the "Add To Cart" buttons on the page
		List<WebElement> addToCartButtons = driver.findElements(By.className("cart_button"));
		// select the first "Add to Cart" button which is the $4.00 Greek Salad
		addToCartButtons.get(0).click();
		
		//navigate to cart
		driver.get("http://localhost/cookingwithlove/buyer/cart.php");
		driver.findElement(By.className("cart_qty")).sendKeys("4");
		Assert.assertEquals(driver.findElements(By.id("cart_footer")), "$16.00");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	
	}
	
	@Test
	public void emptyCartTest() throws InterruptedException
	{
		// open login page locally and login
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Search on buyer main page to get to list of dishes
		driver.findElement(By.linkText("Search")).click();
		// get the list of all the "Add To Cart" buttons on the page
		List<WebElement> addToCartButtons = driver.findElements(By.className("cart_button"));
		// select the first "Add to Cart" button which is the $4.00 Greek Salad
		addToCartButtons.get(0).click();
		
		//navigate to cart
		driver.get("http://localhost/cookingwithlove/buyer/cart.php");
		// delete any orders to ensure cart is empty
		driver.findElement(By.className("cart_qty")).sendKeys("0");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='cartform']/p]")).getText(), "There are no items in your cart.");
		Thread.sleep(10000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test
	public void checkoutTest() throws InterruptedException
	{
		// open login page locally
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Search on buyer main page to get to list of dishes
		driver.findElement(By.linkText("Search")).click();
		// get the list of all the "Add To Cart" buttons on the page
		List<WebElement> addToCartButtons = driver.findElements(By.className("cart_button"));
		// select the first "Add to Cart" button which is the $4.00 Greek Salad
		addToCartButtons.get(0).click();
		
		//navigate to cart
		driver.get("http://localhost/cookingwithlove/buyer/cart.php");
		driver.findElement(By.cssSelector("input[value='Checkout']")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "Order has been added successfully.");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test
	public void emptyCurrentOrdersTest() throws InterruptedException, SQLException
	{
		// empty all orders for my user
		String host = "localhost";
        String port = "3306";
        int acctId = 0;
        // connect to database
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
        Statement s = con.createStatement();
        
        // get account id for username CoachEpp
        ResultSet acctIdsFromTable = s.executeQuery("SELECT accountID FROM user_account WHERE userName = 'CoachEpp'");

     // Check if insertion was successful
        while (acctIdsFromTable.next()) {
            acctId = acctIdsFromTable.getInt("accountID");      
        }
        
        // Execute the DELETE statement
        s.executeUpdate("DELETE FROM orders WHERE accountId = '" + acctId +"'");
		
		// open login page locally
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();		
		//navigate to cart
		driver.get("http://localhost/cookingwithlove/buyer/cart.php");
		driver.findElement(By.cssSelector("input[value='Checkout']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='order-container']/p]")).getText(), "No Orders found.");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test
	public void currentOrdersTest() throws InterruptedException
	{
		// open login page locally
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Search on buyer main page to get to list of dishes
		driver.findElement(By.linkText("Search")).click();
		// get the list of all the "Add To Cart" buttons on the page
		List<WebElement> addToCartButtons = driver.findElements(By.className("cart_button"));
		// select the first "Add to Cart" button which is the $4.00 Greek Salad
		addToCartButtons.get(0).click();		
		//navigate to cart
		driver.findElement(By.linkText("Home")).click();
		//Checkout order to add to orders
		driver.findElement(By.cssSelector("input[value='Checkout']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='order-container']/p]")).getText(), "No Orders found.");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test
	public void previousOrdersTest() throws InterruptedException
	{
		// open login page locally
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Home to reach buyer main page
		driver.findElement(By.linkText("Home")).click();
		// select Home to reach buyer main page
		driver.findElement(By.className("Home")).click();
		// get the list of all the "Add To Cart" buttons on the page
		List<WebElement> addToCartButtons = driver.findElements(By.className("cart_button"));
		// select the first "Add to Cart" button which is the $4.00 Greek Salad
		addToCartButtons.get(0).click();		
		//navigate to cart
		driver.findElement(By.linkText("Home")).click();
		//Checkout order to add to orders
		driver.findElement(By.cssSelector("button pre_order")).click();
		Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "Previous Orders");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	}
	
	@Test
	public void cancelledOrdersTest() throws InterruptedException
	{
		// open login page locally
		driver.get("http://localhost/cookingwithlove/login.php");
		// provide buyer credentials
		driver.findElement(By.id("username")).sendKeys("CoachEpp");
		driver.findElement(By.id("password")).sendKeys("P@$$w0rd");
		Thread.sleep(1000);
		// login to account
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		// select Home to reach buyer main page
		driver.findElement(By.linkText("Home")).click();
		// Select cancelled orders button
		driver.findElement(By.cssSelector("button seller_cancel_order")).click();
		Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "Cancelled Orders");
		Thread.sleep(4000);
		driver.findElement(By.linkText("Log out")).click();
	}
}
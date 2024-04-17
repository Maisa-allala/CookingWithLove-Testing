package UITesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

	private WebDriver driver;

	@BeforeClass
	public void setUp() throws SQLException {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
        Statement s = con.createStatement();
	    int rowsAffected = s.executeUpdate("INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) " +
                "VALUES ('1234', 'testac1', 'Test1!', 'Seller', 'Test', 'Testing', 'test@gmail.com', '123-456-7890', '123 Test Road', '45678', '1901-01-01', NOW(), NULL, 'Y')");
	}

	@AfterClass
	public void tearDown() throws SQLException {
       String host = "localhost";
       String port = "3306";
       Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "Maisa82**");
       Statement s = con.createStatement();
	   // clear orders table
	   s.executeUpdate("DELETE FROM orders");
	        
	   // clear dishes table
	   s.executeUpdate("DELETE FROM dishes");
	        
	   // clear user_account table
	   s.executeUpdate("DELETE FROM user_account"); 

	   if (driver != null) {
		   driver.quit();
		}
	}
	
	//create base account for testing purposes
		//this also tests successful registration
		@Test
		public void addUserRegistrationTest() throws InterruptedException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			//confirm account creation success
			Assert.assertEquals(driver.switchTo().alert().getText(),
					"Congratulations! Your account has been created successfully.");
			driver.switchTo().alert().accept();
			}
		
		//test reset password function with various form entries
		//test 1: empty password fields - Meaghan Bryant
		@Test
		public void resetPasswordEmptyPasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testacc");
			driver.findElement(By.id("new_Password")).sendKeys("");
			driver.findElement(By.id("con_pass")).sendKeys("");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			WebElement errorMessageElement1 = driver.findElement(By.xpath("//div[4]/div[2]/span"));
			String errorMessageConfirm = errorMessageElement1.getText();
			Assert.assertEquals(errorMessage, "Password is required");
			
			//verify alert matches expected
			Assert.assertEquals(errorMessageConfirm, "Confirm password is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 2: no symbol  - Meaghan Bryant
		@Test
		public void resetPasswordNoSymbolTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testacc");
			driver.findElement(By.id("new_Password")).sendKeys("Testing");
			driver.findElement(By.id("con_pass")).sendKeys("Testing");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 3: too short  - Meaghan Bryant
		@Test
		public void resetPasswordTooShortTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testacc");
			driver.findElement(By.id("new_Password")).sendKeys("Tes1!");
			driver.findElement(By.id("con_pass")).sendKeys("Tes1!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 4: too long  - Meaghan Bryant
		//this test failed and revealed a bug - there is no upper limit of 8 characters
		@Test
		public void resetPasswordTooLongTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testac1");
			driver.findElement(By.id("new_Password")).sendKeys("Testingthislongpassword1!");
			driver.findElement(By.id("con_pass")).sendKeys("Testingthislongpassword1!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			Thread.sleep(2000);
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 5: no uppercase - Meaghan Bryant
		@Test
		public void resetPasswordNoUppercaseTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testacc");
			driver.findElement(By.id("new_Password")).sendKeys("test1!");
			driver.findElement(By.id("con_pass")).sendKeys("test1!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 6: no lowercase - Meaghan Bryant
		@Test
		public void resetPasswordNoLowercaseTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testacc");
			driver.findElement(By.id("new_Password")).sendKeys("TEST1!");
			driver.findElement(By.id("con_pass")).sendKeys("TEST1!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		
		//test reset password function with various form entries
		//test 7: passwords don't match - Meaghan Bryant
		@Test
		public void resetPasswordMismatchTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("testacc");
			driver.findElement(By.id("new_Password")).sendKeys("Testing1!");
			driver.findElement(By.id("con_pass")).sendKeys("Test1!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[4]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Password and confirm password do not match.");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 8: wrong username - Meaghan Bryant
		@Test
		public void resetPasswordWrongUsernameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("meaghanisgreat");
			driver.findElement(By.id("new_Password")).sendKeys("Test123!");
			driver.findElement(By.id("con_pass")).sendKeys("Test123!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div/div/p"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "User not found");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test reset password function with various form entries
		//test 9: empty username - Meaghan Bryant
		@Test
		public void resetPasswordEmptyUsernameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/login.php");
			driver.findElement(By.linkText("Forgot Password ?")).click();
			driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("");
			driver.findElement(By.id("new_Password")).sendKeys("Test123!");
			driver.findElement(By.id("con_pass")).sendKeys("Test123!");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[value='Save Password']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Username is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 1 no first name - Meaghan Bryant
		@Test
		public void userRegistrationNoFirstNameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Test Road");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "First Name is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//test 2 invalid first name (numbers) - Meaghan Bryant
		@Test
		public void userRegistrationInvalidFirstNameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("1234");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Test Road");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Only letters");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 3 no last name - Meaghan Bryant
		@Test
		public void userRegistrationNoLastNameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Test Road");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[2]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Last Name is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 4 invalid last name (numbers) - Meaghan Bryant
		@Test
		public void userRegistrationInvalidLastNameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("1234");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Test Road");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[2]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Only letters");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 5 no DOB - Meaghan Bryant
		@Test
		public void userRegistrationNoDOBTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("");
			driver.findElement(By.id("address")).sendKeys("1234 Test Road");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "DOB is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 6 invalid DOB (non-matching format) - Meaghan Bryant
		@Test
		public void userRegistrationInvaliDOBTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("july 4th 2024");
			driver.findElement(By.id("address")).sendKeys("1234 Test Road");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[3]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid DOB code.");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 7 empty address  - Meaghan Bryant
		@Test
		public void userRegistrationEmptyAddressTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[4]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Address is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 8 empty zip  - Meaghan Bryant
		@Test
		public void userRegistrationEmptyZipTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[5]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Zip code is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//test user registration with various inputs
		//test 9 invalid zip  - Meaghan Bryant
		@Test
		public void userRegistrationInvalidZipTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("zipcode");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[5]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid zip code.");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//test 10 empty phone number - Meaghan Bryant
		@Test
		public void userRegistrationEmptyPhoneTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[6]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Phone Number is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs	
		//test 11 invalid phone number - Meaghan Bryant
		@Test
		public void userRegistrationInvalidPhoneTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("phone");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[6]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid phone number");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//test user registration with various inputs
		//test 12 empty email - Meaghan Bryant
		@Test
		public void userRegistrationEmptyEmailTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[7]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Email Address is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//test user registration with various inputs
		//not a bug, but discovered a typo during this test - invalid is spelled invaild
		//test 13 invalid email - Meaghan Bryant
		@Test
		public void userRegistrationInvalidEmailTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("1");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[7]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invaild Email Address");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//test 14 empty username - Meaghan Bryant
		@Test
		public void userRegistrationEmptyUsernameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[9]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Username is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//The password tests should have the same results as in reset password
		//test 15 empty password - Meaghan Bryant
		@Test
		public void userRegistrationEmptyPasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("");
			driver.findElement(By.id("confirm_password")).sendKeys("");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[10]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			WebElement errorMessageElementConfirm = driver.findElement(By.xpath("//div[11]/div[2]/span"));
			String errorMessageConfirm = errorMessageElementConfirm.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Password is required");
			Assert.assertEquals(errorMessageConfirm, "Confirm password is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//test user registration with various inputs
		//The password tests should have the same results as in reset password
		//test 16 password with no symbols - Meaghan Bryant
		@Test
		public void userRegistrationNoSymbolPasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Testing");
			driver.findElement(By.id("confirm_password")).sendKeys("Testing");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[10]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//The password tests should have the same results as in reset password
		//test 17 too short password - Meaghan Bryant
		@Test
		public void userRegistrationTooShortPasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test");
			driver.findElement(By.id("confirm_password")).sendKeys("Test");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[10]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various input
		//The password tests should have the same results as in reset password
		//this test fails - there is no upper limit of 8 characters
		//test 18 too long password - Meaghan Bryant
		@Test
		public void userRegistrationTooLongPasswordTest() throws InterruptedException {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testac2");
			driver.findElement(By.id("password")).sendKeys("Testingthislongpassword1!");
			driver.findElement(By.id("confirm_password")).sendKeys("Testingthislongpassword1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[10]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);

			} catch (UnhandledAlertException e) {
				driver.switchTo().alert().accept();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
		//test user registration with various inputs
		//The password tests should have the same results as in reset password
		//test 19 no uppercase password - Meaghan Bryant
		@Test
		public void userRegistrationNoUpperCasePasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("test1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[10]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//The password tests should have the same results as in reset password
		//test 20 no lowercase password - Meaghan Bryant
		@Test
		public void userRegistrationNoLowerCasePasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("TEST1!");
			driver.findElement(By.id("confirm_password")).sendKeys("TEST1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[10]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Invalid password code");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user registration with various inputs
		//The password tests should have the same results as in reset password
		//test 21 passwords don't match - Meaghan Bryant
		@Test
		public void userRegistrationPasswordMisMatchTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost:/cookingwithlove/");
			driver.findElement(By.linkText("Log in")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("first_name")).sendKeys("Test");
			driver.findElement(By.id("last_name")).sendKeys("Testing");
			driver.findElement(By.id("dob")).sendKeys("1901-01-01");
			driver.findElement(By.id("address")).sendKeys("1234 Testing");
			driver.findElement(By.id("zip")).sendKeys("12345");
			driver.findElement(By.id("phone")).sendKeys("123-456-7890");
			driver.findElement(By.id("email")).sendKeys("test@gmail.com");
			driver.findElement(By.cssSelector("option[value='Buyer']")).click();
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Test1!");
			driver.findElement(By.id("confirm_password")).sendKeys("TesT1!");
			driver.findElement(By.xpath("//input[@value='Create Account']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[11]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Password and confirm password do not match.");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user login with various inputs
		//test 1 empty username - Meaghan Bryant
		@Test
		public void userLoginEmptyUsernameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost/cookingwithlove/login.php");
			driver.findElement(By.id("username")).sendKeys("");
			driver.findElement(By.id("password")).sendKeys("cIS565!");
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Username is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user login with various inputs
		//test 2 invalid username - Meaghan Bryant
		@Test
		public void userLoginInvalidUsernameTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost/cookingwithlove/login.php");
			driver.findElement(By.id("username")).sendKeys("thisusernameisfake");
			driver.findElement(By.id("password")).sendKeys("cIS565!");
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//p[contains(.,'User not found')]"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "User not found");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user login with various inputs
		//test 3 empty password - Meaghan Bryant
		@Test
		public void userLoginEmptyPasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost/cookingwithlove/login.php");
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("");
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//div[2]/div[2]/span"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Password is required");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//test user login with various inputs
		//test 4 invalid password - Meaghan Bryant
		@Test
		public void userLoginInvalidPasswordTest() {
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.get("http://localhost/cookingwithlove/login.php");
			driver.findElement(By.id("username")).sendKeys("testacc");
			driver.findElement(By.id("password")).sendKeys("Cis565&");
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			Thread.sleep(2000);
			
			//locate alert message on screen
			WebElement errorMessageElement = driver.findElement(By.xpath("//p"));
			String errorMessage = errorMessageElement.getText();
			
			//verify alert matches expected
			Assert.assertEquals(errorMessage, "Password does not match");
			Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
		

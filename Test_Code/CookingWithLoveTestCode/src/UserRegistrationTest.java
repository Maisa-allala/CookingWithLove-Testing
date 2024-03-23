import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class UserRegistrationTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/CookingWithLove/");
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.linkText("Create Account")).click();
		driver.findElement(By.id("first_name")).sendKeys("Ali");
		driver.findElement(By.id("last_name")).sendKeys("Baker");
		driver.findElement(By.id("dob")).sendKeys("1975-06-15");
		driver.findElement(By.id("address")).sendKeys("123 Alexis Rd");
		driver.findElement(By.id("zip")).sendKeys("47896");
		driver.findElement(By.id("phone")).sendKeys("419-789-9658");
		driver.findElement(By.id("email")).sendKeys("ali.baker@gmail.com");
		driver.findElement(By.cssSelector("option[value='Buyer']")).click();
		driver.findElement(By.id("username")).sendKeys("alibaker");
		driver.findElement(By.id("password")).sendKeys("Test**1234");
		driver.findElement(By.id("confirm_password")).sendKeys("Test**1234");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Create Account']")).click();
	
		Assert.assertEquals(driver.switchTo().alert().getText(), "Congratulations! Your account has been created successfully.");
		driver.switchTo().alert().accept();
		
		
	}

}

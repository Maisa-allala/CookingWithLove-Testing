import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ForgotPasswordTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.linkText("Forgot Password ?")).click();
		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("seller1");
		driver.findElement(By.id("new_Password")).sendKeys("Test**12345");
		driver.findElement(By.id("con_pass")).sendKeys("Test**12345");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Save Password']")).click();
	
		Assert.assertEquals(driver.switchTo().alert().getText(), "Password has been updated successfully.");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}

}

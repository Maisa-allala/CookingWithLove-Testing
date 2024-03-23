import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AdminViewActiveUsersTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("Test**12345");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("active_user")).click();
	
		Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "Active Users");
		Thread.sleep(4000);
        driver.findElement(By.linkText("Log out")).click();
	}

}

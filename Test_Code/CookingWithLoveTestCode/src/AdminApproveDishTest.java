import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AdminApproveDishTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("Test**12345");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("unpublish_dish")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("71")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name=\"ApproveforPublishing\"]")).click();

		
		Assert.assertEquals(driver.switchTo().alert().getText(), "Dish has been published successfully.");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(4000);
        driver.findElement(By.linkText("Log out")).click();
	}

}

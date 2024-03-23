import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class UserLoginTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("buyer1");
		driver.findElement(By.id("password")).sendKeys("Test**12345");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='welcome_user']:nth-child(1)")).getText(),
				"Welcome :buyer1\nAccount ID: 4");

		driver.findElement(By.linkText("Log out")).click();
		

	}

}

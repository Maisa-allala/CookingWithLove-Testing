import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;


public class Login{

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("maisa");
		driver.findElement(By.id("password")).sendKeys("1234");
		driver.findElement(By.className("btn-danger")).click();
		System.out.println(driver.findElement(By.cssSelector("p.styled-text-error")).getText());
		driver.findElement(By.linkText("Forgot Password ?")).click();

		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("maisa");
//		driver.findElement(By.id("new_Password")).sendKeys("12345");
		driver.findElement(By.cssSelector("input[type='password']:nth-child(1)")).sendKeys("Tahleen*12345");

		driver.findElement(By.xpath("//form/div[4]//input")).sendKeys("Tahleen*12345");
		driver.findElement(By.cssSelector("input[value='Save Password']")).click();
		Assert.assertEquals(false, null);
//		driver.findElement(by.xpath("//Button[text()='Log Out']")).click();

	}

}

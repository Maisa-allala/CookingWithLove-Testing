import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SellerAddNewDishTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys("seller1");
		driver.findElement(By.id("password")).sendKeys("Test**12345");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Add New Dish")).click();
		driver.findElement(By.id("dis_name")).sendKeys("Strawberry Dream Cake");
		driver.findElement(By.cssSelector("option[value='cake']")).click();
		driver.findElement(By.id("dish_detailes")).sendKeys("1 package white cake mix, 4 large eggs, 1 ½ cups frozen sweetened strawberries, 8 ounces cream cheese");
		driver.findElement(By.cssSelector("option[value='available']")).click();
		driver.findElement(By.id("food_price")).sendKeys("20");

	driver.findElement(By.cssSelector("input[type='file']")).sendKeys("C:/Users/maiss/OneDrive/Desktop/StrawberryDreamCake.JPG");
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("input[value='Add Dish']")).click();
	
	Assert.assertEquals(driver.switchTo().alert().getText(), "Dish has been added successfully.");
	driver.switchTo().alert().accept();
	
	driver.findElement(By.linkText("Log out")).click();
	}

}

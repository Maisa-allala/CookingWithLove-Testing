package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class loging {

	public static void main(String[] args) throws SQLException, InterruptedException {
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "" );
		Statement s=con.createStatement();
		ResultSet rs =s.executeQuery("SELECT * FROM user_account where firstName = 'Tahleen'");
		while(rs.next()) {
		System.out.println(rs.getString("userName"));
		System.out.println(rs.getString("password"));
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://localhost:8080/cookingwithlove/login.php");
		driver.findElement(By.id("username")).sendKeys(rs.getString("userName"));
		driver.findElement(By.id("password")).sendKeys(rs.getString("password"));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		
	}
	}

}

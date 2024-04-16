package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ordersTests {
	
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
                   "VALUES ('64', 'Greek Salad', '2 Tomatoes', '4.00', 'available', 'Salad', '3', '2023-12-04', 'Uploads/GreekSalad.jpeg', 'Y', '2023-12-01')");
        
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
        
        // clear user_account table
        s.executeUpdate("Delete FROM user_account");
        
        // clear user_account table
        s.executeUpdate("Delete FROM dishes");
        
        // clear user_account table
        s.executeUpdate("Delete FROM orders");
		
	}
    @Test
    public void insertOrderTest() throws SQLException {
        String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
        Statement s = con.createStatement();

          // Execute the INSERT statement
          s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete) " +
                     "VALUES ('122', '2024-03-02', 'PickUp', '4.00', 'N', '7', 'N', 'N')");
          
          ResultSet orderNumFromTable = s.executeQuery("SELECT orderNum FROM orders WHERE accountID = '7' AND date = '2024-03-02'");

        // Check if insertion was successful
          while (orderNumFromTable.next()) {
              int orderNum = orderNumFromTable.getInt("orderNum");
              Assert.assertEquals(orderNum, 122);
        
          }
       
    }
    
    @Test
    public void retrieveOrderTest() throws SQLException {
        String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
        Statement s = con.createStatement();

         // Execute the INSERT statement to verify there is data in the table
         s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete) " +
                        "VALUES ('132', '2024-02-24', 'PickUp', '24.00', 'N', '7', 'N', 'N')");
             
         ResultSet totalFromTable = s.executeQuery("SELECT totalPrice FROM orders WHERE accountID = '7' AND date = '2024-02-24'");

           // Check if insertion was successful
             while (totalFromTable.next()) {
                 String total = totalFromTable.getBigDecimal("totalPrice").toString();
                 Assert.assertEquals(total, "24.00");
           
             }
       
    }
    
    @Test
    public void updateOrderTest() throws SQLException {
        String host = "localhost";
        String port = "3306";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
             Statement s = con.createStatement();
     
             
             
	     // Execute the INSERT statement to verify there is data in the table
	     s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete) " +
                        "VALUES ('102', '2024-01-06', 'PickUp', '24.00', 'N', '7', 'N', 'N')");
	     
        // Execute the UPDATE statement
        s.executeUpdate("UPDATE orders SET delivery_complete = 'Y' WHERE orderNum = '102'");
        
        // Query the table to get the updated information
        ResultSet deliveryStatusFromTable = s.executeQuery("SELECT delivery_complete FROM orders WHERE orderNum = '102'");

            // Check if update was successful
        while (deliveryStatusFromTable.next()) {
            char deliveryStatus = deliveryStatusFromTable.getString("delivery_complete").charAt(0);
            Assert.assertEquals(deliveryStatus, 'Y');      
        }
       
    }
    
    @Test
    public void deleteOrderTest() throws SQLException {
        String host = "localhost";
        String port = "3306";
       Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
             Statement s = con.createStatement();
             
         // Execute the INSERT statement to verify there is data in the table
	     s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete) " +
                        "VALUES ('162', '2024-01-24', 'PickUp', '4.00', 'N', '7', 'Y', 'Y')");

        // Execute the DELETE statement
        int deletedRow = s.executeUpdate("DELETE FROM orders WHERE orderNum = '162'");

            
        // Check if deletion was successful       
        Assert.assertEquals(deletedRow, 1);      
        
       
    }
}
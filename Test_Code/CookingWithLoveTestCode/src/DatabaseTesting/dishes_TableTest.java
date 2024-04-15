package DatabaseTesting;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class dishes_TableTest {
	
	@BeforeClass
	public void setUp() throws SQLException {
		String host = "localhost";
	    Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "/cooking_project", "root", "");
	    Statement s = con.createStatement();
	    
	    // create user account for inserting dish to
	    int rowsAffected = s.executeUpdate("INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) " +
                "VALUES ('1', 'Testacc', 'Test1!', 'Seller', 'Test', 'Testing', 'test@gmail.com', '123-456-7890', '123 Test Road', '45678', '1901-01-01', NOW(), NULL, 'Y')");
	}
	
	@AfterClass
	public void tearDown() throws SQLException {
		String host = "localhost";
	    Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "/cooking_project", "root", "");
	    Statement s = con.createStatement();
		String usernameToDelete = "Testacc";
		
		//delete account
		int rowsAffected = s.executeUpdate("DELETE FROM user_account WHERE userName = '" + usernameToDelete + "'");
	}
    
	//test inserting a dish into database - Meaghan Bryant
	 @Test
	 public void testAddDish() throws SQLException {
		 String host = "localhost";
	     Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "/cooking_project", "root", "");
	     Statement s = con.createStatement();
	     // Execute the add statement
	     int rowsAffected = s.executeUpdate("INSERT INTO dishes (dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate)" + 
	    		 "VALUES (NULL, 'Energy Drink', 'Gottagofast', '2.50', 'available', 'Soups', '1', NULL, NULL, 'Y', '2024-04-09')");

	     // Check if insertion was successful
	     Assert.assertEquals(rowsAffected, 1, "Add dish failed.");
	       
	    }
	
	 //test deleting dish from database - Meaghan Bryant
	 @Test
	 public void testDeleteDish() throws SQLException {
		 String host = "localhost";
		 String dishNameToDelete = "Energy Drink";
	     Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "/cooking_project", "root", "");
	     Statement s = con.createStatement();

	     // Execute the delete statement
	     int rowsAffected = s.executeUpdate("DELETE FROM dishes WHERE dishName = '" + dishNameToDelete + "'");

	     // Check if insertion was successful
	     Assert.assertEquals(rowsAffected, 1, "Delete dish failed.");
	       
	    }
}

package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class dishes_TableTest {
    
	 @Test
	 public void testAddDish() throws SQLException {
		 String host = "localhost";
	     Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "/cooking_project", "root", "");
	     Statement s = con.createStatement();

	     // Execute the add statement
	     int rowsAffected = s.executeUpdate("INSERT INTO dishes (dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate)" + 
	    		 "VALUES (NULL, 'Energy Drink', 'Gottagofast', '2.50', 'available', 'Soups', '1', NULL, NULL, 'N', '2024-04-09')");

	     // Check if insertion was successful
	     Assert.assertEquals(rowsAffected, 1, "Add dish failed.");
	       
	    }
	
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

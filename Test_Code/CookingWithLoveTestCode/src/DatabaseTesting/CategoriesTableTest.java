
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoriesTableTest {

	    @Test
	    public void testInsertion() throws SQLException {
	        String host = "localhost";
	        String port = "3306";
	        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
	        Statement s = con.createStatement();

            // Execute the INSERT statement
            int rowsAffected = s.executeUpdate("INSERT INTO categories (categoryID, description)" +
                    "VALUES ('14', 'Bites')");

            // Check if insertion was successful
            Assert.assertEquals(rowsAffected, 1, "Data insertion failed.");
	            
	    }
	    
	    @Test
	    public void testUpdateOperation() throws SQLException {
	        String host = "localhost";
	        String port = "3306";
	        String descriptionToUpdate = "Bites"; // The username of the record to be updated
	        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
	        Statement s = con.createStatement();

	        int rowsAffected = s.executeUpdate("UPDATE categories SET description = 'Fruits' WHERE description = '" + descriptionToUpdate + "'");

	        // Check if update was successful
	        Assert.assertEquals(rowsAffected, 1, "Failed to update categories table with description: " + descriptionToUpdate);
	      
	    }
	    
	    @Test
	    public void testRetrieveOperation() throws SQLException {
	        String host = "localhost";
	        String port = "3306";
	        String descriptionToRetrieve = "Fruits"; // The username of the record to be retrieved
	        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
	        Statement s = con.createStatement();

	        // Execute the SELECT statement
	        ResultSet resultSet = s.executeQuery("SELECT description from categories WHERE description = '" + descriptionToRetrieve + "'");

	        // Check if data retrieval was successful
	        while (resultSet.next()) {
				String description = resultSet.getString("description");
				Assert.assertEquals(description, "Fruits", "Failed to retrieve correct category description with description: " + descriptionToRetrieve);
	   
	        }
     	}
	    
	    @Test
	    public void testDeleteOperation() throws SQLException {
	        String host = "localhost";
	        String port = "3306";
	        String descriptionToDelete = "Fruits"; // The username of the record to be deleted
	        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
	        Statement s = con.createStatement();

            // Execute the DELETE statement
            int rowsAffected = s.executeUpdate("DELETE FROM categories WHERE description = '" + descriptionToDelete + "'");

            // Check if deletion was successful
            Assert.assertEquals(rowsAffected, 1, "Failed to delete category description with description: " + descriptionToDelete);
       
	    }	
}

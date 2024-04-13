package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class user_accountTableDeleteDataTest {
    
    @Test
    public void testDeleteOperation() throws SQLException {
        String host = "localhost";
        String port = "3306";
        String userNameToDelete = "Ahmad"; // The username of the record to be deleted
       Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "Maisa82**");
             Statement s = con.createStatement();

            // Execute the DELETE statement
            int rowsAffected = s.executeUpdate("DELETE FROM user_account WHERE userName = '" + userNameToDelete + "'");

            // Check if deletion was successful
            Assert.assertEquals(rowsAffected, 1, "Failed to delete user account with username: " + userNameToDelete);
       
    }
}

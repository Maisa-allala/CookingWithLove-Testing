package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class user_accountTableUpdateDataTest {
    
    @Test
    public void testUpdateOperation() throws SQLException {
        String host = "localhost";
        String port = "3306";
        String userNameToUpdate = "Ahmad"; // The username of the record to be updated
       Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "");
             Statement s = con.createStatement();

            // Execute the UPDATE statement
            int rowsAffected = s.executeUpdate("UPDATE user_account SET accountType = 'Seller' WHERE userName = '" + userNameToUpdate + "'");

            // Check if update was successful
            Assert.assertEquals(rowsAffected, 1, "Failed to update user account with username: " + userNameToUpdate);
      
    }
}
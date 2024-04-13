package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class user_accountTableRetrieveDataTest {
    
    @Test
    public void testRetrieveOperation() throws SQLException {
        String host = "localhost";
        String port = "3306";
        String userNameToRetrieve = "Ahmad"; // The username of the record to be retrieved
       Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "Maisa82**");
             Statement s = con.createStatement();

             // Execute the SELECT statement
             ResultSet resultSet = s.executeQuery("SELECT lastName from user_account WHERE userName = '" + userNameToRetrieve + "'");

             // Check if data retrieval was successful
            while (resultSet.next()) {
                 String lastName = resultSet.getString("lastName");
                 Assert.assertEquals(lastName, "Hajar", "Failed to retrieve correct last name for user account with username: " + userNameToRetrieve);
           
             }
    }
}

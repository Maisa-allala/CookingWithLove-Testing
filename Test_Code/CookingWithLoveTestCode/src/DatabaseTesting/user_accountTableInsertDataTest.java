package DatabaseTesting;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class user_accountTableInsertDataTest {
    
    @Test
    public void testInsertion() throws SQLException {
        String host = "localhost";
        String port = "3306";
      Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cooking_project", "root", "Maisa82**");
             Statement s = con.createStatement();

            // Execute the INSERT statement
            int rowsAffected = s.executeUpdate("INSERT INTO user_account (userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) " +
                    "VALUES ('Ahmad', 'Test**12345', 'Buyer', 'Ahmad', 'Hajar', 'ahmad@gmail.com', '415-567-8569', 'Alexis Road', '45678', '1974-05-15', NOW(), NULL, 'N')");

            // Check if insertion was successful
            Assert.assertEquals(rowsAffected, 1, "Data insertion failed.");
      
    }
}
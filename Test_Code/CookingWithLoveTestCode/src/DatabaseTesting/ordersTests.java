package DatabaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ordersTests {
    
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
             
         ResultSet orderNumFromTable = s.executeQuery("SELECT totalPrice FROM orders WHERE accountID = '7' AND date = '2024-02-24'");

           // Check if insertion was successful
             while (orderNumFromTable.next()) {
                 int orderNum = orderNumFromTable.getInt("orderNum");
                 Assert.assertEquals(orderNum, 132);
           
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
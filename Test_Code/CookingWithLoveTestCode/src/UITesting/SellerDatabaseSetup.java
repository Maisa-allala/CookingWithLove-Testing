package UITesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SellerDatabaseSetup {
	// Define database connection parameters
	String host = "localhost";
	String port = "3306";
	String database = "cooking_project";
	String username = "root";
	String password = "samar216898";

	public void setupAdminData() throws SQLException {

		// Establish database connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database,
				username, password)) {
			// Create a statement
			try (Statement s = con.createStatement()) {

				// Insert multiple records into the user_account table
				int rowsAffected16 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (200, 'admin2', '$2y$10$QsW/nP4iBattPEEfCxqJA.TXIXAxFUB/wWtYibMVWn8w6pVLuFDO2', 'Admin', 'Samar', 'Kal', 'Samar@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '1997-10-05', '2023-12-01', '2023-12-11', 'Y')");

				int rowsAffected20 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (3, 'Samarkal', '$2y$10$qLhabk2IPx7ssCk3dd6GYeIBCcnJb2JWu/yxBic26up0grCmmNM2W', 'Seller', 'Samar', 'Kal', 'samar@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '1997-10-05', '2023-12-23', '2023-12-11', 'Y')");

				int rowsAffected3 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (4, 'buyer1', '$2y$10$QsW/nP4iBattPEEfCxqJA.TXIXAxFUB/wWtYibMVWn8w6pVLuFDO2', 'Buyer', 'Tahleen', 'Tousef', 'maisa@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '2024-01-02', '2023-12-11', null , 'Y')");

				// Insert multiple records into the dishes table
				int rowsAffected17 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (655, 'Banana Bread', 'Banana Bread', '4.00', 'available', 'desserts', 3, '2023-12-04', 'UITesting.Uploads/banana-bread.jpg', 'Y', '2023-12-01')");
				
				int rowsAffected18 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (666, 'Chocolate Cupcake', 'Chocolate Cupcake', '4.00', 'available', 'desserts', 3, '2023-12-04', 'UITesting.Uploads/chocolate-cupcake.jpg', 'Y', '2023-12-01')");
				
				int rowsAffected19 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (677, 'Banana Chocolatechip Bread', 'Banana Chocolatechip Bread', '4.00', 'available', 'desserts', 3, '2023-12-04', 'UITesting.Uploads/Banana-Bread_square.jpg', 'Y', '2023-12-01')");
				
				// Insert into orderitems
				int rowsAffected22 = s.executeUpdate("INSERT INTO orderitems (orderNum, DishID, DishName, quantity, price)" +
	                    "VALUES ('9', '655', 'Banana Bread', '1', '4.00')");
				
				int rowsAffected25 = s.executeUpdate("INSERT INTO orderitems (orderNum, DishID, DishName, quantity, price)" +
	                    "VALUES ('10', '666', 'Chocolate Cupcake', '1', '4.00')");
				
				int rowsAffected21 = s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete) " +
	                    "VALUES ('10', '2024-03-02', 'PickUp', '4.00', 'N', '4', 'N', 'N')");
				
				int rowsAffected23 = s.executeUpdate("INSERT INTO orders (orderNum, date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete) " +
	                     "VALUES ('9', '2024-03-02', 'PickUp', '4.00', 'N', '4', 'Y', 'Y')");
			
			
				// Check if insertion was successful for both records
				
				if ( rowsAffected3 != 1|| rowsAffected16 != 1 || rowsAffected17 != 1 
						 || rowsAffected22 != 1 || rowsAffected23 != 1 || rowsAffected21 != 1
						|| rowsAffected18 != 1 || rowsAffected19 != 1 || rowsAffected20 != 1
						|| rowsAffected25 != 1) {
					throw new SQLException("Data insertion failed for one or more records.");
				}
			}
		}
	}

	public void tearDownAdminData() throws SQLException {

		// Establish database connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database,
				username, password)) {
			// Create a statement
			try (Statement s = con.createStatement()) {

			    // Disable foreign key checks to allow deletion
			    s.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
			    
				// Delete multiple records from the user_account and dishes table
				int rowsAffected10 = s.executeUpdate("DELETE FROM orders WHERE orderNum = '" + 9 + "'");
				int rowsAffected11 = s.executeUpdate("DELETE FROM orderitems WHERE orderNum = '" + 9 + "' AND dishID = '" + 655 + "'" );
				
				int rowsAffected12 = s.executeUpdate("DELETE FROM orders WHERE orderNum = '" + 10 + "'");
				int rowsAffected13 = s.executeUpdate("DELETE FROM orderitems WHERE orderNum = '" + 10 + "' AND dishID = '" + 666 + "'" );
			    
				int rowsAffected6 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 655 + "'");
				int rowsAffected7 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 666 + "'");
				int rowsAffected8 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 677 + "'");

				int rowsAffected1 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 200 + "'");
				int rowsAffected3 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 3 + "'");
				int rowsAffected4 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 4 + "'");

				
			    // Enable foreign key checks
			    s.executeUpdate("SET FOREIGN_KEY_CHECKS=1");


				// Check if delete was successful for both records
				if (rowsAffected1 != 1 || rowsAffected3 != 1 || rowsAffected4 != 1
						|| rowsAffected6 != 1 || rowsAffected7 != 1 || rowsAffected8 != 1
						|| rowsAffected10 != 1 || rowsAffected11 != 1 || rowsAffected12 != 1 || rowsAffected13 != 1 
						) {
					throw new SQLException("Failed to delete one or more records.");
				}
			}
		}
	}

}

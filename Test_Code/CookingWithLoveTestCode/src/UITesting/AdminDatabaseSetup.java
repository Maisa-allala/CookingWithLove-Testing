package UITesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDatabaseSetup {
	// Define database connection parameters
	String host = "localhost";
	String port = "3306";
	String database = "cooking_project";
	String username = "root";
	String password = "";

	public void setupAdminData() throws SQLException {

		// Establish database connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database,
				username, password)) {
			// Create a statement
			try (Statement s = con.createStatement()) {

				// Insert multiple records into the user_account table
				int rowsAffected1 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (1, 'admin', '$2y$10$QsW/nP4iBattPEEfCxqJA.TXIXAxFUB/wWtYibMVWn8w6pVLuFDO2', 'Admin', 'Maisa', 'Allala', 'maisa@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '1974-05-15', '2023-12-01', '2023-12-11', 'Y')");

				int rowsAffected2 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (3, 'seller1', '$2y$10$0GIbjajnb1GHLGDj5Ttpn.ZshXkM2rbTHgGZnpVlvJ4a8uw8MKwGC', 'Seller', 'Maisa', 'Allala', 'maisa@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '1974-05-15', '2023-12-23', '2023-12-11', 'Y')");

				int rowsAffected3 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (4, 'buyer1', '$2y$10$QsW/nP4iBattPEEfCxqJA.TXIXAxFUB/wWtYibMVWn8w6pVLuFDO2', 'Buyer', 'Tahleen', 'Tousef', 'maisa@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '2024-01-02', '2023-12-11', null , 'Y')");

				int rowsAffected4 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (5, 'buyer2', '$2y$10$QsW/nP4iBattPEEfCxqJA.TXIXAxFUB/wWtYibMVWn8w6pVLuFDO2', 'Buyer', 'Maisa', 'Allala', 'maisa@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '2024-01-02', '2024-01-09', '2024-03-23', 'N')");

				int rowsAffected5 = s.executeUpdate(
						"INSERT INTO user_account (accountID, userName, password, accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active) "
								+ "VALUES (6, 'seller2', '$2y$10$QsW/nP4iBattPEEfCxqJA.TXIXAxFUB/wWtYibMVWn8w6pVLuFDO2', 'Seller', 'Maisa', 'Allala', 'maisa@yahoo.com', '415-567-8569', 'Alexis Road', '45678', '2024-01-02', '2024-01-25', '2024-03-20', 'N')");

				// Insert multiple records into the dishes table
				int rowsAffected6 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (64, 'Greek Salad', '2 tomatoes', '4.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");

				int rowsAffected7 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (65, 'Greek Salad', '2 tomatoes', '3.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");
				int rowsAffected8 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (66, 'Greek Salad', '2 tomatoes', '3.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");
				int rowsAffected9 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (67, 'Greek Salad', '2 tomatoes', '3.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");
				int rowsAffected10 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (68, 'Greek Salad', '2 tomatoes', '3.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");
				int rowsAffected11 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (69, 'Greek Salad', '2 tomatoes', '3.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");
				int rowsAffected12 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (70, 'Greek Salad', '2 tomatoes', '3.00', 'available', 'Salad', 3, '2023-12-04', 'Uploads/Greek Salad.jpeg', 'Y', '2023-12-01')");
				int rowsAffected13 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (71, 'Strawberry Dream Cake', '1 package white cake mix, 4 large eggs, 1 ½ cups frozen sweetened strawberries, 8 ounces cream cheese', '20.00', 'available', 'cake', 3, '2023-03-23', 'Uploads/StrawberryDreamCake.JPG', 'N', '2024-03-05')");
				int rowsAffected14 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (72, 'Strawberry Dream Cake', '1 package white cake mix, 4 large eggs, 1 ½ cups frozen sweetened strawberries, 8 ounces cream cheese', '20.00', 'available', 'cake', 3, null, 'Uploads/StrawberryDreamCake.JPG', 'N', '2024-03-05')");
				int rowsAffected15 = s.executeUpdate(
						"INSERT INTO dishes(dishID, dishName, description, price, status, dishType, accountID, publishDate, photoPath, is_published, DishRequestedDate) "
								+ "VALUES (73, 'Strawberry Dream Cake', '1 package white cake mix, 4 large eggs, 1 ½ cups frozen sweetened strawberries, 8 ounces cream cheese', '20.00', 'available', 'cake', 3, null, 'Uploads/StrawberryDreamCake.JPG', 'N', '2024-03-10')");

				// Check if insertion was successful for both records
//           
				if (rowsAffected1 != 1 || rowsAffected2 != 1 || rowsAffected3 != 1 || rowsAffected4 != 1
						|| rowsAffected5 != 1 || rowsAffected6 != 1 || rowsAffected7 != 1 || rowsAffected8 != 1
						|| rowsAffected9 != 1 || rowsAffected10 != 1 || rowsAffected11 != 1 || rowsAffected12 != 1
						|| rowsAffected13 != 1 || rowsAffected14 != 1 || rowsAffected15 != 1) {
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

				// Delete multiple records from the user_account and dishes table
				int rowsAffected6 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 64 + "'");
				int rowsAffected7 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 65 + "'");

				int rowsAffected8 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 66 + "'");
				int rowsAffected9 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 67 + "'");

				int rowsAffected10 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 68 + "'");
				int rowsAffected11 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 69 + "'");

				int rowsAffected12 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 70 + "'");
				int rowsAffected13 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 71 + "'");

				int rowsAffected14 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 72 + "'");
				int rowsAffected15 = s.executeUpdate("DELETE FROM dishes WHERE dishID = '" + 73 + "'");

				int rowsAffected1 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 1 + "'");

				int rowsAffected3 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 4 + "'");

				int rowsAffected4 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 5 + "'");
				int rowsAffected5 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 6 + "'");
				int rowsAffected16 = s.executeUpdate("DELETE FROM user_account WHERE userName = '" + "alibaker" + "'");

				int rowsAffected2 = s.executeUpdate("DELETE FROM user_account WHERE accountID = '" + 3 + "'");

				// Check if delete was successful for both records
				if (rowsAffected1 != 1 || rowsAffected2 != 1 || rowsAffected3 != 1 || rowsAffected4 != 1
						|| rowsAffected5 != 1 || rowsAffected6 != 1 || rowsAffected7 != 1 || rowsAffected8 != 1
						|| rowsAffected9 != 1 || rowsAffected10 != 1 || rowsAffected11 != 1 || rowsAffected12 != 1
						|| rowsAffected13 != 1 || rowsAffected14 != 1 || rowsAffected15 != 1 || rowsAffected16 != 1) {
					throw new SQLException("Failed to delete one or more records.");
				}
			}
		}
	}

}

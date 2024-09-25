package onlineReservationSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
	private static final String URL = "jdbc:mysql://localhost:3306/reservation_system"; // Change to your database name
    private static final String USER = "root"; // Change to your MySQL username
    private static final String PASSWORD = "Alekhya@6145"; // Change to your MySQL password

	public static Connection getConnection() {
		
	        Connection connection = null;
	        try {
	            // Ensure you're using the latest MySQL Connector/J 8.x or above for Java 17
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	            // Establish a connection to the database
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//	            System.out.println("Database connected successfully!");

	        } catch (ClassNotFoundException e) {
	            System.out.println("MySQL JDBC Driver not found.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("Error connecting to the database.");
	            e.printStackTrace(); 
	        } 
	        return connection;
	}
public static void main(String args[]) {
	
	        getConnection();
	    }
	}

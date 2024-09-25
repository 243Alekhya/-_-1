package onlineReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    
    // Method to check user login credentials
    public static boolean login(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        
        // Get login details from user
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // SQL query to check user credentials
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                System.out.println("Login successful!");
                return true; // Valid credentials
            } else {
                System.out.println("Invalid username or password.");
                return false; // Invalid credentials
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

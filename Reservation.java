package onlineReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Reservation {
    
    // Method to book a reservation
    public static void makeReservation(Connection connection, int userId) {
        Scanner scanner = new Scanner(System.in);
        
        // Get reservation details from user
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter journey date (YYYY-MM-DD): ");
        String journeyDate = scanner.nextLine();
        System.out.print("From (place): ");
        String fromPlace = scanner.nextLine();
        System.out.print("To (destination): ");
        String toPlace = scanner.nextLine();
        
        // Generate a unique PNR number (you can adjust the length/format as needed)
        String pnrNumber = generatePNR();
        
        // SQL query to insert reservation data
        String query = "INSERT INTO reservations (user_id, train_number, train_name, class_type, journey_date, from_place, to_place,pnr_number) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, trainNumber);
            preparedStatement.setString(3, trainName);
            preparedStatement.setString(4, classType);
            preparedStatement.setString(5, journeyDate);
            preparedStatement.setString(6, fromPlace);
            preparedStatement.setString(7, toPlace);
            preparedStatement.setString(8, pnrNumber);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Reservation successful!");
                System.out.println("Your PNR number is: " + pnrNumber);
            } else {
                System.out.println("Reservation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static String generatePNR() {
        Random random = new Random();
        // Generate a random 9-digit PNR number
        int pnr = 100000000 + random.nextInt(900000000);  // Generates a random 9-digit number
        return String.valueOf(pnr);
    }
}

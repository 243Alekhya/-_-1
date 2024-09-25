package onlineReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchReservations {


    // Method to fetch and display reservations for a specific user
    public static void getReservations(Connection connection, int userId) {
        String query = "SELECT * FROM reservations WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Check if the user has reservations
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No reservations found for this user.");
                return;
            }

            // Display reservations
            System.out.println("Reservations for User ID: " + userId);
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("id");
                int trainNumber = resultSet.getInt("train_number");
                String trainName = resultSet.getString("train_name");
                String classType = resultSet.getString("class_type");
                String journeyDate = resultSet.getString("journey_date");
                String fromPlace = resultSet.getString("from_place");
                String toPlace = resultSet.getString("to_place");
                
                System.out.println("Reservation ID: " + reservationId);
                System.out.println("Train Number: " + trainNumber);
                System.out.println("Train Name: " + trainName);
                System.out.println("Class Type: " + classType);
                System.out.println("Journey Date: " + journeyDate);
                System.out.println("From: " + fromPlace);
                System.out.println("To: " + toPlace);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

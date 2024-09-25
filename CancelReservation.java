package onlineReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CancelReservation {

    // Method to cancel a reservation based on PNR number
	public static void cancelReservation(Connection connection) {
	    Scanner scanner = new Scanner(System.in);
	    
	    // Get the PNR number from the user
	    System.out.print("Enter your PNR number: ");
	    String pnrNumber = scanner.nextLine().trim();
	    
	    try {
	        // Check if the reservation exists for the given PNR number
	        String checkReservationQuery = "SELECT id FROM reservations WHERE pnr_number = ?";
	        PreparedStatement checkReservationStmt = connection.prepareStatement(checkReservationQuery);
	        checkReservationStmt.setString(1, pnrNumber);
	        ResultSet reservationResult = checkReservationStmt.executeQuery();
	        
	        System.out.println("Querying for PNR: " + pnrNumber);
	        
	        if (!reservationResult.next()) {
	            System.out.println("No reservation found with this PNR number.");
	            return;
	        }
	        
	        int reservationId = reservationResult.getInt("id");
	        
	        // Confirm cancellation from the user
	        System.out.print("Do you want to confirm the cancellation? (yes/no): ");
	        String confirm = scanner.nextLine();
	        
	        if (confirm.equalsIgnoreCase("yes")) {
	            // Add an entry to the cancellations table
	            String insertCancellationQuery = "INSERT INTO cancellations (pnr_number, reservation_id, cancellation_date) VALUES (?, ?, NOW())";
	            PreparedStatement insertCancellationStmt = connection.prepareStatement(insertCancellationQuery);
	            insertCancellationStmt.setString(1, pnrNumber);
	            insertCancellationStmt.setInt(2, reservationId);
	            
	            int rowsAffected = insertCancellationStmt.executeUpdate();
	            
	            if (rowsAffected > 0) {
	                // Remove the entry from the reservations table
	                String deleteReservationQuery = "DELETE FROM reservations WHERE id = ?";
	                PreparedStatement deleteReservationStmt = connection.prepareStatement(deleteReservationQuery);
	                deleteReservationStmt.setInt(1, reservationId);
	                
	                int deletedRows = deleteReservationStmt.executeUpdate();
	                
	                if (deletedRows > 0) {
	                    System.out.println("Reservation successfully canceled.");
	                } else {
	                    System.out.println("Failed to cancel the reservation.");
	                }
	            } else {
	                System.out.println("Cancellation failed.");
	            }
	        } else {
	            System.out.println("Cancellation process aborted.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}

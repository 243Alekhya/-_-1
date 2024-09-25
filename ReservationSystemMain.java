package onlineReservationSystem;

import java.sql.Connection;
import java.util.Scanner;

public class ReservationSystemMain {

    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection(); // Use the connection method from your previous code
        
        if (connection != null) {
            // Prompt user to log in
        	System.out.println("Already having an account");
            boolean isLoggedIn = Login.login(connection);
            
            if (isLoggedIn) {
                // Get user ID (you could improve this by storing/logging user details on successful login)
                int userId = 1; // Assuming you assign userId after login
                
         
                
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. New user Reservations");
                System.out.println("2. Make Reservations");
                System.out.println("3. View Reservations");
                System.out.println("4. Cancel Reservation");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                
                if(choice ==1) {
                	UserRegistration.registerUser(connection);
                
                
                }
                else if(choice ==2) {
                	Reservation.makeReservation(connection, userId);
                }
               else if (choice == 3) {
              	// Fetch and display reservations
                  FetchReservations.getReservations(connection, userId);
                   
                } 
                else if (choice == 4) {
                	 // Cancel a reservation
                    CancelReservation.cancelReservation(connection);
                    }
                else {
                
                    System.out.println("Invalid choice.");
                }
                // Let the user make a reservation
//                Reservation.makeReservation(connection, userId);
//                FetchReservations.getReservations(connection, userId);
            }
        }
    }
}

package hotelmang;

import java.sql.*;
import java.util.Scanner;

public class DeleteReservations {
    public static void deletereservation(Connection connection) {
        Scanner sc = new Scanner(System.in);
        String query = "DELETE FROM reservations WHERE customer_id = ?"; // Fixed SQL syntax here

        System.out.println("Enter customer ID to delete:");
        int cid = sc.nextInt();

        System.out.println("Deleting reservation...");
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cid);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Reservation deleted successfully.");
            } else {
                System.out.println("No reservation found with the given customer ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error in deleting reservation: " + e.getMessage());
        }
    }
}

package hotelmang;

import java.sql.*;
import java.util.Scanner;

public class UpdateReservation {
    public static void updatereservation(Connection connection) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter customer ID:");
            int cid = sc.nextInt();
            sc.nextLine(); // consume the leftover newline

            System.out.println("Enter guest name:");
            String gname = sc.nextLine();

            System.out.println("Enter room number:");
            int roomno = sc.nextInt();
            sc.nextLine(); // consume the leftover newline

            System.out.println("Enter contact number:");
            String contact = sc.nextLine();

            System.out.println("Enter reservation date (YYYY-MM-DD format):");
            String rdate = sc.nextLine();

            String query = "UPDATE reservations SET guest_name = ?, room_no = ?, contact_no = ?, reservation_date = ? WHERE customer_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            // Setting parameters for the query
            ps.setString(1, gname);
            ps.setInt(2, roomno);
            ps.setString(3, contact);
            ps.setString(4, rdate);
            ps.setInt(5, cid);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Reservation updated successfully");
            } else {
                System.out.println("No reservation found with the given customer ID");
            }
        } catch (SQLException e) {
            System.out.println("Error updating reservation: " + e.getMessage());
        }
    }
}

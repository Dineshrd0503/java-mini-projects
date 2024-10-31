package hotelmang;
import java.sql.*;
import java.util.*;

public class CreateReservations {
    public static void createreservation(Connection connection) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("enter customer id");
            int cid = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character

            System.out.println("enter guest name");
            String gname = sc.nextLine();

            System.out.println("enter room number");
            int roomno = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline character

            System.out.println("enter contact no");
            String contact = sc.nextLine();

            System.out.println("enter reservation date (YYYY-MM-DD format)");
            String rdate = sc.nextLine();

            String query = "insert into reservations(customer_id,guest_name,room_no,contact_no,reservation_date) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, cid);
            ps.setString(2, gname);
            ps.setInt(3, roomno);
            ps.setString(4, contact);
            ps.setString(5, rdate);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0)
                System.out.println("Reservation created successfully");
            else
                System.out.println("Failed to create reservation");
        } catch(SQLException e) {
            System.out.println("Error while creating reservation");
            e.printStackTrace();
        }
    }
}

package hotelmang;
import java.sql.*;
import java.util.Scanner;
public class SearchReservations {
    public static void searchreservation(Connection connection)throws SQLException{
        Scanner sc=new Scanner(System.in);
        String query="select * from reservations where customer_id=?";
        System.out.println("enter customer id to search a record");
        int cid=sc.nextInt();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,cid);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               System.out.println("customer id is :"+rs.getInt("customer_id"));
               System.out.println("guest name is :"+rs.getString("guest_name"));
               System.out.println("room number is :"+rs.getInt("room_no"));
               System.out.println("contact number is :"+rs.getString("contact_no"));
               System.out.println("reservation date is :"+rs.getString("reservation_date"));

           }

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }




}

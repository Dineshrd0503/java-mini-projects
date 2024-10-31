package hotelmang;
import java.sql.*;
public class ViewReservations {
    public static void viewreservations(Connection connection){
        try{
            String sql="select * from reservations";
            System.out.println("displaying the reservtaions");
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt("customer_id");
                String name=rs.getString("guest_name");
                String roomType=rs.getString("room_no");
                String roomNumber=rs.getString("contact_no");
                String date=rs.getString("reservation_date");
                System.out.println("id: "+id);
                System.out.println("name: "+name);
                System.out.println("roomType: "+roomType);
                System.out.println("roomNumber: "+roomNumber);
                System.out.println("checkOutDate: "+date);
                System.out.println("---------------------------");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}

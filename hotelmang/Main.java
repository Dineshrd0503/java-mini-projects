package hotelmang;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static final String  url="jdbc:mysql://localhost:3306/hotel_db";
    public static final String user="root";
    public static  final String  password="1234@";
    public static Scanner sc= new Scanner(System.in);
    public static void main(String[] args) throws ClassNotFoundException{

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver laded successfully");
        }
        catch(ClassNotFoundException e){
            System.out.println("unable to load driver");
            System.out.println(e.getMessage());
            return;
        }
        try{
            Connection connection=DriverManager.getConnection(url,user,password);
            System.out.println("conncection estba;ishment successful");
            while(true) {
                System.out.println("press 0 to exit");
                System.out.println("1.create reservation");
                System.out.println("2.update reservation");
                System.out.println("3.delete reservation");
                System.out.println("4.view reservations");
                System.out.println("5.search reservation");
                System.out.println("6.exit");
                int ch = sc.nextInt();
                if (ch == 0) {
                    System.out.println("you chose to exit");
                    return;
                }
                switch (ch) {
                    case 1 -> CreateReservations.createreservation(connection);
                    case 2 -> UpdateReservation.updatereservation(connection);
                    case 3 -> DeleteReservations.deletereservation(connection);
                    case 4 -> ViewReservations.viewreservations(connection);
                    case 5 -> SearchReservations.searchreservation(connection);
                    default -> System.out.println("no opeartions found");
                }
            }

        }
    catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}

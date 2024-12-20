package hospitalmangsys;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
            private Scanner sc;
    public Doctor(Connection connection,Scanner sc){
        this.connection=connection;
        this.sc=sc;
    }
    public void viewDoctors(){
        String query="select * from doctors";
        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            System.out.println("ID\tName\tSpecialization");
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("specialization"));
            }


        }
        catch(SQLException e){
            e.printStackTrace();;
        }
    }
    public boolean getDoctorById(){
        String query="select * from doctors where id=?";
        System.out.println("Enter doctor ID");
        sc.nextLine();
        int id=sc.nextInt();

        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("ID\tName\tSpecialization");
                System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("specialization"));
                return true;
            }
            else{
                System.out.println("Doctor not found");
                return false;
            }

        }
        catch(SQLException e){
            e.printStackTrace();;
            return false;
        }
    }
}

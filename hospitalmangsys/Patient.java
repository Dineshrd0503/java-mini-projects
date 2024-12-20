package hospitalmangsys;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner sc;

    public Patient(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void addPatient() {
        System.out.println("enter name of the patient");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("enter age");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("enter gender");
        String gender = sc.nextLine();
        try {
            String query = "insert into patients(name,age,gender) values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("patient added successfully");
            else
                System.out.println("error occurred while inserting rows");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void viewPatients() {
        String query = "select * from patients";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("id\tname\tage\tgender");
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getInt("age") + "\t" + rs.getString("gender"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean getPatientById() {
        String query = "select * from patients where id=?";
        System.out.println("enter id of the patient");
        int id = sc.nextInt();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("id\tname\tage\tgender");
                System.out.println(rs.getInt("id") +"\t" + rs.getString("name") + "\t" + rs.getInt("age") + "\t" + rs.getString("gender"));

                return true;
            } else {
                System.out.println("No patient found with this id");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}


package hospitalmangsys;

import java.sql.*;
import java.util.Scanner;

public class Appointments {
    private Scanner sc;
    private Connection connection;
    public Appointments(Connection connection,Scanner sc){
        this.connection=connection;
        this.sc=sc;
    }

    public void viewAppointments(Connection connection,Scanner sc) {
        System.out.println("enter adimin credentials");
        sc.nextLine();
        System.out.println("enter username");
        String user=sc.nextLine();
        System.out.println("enter password");
        int password=sc.nextInt();
        if(!(user.equals("dinesh")&&password==2003)) {
            System.out.println("invalid credentials");
            System.exit(0);
        }
        String query = "select * from appointments";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("id\tpatient_id\tdoctor_id\tappointment_date");
            while (rs.next())
                System.out.println(rs.getInt("id")+"\t\t"+rs.getString("patient_id")+"\t\t"+rs.getString("doctor_id")+"\t\t"+rs.getString("appointment_date"));




        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public  void bookAppointment(Connection connection, Scanner sc, Patient patient, Doctor doctor) {
        System.out.println("Enter patient ID:");
        int patient_id = sc.nextInt();
        System.out.println("Enter doctor ID:");
        int doctor_id = sc.nextInt();
        System.out.println("Enter appointment date (YYYY-MM-DD):");
        sc.nextLine();
        String date = sc.nextLine();

        if (patient.getPatientById() && doctor.getDoctorById()) {
            if (checkDoctorAvaliability(doctor_id, date, connection)) {
                String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                try {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setInt(1, patient_id);
                    ps.setInt(2, doctor_id);
                    ps.setString(3, date);
                    int result = ps.executeUpdate();
                    if (result > 0) {
                        System.out.println("Appointment booked successfully");
                    } else {
                        System.out.println("Error while booking an appointment");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while booking an appointment");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor is not available on the selected date");
            }
        } else {
            System.out.println("Patient or doctor does not exist");
        }
    }

    public static boolean checkDoctorAvaliability(int doctor_id, String date, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, doctor_id);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            }
        } catch (SQLException e) {
            System.out.println("Error while checking doctor availability");
            e.printStackTrace();
        }
        return false;
    }


}

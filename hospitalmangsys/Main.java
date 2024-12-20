package hospitalmangsys;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/hospitaldemo";
    private static final String username="root";
    private static final String password="1234@";
    public static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("connection estvalished successfully");
            Patient patient=new Patient(connection,sc);
            Doctor doctor=new Doctor(connection,sc);
            Appointments appointment=new Appointments(connection,sc);
            while(true){
                System.out.println("welcome to onlie hospital managment system");
                System.out.println("1.Add patient");
                System.out.println("2.View Doctors");
                System.out.println("3.View Patients");
                System.out.println("4.Search Patient");
                System.out.println("5.seearch doctor");
                System.out.println("6.Book Appointment");
                System.out.println("7.view Appointments");
                System.out.println("8.Exit");
                System.out.println("enter your choice");
                int ch=sc.nextInt();
                switch(ch){
                    case 1->patient.addPatient();
                    case 2->doctor.viewDoctors();
                    case 3->patient.viewPatients();
                    case 4->patient.getPatientById();
                    case 5->doctor.getDoctorById();
                    case 6->appointment.bookAppointment(connection,sc,patient,doctor);
                    case 7->appointment.viewAppointments(connection,sc);
                    case 8->System.exit(0);
                    default->System.out.println("invalid choice");
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}

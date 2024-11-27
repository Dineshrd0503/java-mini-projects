package banksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner sc;

    public User(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void register() {
        sc.nextLine();
        System.out.println("Enter your full name:");
        String name = sc.nextLine();
        System.out.println("Enter your email:");
        String email = sc.nextLine();
        System.out.println("Enter your password:");
        String password = sc.nextLine();

        if (user_exists(email)) {
            System.out.println("User already exists.");
            return;
        }

        String register_query = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(register_query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("User registered successfully.");
            } else {
                System.out.println("Error in registering user.");
            }
        } catch (SQLException e) {
            System.out.println("Error in registering user: " + e.getMessage());
        }
    }

    public String login() {
        sc.nextLine();
        System.out.println("Enter email:");
        String email = sc.nextLine();
        System.out.println("Enter password:");
        String password = sc.nextLine();

        String login_query = "SELECT email FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(login_query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful.");
                return email;
            } else {
                System.out.println("Invalid email or password.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error in login: " + e.getMessage());
        }
        return null;
    }

    public boolean user_exists(String email) {
        String check_query = "SELECT email FROM users WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(check_query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error in checking user existence: " + e.getMessage());
        }
        return false;
    }
}

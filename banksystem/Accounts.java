package banksystem;

import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection connection;
    private Scanner sc;

    public Accounts(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }


    public long open_account(String email) {
        // Validate if the account already exists
        if (!account_exist(email)) {
            String query = "INSERT INTO accounts(acc_no, full_name, email, balance, pin) VALUES(?,?,?,?,?)";
            sc.nextLine(); // Clear scanner buffer
            System.out.println("Enter your full name:");
            String name = sc.nextLine();
            System.out.println("Enter your initial balance:");
            double initial_balance = sc.nextDouble();
            sc.nextLine(); // Clear scanner buffer
            System.out.println("Enter a 4-digit PIN:");
            String pin = sc.nextLine();

            // Ensure the PIN is valid
            if (pin.length() != 4 || !pin.matches("\\d+")) {
                System.out.println("Invalid PIN. Please enter a 4-digit numeric PIN.");
                return -1;
            }

            try {
                long acc_no = generate_acno(); // Generate a unique account number
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setLong(1, acc_no);
                ps.setString(2, name);
                ps.setString(3, email); // Use the existing email
                ps.setDouble(4, initial_balance);
                ps.setString(5, pin);

                int result = ps.executeUpdate();
                if (result > 0) {
                    System.out.println("Account created successfully! Your account number is: " + acc_no);
                    return acc_no;
                } else {
                    System.out.println("Error in creating the account.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("An account already exists with this email.");
        }
        return -1;
    }


    public long get_acno(String email) {
        String query = "SELECT acc_no FROM accounts WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("acc_no");
            } else {
                System.out.println("No account found with this email.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }


    private long generate_acno() {
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT acc_no FROM accounts ORDER BY acc_no DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return rs.getLong("acc_no") + 1;
            } else {
                return 10000100; // Default starting account number
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to generate account number: " + e.getMessage());
        }
    }

    public boolean account_exist(String email) {
        String query = "SELECT acc_no FROM accounts WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}

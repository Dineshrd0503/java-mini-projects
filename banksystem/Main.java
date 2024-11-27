package banksystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/banksystem";
    private static final String username = "root";
    private static final String password = "1234@";

    public static void main(String[] args) {
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e.getMessage());
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database successfully.");

            Scanner sc = new Scanner(System.in);
            User user = new User(connection, sc);
            Accounts account = new Accounts(connection, sc);
            AccountManager acmang = new AccountManager(connection, sc);

            while (true) {
                // Display main menu
                System.out.println("\n=== Welcome to Online Banking System ===");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: // Register a new user
                        user.register();
                        break;

                    case 2: // Login
                        String email = user.login();
                        if (email != null) {
                            System.out.println("You are successfully logged in!");
                            long acc_no;
                            if (!account.account_exist(email)) {
                                System.out.println("\nYou do not have a bank account.");
                                System.out.println("1. Open a new bank account");
                                System.out.println("2. Exit");
                                System.out.print("Enter your choice: ");
                                int subChoice = sc.nextInt();
                                if (subChoice == 1) {
                                    acc_no = account.open_account(email);
                                    if (acc_no != -1) {
                                        System.out.println("Your new account number is: " + acc_no);
                                    }
                                } else {
                                    System.out.println("Exiting...");
                                    break;
                                }
                            }

                            // Retrieve account number after ensuring account exists
                            acc_no = account.get_acno(email);
                            System.out.println("\nYour account number is: " + acc_no);

                            // Logged-in user's banking operations
                            int bankingChoice = 0;
                            while (bankingChoice != 5) {
                                System.out.println("\n=== Banking Options ===");
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Check Balance");
                                System.out.println("4. Transfer Money");
                                System.out.println("5. Logout");
                                System.out.print("Enter your choice: ");
                                bankingChoice = sc.nextInt();

                                switch (bankingChoice) {
                                    case 1: // Debit money
                                        acmang.debit_money(acc_no);
                                        break;
                                    case 2: // Credit money
                                        acmang.credit_money(acc_no);
                                        break;
                                    case 3: // Check balance
                                        acmang.get_balance(acc_no);
                                        break;
                                    case 4: // Transfer money
                                        acmang.transfer_money(acc_no);
                                        break;
                                    case 5: // Logout
                                        System.out.println("Logging out...");
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again.");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Login failed. Please try again.");
                        }
                        break;

                    case 3: // Exit the system
                        System.out.println("Thank you for using our banking system. Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}

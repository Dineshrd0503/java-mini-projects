package banksystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner sc;

    public AccountManager(Connection connection, Scanner sc) {
        this.connection = connection;
        this.sc = sc;
    }

    public void debit_money(long acc_no) throws SQLException {
        System.out.println("Enter amount to debit:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter security pin:");
        String pin = sc.nextLine();

        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("SELECT balance FROM accounts WHERE acc_no = ? AND pin = ?");
            ps.setLong(1, acc_no);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                if (amount <= balance - 500) {
                    PreparedStatement debitPs = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE acc_no = ?");
                    debitPs.setDouble(1, amount);
                    debitPs.setLong(2, acc_no);

                    int rows = debitPs.executeUpdate();
                    if (rows > 0) {
                        connection.commit();
                        System.out.println("Transaction successful. Amount debited: " + amount);
                    } else {
                        connection.rollback();
                        System.out.println("Transaction failed.");
                    }
                } else {
                    System.out.println("Insufficient balance. Maintain a minimum balance of 500.");
                }
            } else {
                System.out.println("Invalid account number or pin.");
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Transaction failed: " + e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void credit_money(long acc_no) throws SQLException {
        System.out.println("Enter amount to credit:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter security pin:");
        String pin = sc.nextLine();

        try {
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("SELECT acc_no FROM accounts WHERE acc_no = ? AND pin = ?");
            ps.setLong(1, acc_no);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PreparedStatement creditPs = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE acc_no = ?");
                creditPs.setDouble(1, amount);
                creditPs.setLong(2, acc_no);

                int rows = creditPs.executeUpdate();
                if (rows > 0) {
                    connection.commit();
                    System.out.println("Transaction successful. Amount credited: " + amount);
                } else {
                    connection.rollback();
                    System.out.println("Transaction failed.");
                }
            } else {
                System.out.println("Invalid account number or pin.");
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Transaction failed: " + e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void get_balance(long acc_no) {
        sc.nextLine();
        System.out.println("Enter security pin:");
        String pin = sc.nextLine();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT balance FROM accounts WHERE acc_no = ? AND pin = ?");
            ps.setLong(1, acc_no);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                System.out.println("Your current balance is: " + balance);
            } else {
                System.out.println("Invalid account number or pin.");
            }
        } catch (SQLException e) {
            System.out.println("Unable to fetch balance: " + e.getMessage());
        }
    }

    public void transfer_money(long sender_acc_no) throws SQLException {
        System.out.println("Enter receiver account number:");
        long receiver_acc_no = sc.nextLong();
        System.out.println("Enter amount to transfer:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter sender security pin:");
        String sender_pin = sc.nextLine();

        try {
            connection.setAutoCommit(false);

            PreparedStatement senderPs = connection.prepareStatement("SELECT balance FROM accounts WHERE acc_no = ? AND pin = ?");
            senderPs.setLong(1, sender_acc_no);
            senderPs.setString(2, sender_pin);
            ResultSet senderRs = senderPs.executeQuery();

            if (senderRs.next()) {
                double sender_balance = senderRs.getDouble("balance");

                if (amount <= sender_balance - 500) {
                    PreparedStatement receiverPs = connection.prepareStatement("SELECT acc_no FROM accounts WHERE acc_no = ?");
                    receiverPs.setLong(1, receiver_acc_no);
                    ResultSet receiverRs = receiverPs.executeQuery();

                    if (receiverRs.next()) {
                        PreparedStatement debitPs = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE acc_no = ?");
                        PreparedStatement creditPs = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE acc_no = ?");
                        debitPs.setDouble(1, amount);
                        debitPs.setLong(2, sender_acc_no);
                        creditPs.setDouble(1, amount);
                        creditPs.setLong(2, receiver_acc_no);

                        int debitRows = debitPs.executeUpdate();
                        int creditRows = creditPs.executeUpdate();

                        if (debitRows > 0 && creditRows > 0) {
                            connection.commit();
                            System.out.println("Transaction successful. Amount transferred: " + amount);
                        } else {
                            connection.rollback();
                            System.out.println("Transaction failed.");
                        }
                    } else {
                        System.out.println("Receiver account number is invalid.");
                    }
                } else {
                    System.out.println("Insufficient balance. Maintain a minimum balance of 500.");
                }
            } else {
                System.out.println("Invalid sender account number or pin.");
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Transaction failed: " + e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

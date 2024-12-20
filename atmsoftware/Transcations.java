package atmsoftware;
import java.util.Scanner;

        public class Transcations {
            public static void deposit(String accno) {
                // Logic to deposit money
                Scanner sc = new Scanner(System.in);
                System.out.println("enter the amount to deposit");
                double amt = sc.nextDouble();
                if (amt <= 0) {
                    System.out.println("Invalid amount. Please enter a positive value.");
                    return;
                }
                // Update balance and add unique transaction
                Main.balance += amt;
                String transactionKey = "Deposit_" + System.currentTimeMillis();
                Main.transactions.put(transactionKey, amt);
                System.out.println("Deposited " + amt + " to account number " + accno);
            }

            public static void withdraw(String accno) {
                // Logic to withdraw money
                Scanner sc = new Scanner(System.in);
                System.out.println("enter the amount to withdraw");
                double amt = sc.nextDouble();
                if (amt <= 0) {
                    System.out.println("Invalid amount. Please enter a positive value.");
                    return;
                }
                if (Main.balance - amt <= 500) {
                    System.out.println("Insufficient balance. min balance should be 500");
                    return;
                }
                // Update balance and add unique transaction
                Main.balance -= amt;
                String transactionKey = "Withdraw_" + System.currentTimeMillis();
                Main.transactions.put(transactionKey, amt);
                System.out.println("Withdrawn " + amt + " from account number " + accno);
            }

            public static void transfer(String accno) {
                // Logic to transfer money
                Scanner sc = new Scanner(System.in);
                System.out.println("enter the amount to transfer");
                double amt = sc.nextDouble();
                sc.nextLine();
                if (amt <= 0) {
                    System.out.println("Invalid amount. Please enter a positive value.");
                    return;
                }
                if (Main.balance - amt <= 500) {
                    System.out.println("Insufficient balance. min balance should be 500");
                    return;
                }
                System.out.println("enter the receiver account number");
                String recaccno = sc.nextLine();
                String accnoPattern = "\\d{13}";
                if (!recaccno.matches(accnoPattern)) {
                    System.out.println("Invalid account number");
                    return;
                }
                // Update balance and add unique transaction
                Main.balance -= amt;
                String transactionKey = "Transfer_" + System.currentTimeMillis();
                Main.transactions.put(transactionKey, amt);
                System.out.println("Transferred " + amt + " from account number " + accno);
            }
        }
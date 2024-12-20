package atmsoftware;
import java.util.*;
public class Main {
    static String accno;
    static String password;
    static double balance=10000;
    static Map<String,Double> transactions = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ATM Software");
        System.out.println("Enter your account number:");
        String accno = sc.nextLine().trim();
        System.out.println("Enter your password:");
        String password = sc.nextLine().trim();

        if(ValidateCustomer.validate(accno,password))
            System.out.println("Login successful");
        else {
            System.out.println("Invalid account number or password");
            return;
        }
        System.out.println("Choose an option:");
        while(true) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6.view Your Details");
            System.out.println("7. Exit");
            int choice = sc.nextInt();
            if(choice == 7) {
                System.out.println("Thank you for using ATM Software. Goodbye!");
                return;
            }
            switch(choice) {
                case 1 -> Transcations.deposit(accno);
                case 2 -> Transcations.withdraw(accno);
                case 3 -> History.viewbalance(accno, balance);
                case 4 -> Transcations.transfer(accno);
                case 5 -> History.viewhistory(accno, transactions, balance);
                case 6 -> CustomerDetails.viewDetails(accno, password, transactions, balance);
                default -> System.out.println("Invalid choice. Please try again.");


            }
        }




    }
}

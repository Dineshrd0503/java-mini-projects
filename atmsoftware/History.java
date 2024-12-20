package atmsoftware;

import java.util.Map;

public class History {
    public static void viewbalance(String accno,double balance) {
        System.out.println("Account Number: " + accno);
        System.out.println("Balance: " + balance);
        System.out.println("Thank you for using ATM Software");
    }
    public static void viewhistory(String accno, Map<String, Double> transcations, double balance) {
        System.out.println("Account Number: " + accno);
        System.out.println("Transaction History:");
        for (Map.Entry<String, Double> entry : transcations.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Thank you for using ATM Software");
    }
}

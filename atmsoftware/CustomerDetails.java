package atmsoftware;

import java.util.Map;

public class CustomerDetails {
    public static void viewDetails(String accno, String name,Map<String, Double> transcations, double balance) {
        System.out.println("Account Number: " + accno);
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);
        System.out.println("Transaction History:");
        for (Map.Entry<String, Double> entry : transcations.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Thank you for using ATM Software");
    }
}

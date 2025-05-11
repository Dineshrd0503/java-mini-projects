package electricitybilling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewDetails {
    public static void viewHistory(String accno, HashMap<String, List<Double>> transcations) {
        System.out.println("Account Number: " + accno);
        System.out.println("Transaction History:");
        for (Map.Entry<String, List<Double>> entry : transcations.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Thank you for using Electricity Billing System");
    }

    public static void viewDetails(String accno) {
        System.out.println("dear usotmer ");
        System.out.println("Account Number: " + accno);
        System.out.println("thank you for using Electricity Billing System");
    }
}

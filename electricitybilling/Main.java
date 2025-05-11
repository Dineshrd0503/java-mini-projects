package electricitybilling;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static HashMap<String, List<Double>> transcations = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Electricity Billing System");
        System.out.println("enter your 13 digit unique account number");
        String accno= sc.nextLine();
        if(!accno.matches("\\d{13}")){
            System.out.println("❌ Account number must be exactly 13 digits.");
            return;
        }
        else{
            System.out.println("welcome user");
            while(true) {
                System.out.println("1.pay bill\n2.view details\n3.view history\n4.exit\5.0 to exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> PayBill.payBill(accno);
                    case 2 -> ViewDetails.viewDetails(accno);
                    case 3 -> ViewDetails.viewHistory(accno, transcations);
                    case 4 -> {
                        System.out.println("Thank you for using the Electricity Billing System. Goodbye!");
                        return;
                    }
                }
            }
        }
    }
}

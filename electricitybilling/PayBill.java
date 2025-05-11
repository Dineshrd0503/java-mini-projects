package electricitybilling;

import java.util.ArrayList;
import java.util.Scanner;

public class PayBill {

    public static void payBill(String accno) {
        Scanner sc= new Scanner(System.in);
        double units;
        double slab=0;
        double billAmount;
        System.out.println("Enter the number of units consumed:");
        units = sc.nextInt();
        if (units < 0) {
            System.out.println("Invalid number of units. Please enter a positive value.");
            return;
        }
        if(units>0&&units<=100){
            // Assuming a rate of 5.0 per unit
            slab=3.0;
            billAmount = units * 3.0;
        }
        else if(units>100&&units<=200){
            // Assuming a rate of 5.0 per unit
            slab=5.0;
            billAmount = units * 5.0;
        }
        else if(units>200&&units<=300){
            // Assuming a rate of 5.0 per unit
            slab=7.0;
            billAmount = units * 7.0;
        }
        else{
            // Assuming a rate of 10.0 per unit
            slab=10.0;
            billAmount = units * 10.0;
        }
        System.out.println("Bill Amount for " + units + " units: " + billAmount);
        System.out.println("Slab Rate: " + slab);
        Main.transcations.putIfAbsent(accno, new ArrayList<>());
        Main.transcations.get(accno). add(units);

    }
}

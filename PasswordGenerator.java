import java.util.*;
import java.lang.Math;

public class PasswordGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] choices = {"rock", "paper", "scissor"};
        while (true) {
            System.out.println("press 1 to start 0 t exit");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice != 1) {
                System.out.println("you chose ti exit");
                return;
            }
            System.out.println("Enter your choice (rock, paper, scissor)");
            String playerChoice = sc.nextLine().toLowerCase();
            while (!Arrays.asList(choices).contains(playerChoice)) {
                System.out.println("invalid inpt,renerter the crt inpt choice");
                playerChoice = sc.nextLine().toLowerCase();

            }
            int computerChoiceIndex = rand.nextInt(choices.length);
            String computerChoice = choices[computerChoiceIndex];
            System.out.println("Computer choice: " + computerChoice);
            System.out.println("user choicee " + playerChoice);
            if (computerChoice.equals(playerChoice))
                System.out.println("its a tie");
            else if ((computerChoice.equals("paper") && playerChoice.equals("scissor")) || (computerChoice.equals("rock") && playerChoice.equals("paper")) || computerChoice.equals("scissor") && playerChoice.equals("rock"))
                System.out.println("congrats you won");
            else
                System.out.println("computer won");

        }
    }
}

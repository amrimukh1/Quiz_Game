package SmartQuiz;

import java.util.Scanner;

public class Player {

    public String getName()

    {
        System.out.println("Please enter your name");
        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();

        System.out.println("Press enter to start the game");

         Scanner scanner2 = new Scanner(System.in);
         scanner2.nextLine();
         System.out.println("Let's continue!");
        return name;
    }
}

package SmartQuiz;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class QuizGame {

    public static void main(String[] args) {

        TimeController time = new TimeController();
        MenuController menuController = new MenuController();
        SelectCategory selectFile = new SelectCategory();
        Player player = new Player();
        String player_name;

        // Record the start time
        time.recordStartTime();

        QuestionReader reader = new QuestionReader();
        List<Question> questions = new ArrayList<Question>();

        // reading the rules
        menuController.printRules();

        System.out.println("Enter your name ");
        player_name = player.getName();

        menuController.printAvailableCategories();

        // Read the integer input - public List<Question> selectCategory(int input)
        Scanner scanner = new Scanner(System.in);
        int integerInput = Integer.parseInt(scanner.nextLine());
        questions = selectFile.readFromTxtFile(integerInput);

        GameController gameController = new GameController(questions);
        gameController.runQuizAndCalculateTime();

        System.out.println("Quiz completed, " + player_name + " ! Your score is: " + gameController.score
                + "! Your total time in minutes: " + time.recordEndTime() + " minutes");
    }

}

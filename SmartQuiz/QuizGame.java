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
        
        // Record the start time
        time.recordStartTime();

        gameController.runQuizAndCalculateTime();

        MyTimeDataClass totalDuration = time.recordEndTime();

        System.out.println("Quiz completed, " + player_name + " ! Your score is: " + gameController.score
                + "! Your total time: " + totalDuration.getMinutes() + " minutes and " + totalDuration.getSeconds() + " seconds.");
    }

}

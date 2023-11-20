import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    private static final int QUESTION_TIME_LIMIT = 20; // Time limit for each question in seconds
    private static Timer timer;

    final double MAX_SCORE = 20;
    

    public static void main(String[] args) {
        QuizGame game = new QuizGame();

        List<Question> questions = readQuestionsFromFile("sports.txt");

        int score = 0;
        double totalTime = 0.0;

       
        for (Question q : questions) {        

            startTimer(QUESTION_TIME_LIMIT);//This starts a timer for the current question with the specified time limit.
            displayQuestion(q);

           String userAnswer = getUserAnswer();
                System.out.println("User Answer is: " + userAnswer);
                stopTimer();//This stops the timer after the user has provided an answer.

                double timeRemaining = timerTask.getCounter();
                    
         
                if (userAnswer.equalsIgnoreCase(q.correctAnswer)) {
                    double points = game.calculatePoints(timeRemaining);
                    System.out.println("Correct! Time remaining: " + timeRemaining + " seconds. You earned " + points + " points.\n");
                      //System.out.println("Correct!\n");
                    score += points;
                } else {
                    System.out.println("Oops. The correct answer is: " + q.correctAnswer + "\n");
                }

                // Move to the next question automatically if the user didn't answer within the time limit
            if (timerTask.getCounter() <= 0) {
                System.out.println("Time's up! Moving to the next question.\n");
            }
            
            totalTime += timeRemaining;

            }


        System.out.println("Quiz completed. Your score is: " + score + " out of " + questions.size() * game.MAX_SCORE);
    }

    private static void startTimer(int seconds) {
        timerTask = new CustomTimerTask(seconds);
        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }

    private static void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private static List<Question> readQuestionsFromFile(String filename) {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                String question = line;
                List<String> choices = new ArrayList<>();
                String correctAnswer = null;

                for (int i = 0; i < 4; i++) {
                    choices.add(br.readLine());
                }

                correctAnswer = br.readLine().substring("Answer:   ".length()).trim();
                 // System.out.println("I am in readQuestionsFromFile and the Correct answer is :" + correctAnswer);

                questions.add(new Question(question, choices, correctAnswer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private static void displayQuestion(Question question) {
        System.out.println(question.question);
        for (int i = 0; i < question.choices.size(); i++) {
            System.out.println(question.choices.get(i));
        }
    }

    private static String getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer (A, B, C, or D): ");
        return scanner.nextLine().trim().toUpperCase();
    }

     //calculates points based on the time remaining.
    // private double calculatePoints(double timeRemaining) {
    //     if (timeRemaining >= 11) {
    //         return MAX_SCORE;
    //     } else if (timeRemaining > 0) {
    //         return MAX_SCORE / 2;
    //     } else {
    //         return 0.0;
    //     }
    // }

    private double calculatePoints(double timeRemaining) {
        return timeRemaining;
    }

    static CustomTimerTask timerTask;
    //This is a nested class that extends TimerTask and is used to define the behavior of the timer.

    static class CustomTimerTask extends TimerTask {
        private int counter;
        
        public CustomTimerTask(int seconds) {
            this.counter = seconds;
        }

        @Override
        public void run() {
            if (counter > 0) {
                counter--;
            }
        }

        public int getCounter() {
            return counter;
        }
       
    }

       
    }





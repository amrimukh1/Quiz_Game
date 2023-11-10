import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class QuizGame {
    public static void main(String[] args) {
        List<Question> questions = readQuestionsFromFile("Science_quiz.txt");
        int score = 0;

        for (Question q : questions) {
            displayQuestion(q);
            String userAnswer = getUserAnswer();
            System.out.println("UserAnswer is : " + userAnswer);
            
            if (userAnswer.equalsIgnoreCase(q.correctAnswer)) {
              System.out.println("Comparing the string: " + userAnswer.equalsIgnoreCase(q.correctAnswer));
                System.out.println("Correct!\n");
                score++;
              } 
            else {
              System.out.println("Comparing the string: " + userAnswer.equalsIgnoreCase(q.correctAnswer));
                System.out.println("Incorrect. The correct answer is: " + q.correctAnswer + "\n");
            }
        }

        System.out.println("Quiz completed. Your score is: " + score + " out of " + questions.size());
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

                correctAnswer = br.readLine().substring("Answer:   ".length());
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
}

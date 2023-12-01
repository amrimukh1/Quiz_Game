package SmartQuiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionReader {
    public List<Question> readQuestionsFromFile(String filename) {
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

                questions.add(new Question(question, choices, correctAnswer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questions;
    }

    public void displayQuestion(Question question) {
        System.out.println(question.question);
        for (int i = 0; i < question.choices.size(); i++) {
            System.out.println(question.choices.get(i));
        }
    }

    public String getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your answer (A, B, C, or D): ");
        String answer = scanner.nextLine().trim().toUpperCase();

        return answer;
    }

}

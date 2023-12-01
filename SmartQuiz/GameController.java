package SmartQuiz;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    TimeController time = new TimeController();
    QuestionReader reader = new QuestionReader();
    private static final int QUESTION_TIME_LIMIT = 20;
    List<Question> questions = new ArrayList<Question>();
    int score = 0;

    GameController(List<Question> questions) {
        this.questions = questions;

    }

    public void runQuizAndCalculateTime() {
        for (Question q : questions) {

            time.startTimer(QUESTION_TIME_LIMIT);// This starts a timer for the current question with the specified time limit
                                                 
            reader.displayQuestion(q);

            String userAnswer = reader.getUserAnswer();
            System.out.println("User Answer is: " + userAnswer);
            time.stopTimer();

            double timeRemaining = time.timerTask.getCounter();

            // comparing userAnswer and correctAnswer comparingUserAnswerToCorrectAnswer()

            if (userAnswer.equalsIgnoreCase(q.correctAnswer)) {
                double points = time.calculatePoints(timeRemaining);
                System.out.println(
                        "Correct! Time remaining: " + timeRemaining + " seconds. You earned " + points + " points.\n");

                score += points;
            } else {
                System.out.println("Oops. The correct answer is: " + q.correctAnswer + "\n");
            }

            // Move to the next question automatically if the user didn't answer within the time limit
            
            if (time.timerTask.getCounter() <= 0) {
                System.out.println("Time's up! Moving to the next question.\n");
            }

        }

    }
}

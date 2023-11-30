import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Duration;
import java.time.Instant;

public class QuizGame {
    private static final int QUESTION_TIME_LIMIT = 20; // Time limit for each question in seconds
    //private static Timer timer;

    final double MAX_SCORE = 20;
    private long seconds;
    
    public static void main(String[] args) {

        QuizGame game = new QuizGame();
        DisplayTime time = new DisplayTime();

        //Record the start time
        Instant startTime = Instant.now();

        QuestionReader reader = new QuestionReader(); 
         List<Question> questions = new ArrayList<Question>();

         try (BufferedReader regler_reader = new BufferedReader(new FileReader("regler.txt"))) {
            // Read each line from the file
            String line;
            while ((line = regler_reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter choice ( 1-5 ) ");
        System.out.println("1. Muzic");
        System.out.println("2. Sport" );
        System.out.println("3. Historia");
        System.out.println("4. Vetenskap");
        System.out.println("5. All");
       
       //if (scanner_1.hasNextInt()) {
            // Read the integer input
            int integerInput = Integer.parseInt(scanner.nextLine());
            switch (integerInput) {
                case 1:
                {System.out.println("You chose Muzic , lets begin: ");
                    
                    
                    questions = reader.readQuestionsFromFile("musik.txt");
                    break;                             
                }
                    
                    
                case 2:
                {System.out.println("You chose Sport, lets begin: ");
                  questions = reader.readQuestionsFromFile("sports.txt");
                    break;
            
                 }
                     
                case 3:
                     {System.out.println("You chose History, lets begin: ");
                     questions = reader.readQuestionsFromFile("history.txt");
                    break;}
                case 4:
                   {System.out.println("You chose Science, lets begin: ");
                   questions = reader.readQuestionsFromFile("Science_quiz.txt");
                    break;}
                case 5:
                     {
                        System.out.println("You chose All the categories above, lets begin: ");
                        questions = reader.readQuestionsFromFile("All.txt");
                    break;}
                default:
                    System.out.println("Wrong choice, pls choose between 1-5");
                    break;
               
            }
            
  //  } else {
            
    //        System.out.println("Invalid input. Please enter a valid integer.");
     //     }
        
          
                  
    
        System.out.println("Please enter your name");
       
        Scanner sc = new Scanner(System.in);
         
        String name = sc.next();

         System.out.println("Press enter to start the game");

          Scanner scanner2 = new Scanner(System.in);
         scanner2.nextLine();
         System.out.println("Let's continue!");

      /*  scanner2.close();
         sc.close();
         scanner.close(); */ 
          
        int score = 0;
        //double totalTime = 0.0;

       
        for (Question q : questions) {        

            time.startTimer(QUESTION_TIME_LIMIT);//This starts a timer for the current question with the specified time limit.
            reader.displayQuestion(q);

           String userAnswer = reader.getUserAnswer();
                System.out.println("User Answer is: " + userAnswer);
                time.stopTimer();//This stops the timer after the user has provided an answer.

                double timeRemaining = time.timerTask.getCounter();
                    
         
                if (userAnswer.equalsIgnoreCase(q.correctAnswer)) {
                    double points = game.calculatePoints(timeRemaining);
                    System.out.println("Correct! Time remaining: " + timeRemaining + " seconds. You earned " + points + " points.\n");
                      //System.out.println("Correct!\n");
                    score += points;
                } else {
                    System.out.println("Oops. The correct answer is: " + q.correctAnswer + "\n");
                }

                // Move to the next question automatically if the user didn't answer within the time limit
            if (time.timerTask.getCounter() <= 0) {
                System.out.println("Time's up! Moving to the next question.\n");
            }
            
            //totalTime += timeRemaining;

            }
            //Record the endTime
            Instant endTime = Instant.now();
            Duration duration = Duration.between(startTime, endTime);
            game.seconds = duration.getSeconds();

            //convert the seconds into minutes divided by 60
            long minutes = game.seconds / 60;

            System.out.println("Quiz completed, "+ name + " ! Your score is: " + score + "! Your total time in minutes: " + minutes+ " minutes and in seconds: " +game.seconds+ "seconds");
    }

       /* private static String formatDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value);
    }*/
    

    private double calculatePoints(double timeRemaining) {
        return timeRemaining;
    }

       
    }





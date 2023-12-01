package SmartQuiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import SmartQuiz.Category.CategoryType;

public class MenuController {


    public void printAvailableCategories() {
        System.out.println("Enter choice (1-" + (Category.CategoryType.values().length + 1) + ") ");
        Category.CategoryType[] values = Category.CategoryType.values();
        int i = 1;
        for (Category.CategoryType c : values) {
            System.out.println(i + ". " + c.toString());
            i++;
        }
        System.out.println(i + ". " + "ALL");
    }

    public void printRules() {
        try (BufferedReader regler_reader = new BufferedReader(new FileReader("TextFiles/regler.txt"))) {
            // Read each line from the file
            String line;
            while ((line = regler_reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

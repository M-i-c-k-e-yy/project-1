import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class ESPgame {

    public static void main(String[] args) {
        try {
            // Create a scanner object for user input
            Scanner inputScanner = new Scanner(System.in);
            Random random = new Random();

            // Use the hardcoded filename (colors.txt)
            String filename = "colors.txt";

            // Create a File object using the string for the filename
            File file = new File(filename);

            // Check if the file exists
            if (!file.exists()) {
                System.out.println("Error: colors.txt file does not exist.");
                return;
            }

            // Scanner to read the colors file
            Scanner fileScanner = new Scanner(file);
            
            // Display the colors with corresponding numbers
            System.out.println("There are sixteen colors from the file:");
            int lineNumber = 1;
            while (fileScanner.hasNextLine() && lineNumber <= 16) {
                String color = fileScanner.nextLine();
                System.out.println(lineNumber + ". " + color);
                lineNumber++;
            }

            // Re-open the file to read again for random color selection
            fileScanner = new Scanner(new File(filename));

            int correctGuesses = 0;

            // Play 3 rounds of guessing
            for (int round = 1; round <= 3; round++) {
                System.out.println("\nRound " + round);
                System.out.println("I am thinking of a color. Choose it's corresponding number (1-16).");

                // Generate a random number between 1 and 16 (inclusive)
                int randomColorNumber = random.nextInt(16) + 1;
                String selectedColor = getColorByNumber(randomColorNumber, fileScanner);

                // Ask the user to select the number corresponding to the color
                int userNumberGuess = -1;

                // Ensure valid input from the user
                while (userNumberGuess < 1 || userNumberGuess > 16) {
                    System.out.print("Enter a number between 1 and 16: ");
                    if (inputScanner.hasNextInt()) {
                        userNumberGuess = inputScanner.nextInt();
                        inputScanner.nextLine(); // Consume the leftover newline
                    } else {
                        System.out.println("Invalid input. Please enter a number between 1 and 16.");
                        inputScanner.nextLine(); // Consume the invalid input
                    }
                }

                // Check if the user's number matches the random color number
                if (userNumberGuess == randomColorNumber) {
                    System.out.println("Correct! I was thinking of " + selectedColor + ".");
                    correctGuesses++;
                } else {
                    System.out.println("Wrong. I was thinking of " + selectedColor + ".");
                }

                // Reset the file scanner for the next round
                fileScanner = new Scanner(new File(filename));
            }

            // End of the game, display the result
            System.out.println("\nGame Over.");
            System.out.println("You guessed " + correctGuesses + " out of 3 colors correctly.");

            // Prompt for user information
            System.out.print("Enter your name: ");
            String userName = inputScanner.nextLine();

            System.out.print("Describe yourself: ");
            String description = inputScanner.nextLine();

            System.out.print("Enter the due date (MM/DD): ");
            String dueDate = inputScanner.nextLine();

            // Display the user information at the end
            System.out.println("\nUser Name: " + userName);
            System.out.println("Description: " + description);
            System.out.println("Due Date: " + dueDate);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. Please make sure the colors.txt file is in the correct directory.");
        }
    }

    // Method to get the color by the corresponding number from the file
    public static String getColorByNumber(int number, Scanner fileScanner) {
        int lineNumber = 1;
        while (fileScanner.hasNextLine()) {
            String color = fileScanner.nextLine();
            if (lineNumber == number) {
                return color; // Return the color corresponding to the number
            }
            lineNumber++;
        }
        return null; // In case something goes wrong (should not happen)
    }
}
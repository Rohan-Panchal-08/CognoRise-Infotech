import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        String[] wordList = {"java", "python", "javascript", "hangman", "coding", "developer"};
        Random random = new Random();
        String chosenWord = wordList[random.nextInt(wordList.length)];
        int maxGuesses = 6;
        int incorrectGuesses = 0;
        char[] displayWord = new char[chosenWord.length()];
        ArrayList<Character> guessedLetters = new ArrayList<>();

        for (int i = 0; i < displayWord.length; i++) {
            displayWord[i] = '_';
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hangman!");

        while (incorrectGuesses < maxGuesses) {
            System.out.println("\nWord: " + String.valueOf(displayWord));
            System.out.println("Guessed Letters: " + guessedLetters);
            System.out.println("Incorrect Guesses Remaining: " + (maxGuesses - incorrectGuesses));
            drawHangman(incorrectGuesses);
            System.out.print("Enter a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue;
            }

            guessedLetters.add(guess);

            if (chosenWord.indexOf(guess) >= 0) {
                System.out.println("Correct guess!");
                for (int i = 0; i < chosenWord.length(); i++) {
                    if (chosenWord.charAt(i) == guess) {
                        displayWord[i] = guess;
                    }
                }
            } else {
                System.out.println("Incorrect guess.");
                incorrectGuesses++;
            }

            if (String.valueOf(displayWord).equals(chosenWord)) {
                System.out.println("\nCongratulations! You've guessed the word: " + chosenWord);
                break;
            }
        }

        if (incorrectGuesses == maxGuesses) {
            System.out.println("\nYou've run out of guesses. The word was: " + chosenWord);
            drawHangman(incorrectGuesses);
        }

        scanner.close();
    }

    public static void drawHangman(int incorrectGuesses) {
        switch (incorrectGuesses) {
            case 0:
                System.out.println(" +---+\n     |\n     |\n     |\n    ===");
                break;
            case 1:
                System.out.println(" +---+\n O   |\n     |\n     |\n    ===");
                break;
            case 2:
                System.out.println(" +---+\n O   |\n |   |\n     |\n    ===");
                break;
            case 3:
                System.out.println(" +---+\n O   |\n/|   |\n     |\n    ===");
                break;
            case 4:
                System.out.println(" +---+\n O   |\n/|\\  |\n     |\n    ===");
                break;
            case 5:
                System.out.println(" +---+\n O   |\n/|\\  |\n/    |\n    ===");
                break;
            case 6:
                System.out.println(" +---+\n O   |\n/|\\  |\n/ \\  |\n    ===");
                break;
        }
    }
}

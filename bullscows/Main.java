package bullscows;

import javax.xml.stream.events.StartDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int numberOfDigits = 0;

        System.out.println("Please, enter the secret code's length:");

        String length = r.readLine();
        if (length.matches("\\d+")) {
            numberOfDigits = Integer.parseInt(length);
            if (numberOfDigits <= 0 || numberOfDigits > 36) {
                System.out.printf("Error: maximum number of possible length in the code is 36.");
                System.exit(0);
            }
        } else {
            System.out.printf("Error: %s isn't a valid number.",length);
            System.exit(0);
        }
        int range = 0;
        String Range;
        System.out.println("Input the number of possible symbols in the code:");
        Range = r.readLine();
        if (Range.matches("\\d+")) {
            range = Integer.parseInt(Range);
            if (range > 36) {
                System.out.printf("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
        } else {
            System.out.printf("Error: %s isn't a valid number.",Range);
            System.exit(0);
        }
        if ((range - numberOfDigits) < 0) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", numberOfDigits, range);
            System.exit(0);
        }
        CodeGenerator secret = new CodeGenerator(numberOfDigits, range);
        System.out.println("Okay, let's start a game!");
        int turn = 1;
        while (true) {
            int bulls = 0;
            int cows = 0;
            char[] secretCharacters;
            System.out.printf("Turn %d:\n", turn);
            String guess = r.readLine();
            secretCharacters = guess.toCharArray();
            for (int i = 0; i < numberOfDigits; i++) {
                for (int j = 0; j < numberOfDigits; j++) {
                    if (secretCharacters[i] == secret.charAt(j) && i == j) bulls++;
                    if (secretCharacters[i] == secret.charAt(j) && i != j) cows++;
                }
            }
            String outputFormat = "";
            String cowsGrade = "";
            String bullsGrade;
            if (cows != 0) {
                cowsGrade = cows + " cow(s)";
                outputFormat = String.format("%s", cowsGrade);
            }
            if (bulls != 0) {
                bullsGrade = bulls + " bull(s)";
                outputFormat = String.format("%s", bullsGrade);
                if (cows != 0)
                    outputFormat = String.format("Grade: %s and %s", bullsGrade, cowsGrade);
            }
            if (bulls == 0 && cows == 0) outputFormat = "None";
            System.out.println(outputFormat);
            if (bulls == numberOfDigits) {
                outputFormat = "Congratulations! You guessed the secret code.";
                System.out.println(outputFormat);
                break;
            }
        }
    }
}

package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static String alphaNumericStr = "0123456789abcdefghijklmnopqrstuvwxyz";

    // generate secret number anonymization
    static String generateStars(int secretCodeLength) {
        return "*".repeat(Math.max(0, secretCodeLength));
    }

    static String RandGeneratedStr(int secretCodeLength, int symbolsLength) {
        Random random = new Random();
        // a list of characters to choose from in form of a string

        char[] chars = symbolsLength < 36 ? alphaNumericStr.substring(0, symbolsLength).toCharArray() : alphaNumericStr.toCharArray();

        // creating a StringBuffer size of AlphaNumericStr
        StringBuilder s = new StringBuilder(secretCodeLength);

        for (int i = 0; i < secretCodeLength; i++) {
            int index = random.nextInt(chars.length - i - 1);
            // Simple swap
            char a = chars[i + index];
            chars[i + index] = chars[i];
            chars[i] = a;
            s.append(a);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        /*
         * Stage 6
         */
        int countTurns = 1;
        boolean flagEnd = false;

        // input length of secret number
        String str1 = null;
        int lengthOfSecretNumber = 0;
        try {
            System.out.println("Please, enter the secret code's length:");
            str1 = scanner.nextLine();
            lengthOfSecretNumber = Integer.parseInt(str1);

        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", str1);
            System.exit(0);
        }

        if (lengthOfSecretNumber > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique chars.\n", lengthOfSecretNumber);
        }

        // input length of possible symbols
        String str2 = null;
        int lengthOfPossibleSymbols = 0;
        try {
            System.out.println("Input the number of possible symbols in the code:");
            str2 = scanner.nextLine();
            lengthOfPossibleSymbols = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", str2);
            System.exit(0);
        }

        if (lengthOfPossibleSymbols < lengthOfSecretNumber) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", lengthOfPossibleSymbols, lengthOfSecretNumber);
            System.exit(0);
        }

        // generate random secret number
        String secretNumber = RandGeneratedStr(lengthOfSecretNumber, lengthOfPossibleSymbols);

        // print info
        if (lengthOfPossibleSymbols == 1) {
            System.out.println("Error");
            System.exit(0);
        } else if (lengthOfPossibleSymbols <= 10) {
            System.out.printf("The secret is prepared: %s (0-%s).\n", generateStars(lengthOfSecretNumber), alphaNumericStr.charAt(lengthOfPossibleSymbols - 1));
        } else if (lengthOfPossibleSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%s).\n", generateStars(lengthOfSecretNumber), alphaNumericStr.charAt(lengthOfPossibleSymbols - 1));
        }

        System.out.println("Okay, let's start a game!");

        while (!flagEnd) {
            System.out.println("Turn " + countTurns++ + ":");
            String line = scanner.next();

            int countBulls = 0;
            int countCows = 0;

            for (int i = 0; i < line.length(); i++) {
                if (line.contains(String.valueOf(secretNumber.charAt(i)))) {
                    countCows++;
                }
                if (secretNumber.charAt(i) == line.charAt(i)) {
                    countCows--;
                    countBulls++;
                }
            }

            String bull = countBulls > 1 ? "bulls" : "bull";
            String cow = countCows > 1 ? "cows" : "cow";

            if (countBulls == 0 && countCows == 0) {
                System.out.println("Grade: None.");
            } else if (countCows != 0 && countBulls == 0) {
                System.out.printf("Grade: %d %s.\n", countCows, cow);
            } else if (countCows == 0 && countBulls != lengthOfSecretNumber) {
                System.out.printf("Grade: %d bulls\n", countBulls);
            } else if (countBulls == lengthOfSecretNumber) {
                System.out.printf("Grade: %d bulls\n", countBulls);
                System.out.println("Congratulations! You guessed the secret code.");
                flagEnd = true;
            } else {
                System.out.printf("Grade: %d %s and %d %s.\n", countBulls, bull, countCows, cow);
            }
        }
    }
}


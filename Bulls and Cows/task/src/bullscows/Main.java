package bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
         * Stage 4
         */
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int countTurns = 1;
        boolean flagEnd = false;

        System.out.println("Please, enter the secret code's length:");
        int amountOfSecretNumber = scanner.nextInt();

        UniqueRng rng = new UniqueRng(amountOfSecretNumber);
        while (rng.hasNext()) {
            sb.append(rng.next());
        }

        if (amountOfSecretNumber > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.\n", amountOfSecretNumber);
        } /*else {
            System.out.printf("The random secret number is: %s\n", sb);
        }*/

        System.out.println("Okay, let's start a game!");

        while (!flagEnd) {
            int countCows = 0;
            int countBulls = 0;
            System.out.println("Turn " + countTurns++ + ":");
            String line = scanner.next();

            for (int i = 0; i < sb.length(); i++) {
                for (int j = 0; j < line.toCharArray().length; j++) {
                    if (sb.charAt(i) == line.charAt(j)) {
                        countCows++;
                        if (i == j) {
                            countBulls++;
                            countCows--;
                        }
                    }
                }
            }

            String bull = countBulls > 1 ? "bulls" : "bull";
            String cow = countCows > 1 ? "cows" : "cow";

            if (countBulls == 0 && countCows == 0) {
                System.out.println("Grade: None.\n");
            } else if (countCows != 0 && countBulls == 0) {
                System.out.printf("Grade: %d %s.\n", countCows, cow);
            } else if (countBulls == amountOfSecretNumber) {
                System.out.printf("Grade: %d bulls\n", countBulls);
                System.out.println("Congratulations! You guessed the secret code.");
                flagEnd = true;
            } else {
                System.out.printf("Grade: %d %s and %d %s.\n", countBulls, bull, countCows, cow);
            }
        }
    }
}

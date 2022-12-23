package bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
         * Stage 3
         */
        Scanner scanner = new Scanner(System.in);
        //  String line = scanner.nextLine();
        int amountOfSecretNumber = scanner.nextInt();
        StringBuilder sb = new StringBuilder();

        UniqueRng rng = new UniqueRng(amountOfSecretNumber);
        while (rng.hasNext()) {
            sb.append(rng.next());
        }

        if (amountOfSecretNumber > 10) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", amountOfSecretNumber);
        } else {
            System.out.printf("The random secret number is: %s", sb);
        }

/*        int countCow = 0;
        int countBull = 0;

        for (int i = 0; i < sb.length(); i++) {
            for (int j = 0; j < line.toCharArray().length; j++) {
                if (sb.charAt(i) == line.charAt(j)) {
                    countCow++;
                    if (i == j) {
                        countBull++;
                        countCow--;
                    }
                }
            }
        }

        if (countBull == 0 && countCow == 0) {
            System.out.printf("Grade: None. The secret code is %s.", sb);
        } else if (countCow != 0 && countBull == 0) {
            System.out.printf("Grade: %d cow(s). The secret code is %s.", countCow, sb);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s.", countBull, countCow, sb);
        }*/
    }
}

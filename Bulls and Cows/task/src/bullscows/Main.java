package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
         * Stage 2
         */
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        String secretCode = "9305";
        String[] secretCodeArr = secretCode.split("");

        int countCow = 0;
        int countBull = 0;

        for (int i = 0; i < secretCodeArr.length; i++) {
            for (int j = 0; j < line.toCharArray().length; j++) {
                if (secretCodeArr[i].matches(String.valueOf(line.charAt(j)))) {
                    countCow++;
                    if (i == j) {
                        countBull++;
                        countCow--;
                    }
                }
            }
        }

        if (countBull == 0 && countCow == 0) {
            System.out.printf("Grade: None. The secret code is %s.", secretCode);
        } else if (countCow != 0 && countBull == 0) {
            System.out.printf("Grade: %d cow(s). The secret code is %s.", countCow, secretCode);
        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s.", countBull, countCow, secretCode);
        }

    }
}

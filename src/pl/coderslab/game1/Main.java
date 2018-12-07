package pl.coderslab.game1;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Random r = new Random();
        guessANumber(r.nextInt(100)+1);
        }


    static void guessANumber (int prizeNumber) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please pick a number");
        int guessedNumber;

        while (true) {
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("You must enter a number");
            }
            guessedNumber = scan.nextInt();

            if (guessedNumber > prizeNumber) {
                System.out.println("Too big!");
            } else if (guessedNumber < prizeNumber) {
                System.out.println("Too small!");
            } else {
                System.out.println("This is it!");
                break;
            }
        }
    }

}

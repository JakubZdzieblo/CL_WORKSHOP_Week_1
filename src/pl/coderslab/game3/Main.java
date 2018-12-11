package pl.coderslab.game3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        int min = 0;
        int max = 1000;
        int guess;

        System.out.println("Think up a number from 0 - 1000 range and I will guess it in 10 tries or less");

        while (true) {
            guess = ((max-min)/2) + min;
            System.out.println("My guess is " + guess);
            System.out.println("Is it correct? Y/N");
            if(yesOrNo()){
                System.out.println("I won!");
                break;
            }
            System.out.println("Is my guess too big? Y/N");
            if(yesOrNo()){
                max = guess;
            } else {
                System.out.println("Is it too low then? Y/N");
                if(yesOrNo()) {
                    min = guess;
                } else {
                    System.out.println("Don't cheat!");
                }
            }

        }


    }

    private static boolean yesOrNo () {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String answer = scan.nextLine();
            answer = answer.toLowerCase();
            if ((answer.equals("yes")) || (answer.equals("y"))) {
                return true;
            } else if ((answer.equals("no")) || (answer.equals("n"))) {
                return false;
            } else {
                System.out.println("Please answer yes or no");
            }
        }

    }



}

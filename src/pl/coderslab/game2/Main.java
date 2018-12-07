package pl.coderslab.game2;


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int[] playerNumbers = input6LottoNumbers();
        System.out.println("Here are your LOTTO picks:");
        System.out.println(Arrays.toString(playerNumbers));
        int[] draw = lotto6Numbers();
        System.out.println("Here's the draw:");
        System.out.println(Arrays.toString(draw));
        int result = lottoResultCheck(playerNumbers, draw);
        if (result < 3) {
            System.out.println("Sorry, no prize this time! " + result + " hits.");
        } else {
            System.out.println("You have won! " + result + " numbers are correct!");
        }

    }

    static int[] input6LottoNumbers() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter 6 different numbers from 1-49 range");

        int[] numbers = new int[6];
        int i = 0;

        while (i < 6) {
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("You must enter a number");
            }
            int newNumber = scan.nextInt();
            if ((newNumber > 49) || (newNumber < 1)) {
                System.out.println("Number out of range");
            } else if (checkIfNumberIsInArray(numbers, newNumber)) {
                System.out.println("Number already used, pick another");
            } else {
                numbers[i] = newNumber;
                i++;
                System.out.println("Number " + i + " is " + newNumber);
                }
            }

        Arrays.sort(numbers);

        return numbers;

    }

    static int[] lotto6Numbers() {

        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        Collections.shuffle(Arrays.asList(arr));

        int[] result = new int[6];

        for (int i = 0; i < 6; i++) {
            result[i] = arr[i];
        }

        Arrays.sort(result);

        return result;
    }

    static boolean checkIfNumberIsInArray (int[] arr, int number) {
        for (int i = 0; i < arr.length; i++) {
            if (number == arr[i]) {
                return true;
            }
        }
        return false;
    }

    static int lottoResultCheck (int[] playerArray, int[] drawArray) {
        int numbersHitCount = 0;

        if (playerArray.length != drawArray.length) {
            System.out.println("Arrays must be of the same size");
            return -1;
        }

        for (int i=0; i < playerArray.length; i++) {
            if (checkIfNumberIsInArray(drawArray, playerArray[i])) {
                numbersHitCount ++;
                }
        }
        return numbersHitCount;
    }
}

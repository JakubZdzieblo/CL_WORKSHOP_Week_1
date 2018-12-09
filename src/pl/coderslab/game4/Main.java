package pl.coderslab.game4;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        System.out.println("2 x D6 + 3: " + diceRoll(" 2d6 + 3 "));
        System.out.println("1 x D100: " + diceRoll("D100"));
        System.out.println("5 x D12 - 5: " + diceRoll("5d12-5"));
        System.out.println("No dice type: ");
        System.out.println(diceRoll("12+5"));
        System.out.println("Bad format: ");
        System.out.println(diceRoll("abcd"));

    }

    static int diceRoll(String diceCode) {
        int modIndex, mod, rolls, diceType, result=0;
        diceCode = diceCode.toUpperCase().trim();
        int dIndex = diceCode.indexOf('D');
        if (dIndex == -1) {
            System.out.println("Dice type not found");
            return -1;
        }

        try {
            if (diceCode.contains("+")) {
                modIndex = diceCode.indexOf('+');
                mod = Integer.parseInt(diceCode.substring(modIndex +1).trim());
            } else if (diceCode.contains("-")) {
                modIndex = diceCode.indexOf('-');
                mod = -Integer.parseInt(diceCode.substring(modIndex +1).trim());
            } else {
                modIndex = diceCode.length();
                mod = 0;
            }
            if (dIndex == 0) {
                rolls = 1;
            } else {
                rolls = Integer.parseInt(diceCode.substring(0, dIndex).trim());
            }
            diceType = Integer.parseInt(diceCode.substring(dIndex+1, modIndex).trim());

        } catch (NumberFormatException e) {
            System.out.println("Format error: use the xDy+z format (x and z optional)");
            return -1;
        }

        Random r = new Random();

        for (int i = 1; i <= rolls; i++) {
            result += r.nextInt(diceType)+1;
        }

        return result + mod;
    }

}

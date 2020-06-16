package org.vtb;

/**
 * Created by pgalkin on 2/26/2016.
 * contains main method
 */

import java.util.Random;

public class GameProcessor {
    public static String bulls;
    public static String cows;
    public static String congratsString = "1 Emule";
    /**
     *Main methods run bu JVM and prints message
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int tries = 0;
        boolean win = false;

        Hider newGame = new Hider();
        String sHidden = newGame.generator();

        while(!win){
            tries = ++tries;
            block: {
                Comparator newValue = new Comparator();
                if(newValue.attemptValue == 0) {
                    System.out.println("hidden value is " + sHidden);
                    break;
                }
                if(newValue.sAttemptValue.length() !=4){
                    System.out.println("You entered " + newValue.sAttemptValue.length() + "-digits number");
                    break block;
                }

                win = isWin(newValue.sAttemptValue, sHidden, newValue);
                if(win)
                    System.out.println("Number of tries: " + tries);
            }

        }
    }

    public static boolean isWin(String attempt, String hidden, Comparator c){
        if(attempt.equalsIgnoreCase(hidden)){
            congratsString = "4 BULLS!!!";
            System.out.println(congratsString);
            return true;

        }
        else {
            bulls = c.getBulls(hidden) + " BULLS";
            cows = c.getCows(hidden) + " COWS";
            System.out.println(bulls);
            System.out.println(cows);
            return false;
        }
    }
}

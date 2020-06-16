package org.vtb;

/**
 * Created by pgalkin on 3/21/2016.
 */

import java.util.Random;

public class Hider {

    public String getTestHiddenValue() {
        return testHiddenValue=generator();
    }
    public String testHiddenValue;

    /**
     * @return random value from 1000 to 9999 converted to String
     */
    public String generator(){
        String a;
        String hiddenValue;
        Random randNumber = new Random();
        hiddenValue = Integer.toString(randNumber.nextInt(9));
        a = Integer.toString(randNumber.nextInt(9));
        while(hiddenValue.length()<4){
            if(hiddenValue.contains(a)) a = Integer.toString(randNumber.nextInt(9));
            else hiddenValue = hiddenValue +a;
        }
        // System.out.println("hidden value is " + hiddenValue);
        return hiddenValue;
    }

}


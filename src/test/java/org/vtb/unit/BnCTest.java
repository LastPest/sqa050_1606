package org.vtb.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.vtb.Comparator;
import org.vtb.Hider;

public class BnCTest {

    @RepeatedTest(4)
    void test4Digits(){
        Hider hider = new Hider();
        String hiddenValue = hider.generator();
        Assertions.assertEquals(4, hiddenValue.length());
    }

    @Test
    void testCows(){
        Comparator comparator = new Comparator();
        comparator.setTestAttemptValue(1234);
        Assertions.assertEquals(0, comparator.getCows("1234"));
    }
}

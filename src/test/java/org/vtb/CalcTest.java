package org.vtb;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calculator test 11")
public class CalcTest {
    static Calculator calculator;
    int numb;


    @BeforeAll
    static void setUpCalc(){
        calculator = new Calculator();
    }

    @BeforeEach
    void beforee(){
        numb = calculator.sum(4, 7);
    }

    @Test
//    @Disabled
    @Tag("fail")
    @DisplayName("this test fails")
    public void firstTest(){
        int x = 4;
        int y = 12;
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        assertAll(
                () ->   assertEquals(14, x+y),
                () ->   assertTrue(x<y)
        );
//        fail("optional message");
    }

    @RepeatedTest(3)
    @Order(3)
    @Tag("pass")
    @DisplayName("check method sum")
    void sumTest(){
        assertEquals(11, numb);
    }

    @Test
    @Tag("pass")
    @DisplayName("check method divide")
    void divideTest(){
        assertEquals(2, calculator.divide(8, 4));
    }

    @Test
    @Order(2)
    @Tag("pass")
    @DisplayName("check method multiply")
    void multiplyTest(){
        assertEquals(32, calculator.multiply(8, 4));
    }


}

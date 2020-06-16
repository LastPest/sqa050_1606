package org.vtb;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class CalcParamTest {
    static Calculator calculator;

    @BeforeAll
    static void setUpCalc(){
        calculator = new Calculator();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 2})
    void checkSum(int parameter){
        assertEquals(parameter, calculator.sum(1,3));
    }

    @ParameterizedTest
    @CsvSource(value = {"1#3#4", "2#6#8", "0#0#0"}, delimiter = '#')
    void checkSum1(int a, int b, int result){
        assertEquals(result, calculator.sum(a,b));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", delimiter = ',', numLinesToSkip = 1)
    void checkSum2(int a, int b, int result){
        assertEquals(result, calculator.sum(a,b));
    }

    @ParameterizedTest
    @EnumSource(value = CalcSumData.class, names = {"dataset2"}, mode = EnumSource.Mode.EXCLUDE)
    void checkMultiply(CalcSumData data){
        assertEquals(data.getResult(), calculator.multiply(data.getA(),data.getB()));
    }

    @ParameterizedTest
    @EnumSource(value = CalcSumData.class, names = {".*1", ".*3"}, mode = EnumSource.Mode.MATCH_ANY)
    void checkMultiply1(CalcSumData data){
        assertEquals(data.getResult(), calculator.multiply(data.getA(),data.getB()));
    }

    @ParameterizedTest
    @MethodSource("getData")
    void checkDivide(int a, int b, String result){
        assertEquals(Integer.parseInt(result), calculator.divide(a,b));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void checkDivide1(int a, int b, String result){
        assumingThat(b!=0,
            ()-> assertEquals(Integer.parseInt(result), calculator.divide(a,b)));

    }

    @ParameterizedTest
    @ArgumentsSource(value = ArgumentsProvider.class)
    void checkDivide2(int a, int b, String result){
        assertEquals(Integer.parseInt(result), calculator.divide(a,b));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void checkDivide3(int a, int b, String result){
        assertThrows(ArithmeticException.class,
                ()-> calculator.divide(a,b));
    }

    static Stream<Arguments> getData(){
        return Lesson.dataProvider11();
    }

    private static Stream<Arguments> dataProvider(){
        return Stream.of(
                Arguments.of(4,2,"2"),
                Arguments.of(6,0,"3"),
                Arguments.of(10,0,"5")
                );
    }
}

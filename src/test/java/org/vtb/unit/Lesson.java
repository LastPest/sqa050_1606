package org.vtb.unit;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Lesson {

    public static Stream<Arguments> dataProvider11(){
        return Stream.of(
                Arguments.of(4,2,"2"),
                Arguments.of(6,2,"3"),
                Arguments.of(10,2,"5")
        );
    }

}

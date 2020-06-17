package org.vtb.unit;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ArgumentsProvider implements org.junit.jupiter.params.provider.ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(4,2,"2"),
                Arguments.of(6,2,"3"),
                Arguments.of(10,2,"5")
        );
    }
}

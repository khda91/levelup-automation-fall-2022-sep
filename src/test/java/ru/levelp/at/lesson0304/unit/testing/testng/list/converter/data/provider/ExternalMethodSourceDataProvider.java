package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class ExternalMethodSourceDataProvider {

    public static Stream<Arguments> methodSourceDataProvider() {
        return Stream.of(
            Arguments.of(5, 120),
            Arguments.of(3, 6),
            Arguments.of(7, 5040),
            Arguments.of(4, 24)
        );
    }
}

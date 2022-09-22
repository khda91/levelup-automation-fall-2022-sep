package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.lesson0304.unit.testing.Factorial;
import java.util.stream.Stream;

public class MethodSourceDataProviderIT {

    protected Factorial factorial;

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        factorial = new Factorial();
    }

    @ParameterizedTest
    @MethodSource("methodSourceDataProvider")
    public void methodSourceTest(long num, long expectedOutput) {
        System.out.println(this.getClass().getName() + " methodSourceTest");
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    static Stream<Arguments> methodSourceDataProvider() {
        return Stream.of(
            Arguments.of(5, 120),
            Arguments.of(3, 6),
            Arguments.of(7, 5040)
        );
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        factorial = null;
    }
}

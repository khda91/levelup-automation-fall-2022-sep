package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.lesson0304.unit.testing.Factorial;

public class ExternalMethodSourceDataProviderIT {

    protected Factorial factorial;

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        factorial = new Factorial();
    }

    @ParameterizedTest
    @MethodSource("ru.levelp.at.lesson0304.unit.testing.testng.list.converter"
        + ".data.provider.ExternalMethodSourceDataProvider#methodSourceDataProvider")
    public void methodSourceTest(long num, long expectedOutput) {
        System.out.println(this.getClass().getName() + " methodSourceTest");
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        factorial = null;
    }
}

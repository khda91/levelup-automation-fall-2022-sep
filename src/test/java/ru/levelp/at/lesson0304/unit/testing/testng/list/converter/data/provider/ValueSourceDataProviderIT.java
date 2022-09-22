package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.levelp.at.lesson0304.unit.testing.Factorial;

public class ValueSourceDataProviderIT {

    protected Factorial factorial;

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        factorial = new Factorial();
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1})
    public void valueSourceTest(long num) {
        System.out.println(this.getClass().getName() + " valueSourceTest");
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(1);
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        factorial = null;
    }
}

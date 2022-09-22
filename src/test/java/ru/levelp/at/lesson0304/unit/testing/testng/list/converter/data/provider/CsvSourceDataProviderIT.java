package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import ru.levelp.at.lesson0304.unit.testing.Factorial;

public class CsvSourceDataProviderIT {

    protected Factorial factorial;

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        factorial = new Factorial();
    }

    @ParameterizedTest
    @CsvSource({"1,1",
        "5,120"//,
        /*"6, 725"*/})
    public void csvSourceTest(long num, long expectedOutput) {
        System.out.println(this.getClass().getName() + " methodSourceTest");
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ru/levelp/at/lesson0304/unit/testing/csv_source_data_provider.csv")
    public void csvFileSourceTest(long num, long expectedOutput) {
        System.out.println(this.getClass().getName() + " csvFileSourceTest");
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        factorial = null;
    }
}

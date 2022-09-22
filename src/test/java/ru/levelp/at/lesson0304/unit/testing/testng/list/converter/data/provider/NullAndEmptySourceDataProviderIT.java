package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.provider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;
import java.util.List;

public class NullAndEmptySourceDataProviderIT {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    protected ListConverter converter;

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        converter = new ListConverter();
    }

    @ParameterizedTest
//    @EmptySource
//    @NullSource
    @NullAndEmptySource
    public void inputListIsNullTestNgAssertTest(List<String> input) {
        System.out.println(this.getClass().getName() + " inputListIsNullTestNgAssertTest");
        List<String> actualOutput = converter.removeLetterS(input);
        org.junit.jupiter.api.Assertions.assertTrue(actualOutput.isEmpty());
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        converter = null;
    }
}

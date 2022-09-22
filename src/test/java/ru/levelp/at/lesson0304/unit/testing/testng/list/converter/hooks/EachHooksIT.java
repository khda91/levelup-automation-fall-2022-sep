package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;
import java.util.Collections;
import java.util.List;

public class EachHooksIT {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    private ListConverter converter;

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        converter = new ListConverter();
    }

    @Test
    public void positiveRemoveLetterS() {
        System.out.println(this.getClass().getName() + " positiveRemoveLetterS");
        List<String> actualOutput = converter.removeLetterS(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }

    @Test
    public void inputListIsNullAssertjTest() {
        System.out.println(this.getClass().getName() + " inputListIsNullAssertjTest");
        List<String> actualOutput = converter.removeLetterS(null);
        // assertj
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @Test
    public void inputListIsNullTestNgAssertTest() {
        System.out.println(this.getClass().getName() + " inputListIsNullTestNgAssertTest");
        List<String> actualOutput = converter.removeLetterS(null);
        // assert junit jupiter
        org.junit.jupiter.api.Assertions.assertTrue(actualOutput.isEmpty());
    }

    @Test
    public void inputListIsEmptyTest() {
        System.out.println(this.getClass().getName() + " inputListIsEmptyTest");
        List<String> actualOutput = converter.removeLetterS(Collections.emptyList());
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        converter = null;
    }
}

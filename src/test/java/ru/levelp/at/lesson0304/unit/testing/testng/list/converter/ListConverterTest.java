package ru.levelp.at.lesson0304.unit.testing.testng.list.converter;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;

public class ListConverterTest {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    @Test
    public void positiveRemoveLetterS() {
        ListConverter converter = new ListConverter();
        List<String> actualOutput = converter.removeLetterS(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }

    @Test
    public void inputListIsNullAssertjTest() {
        ListConverter converter = new ListConverter();
        List<String> actualOutput = converter.removeLetterS(null);
        // assertj
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @Test
    public void inputListIsNullTestNgAssertTest() {
        ListConverter converter = new ListConverter();
        List<String> actualOutput = converter.removeLetterS(null);
        // assert testng
        Assert.assertTrue(actualOutput.isEmpty());
    }

    @Test
    public void inputListIsEmptyTest() {
        ListConverter converter = new ListConverter();
        List<String> actualOutput = converter.removeLetterS(Collections.emptyList());
        Assertions.assertThat(actualOutput).isEmpty();
    }
}

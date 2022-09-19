package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.suite;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class PositiveListConverterTest extends BaseListConverterTest {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    @Test
    public void positiveRemoveLetterS() {
        System.out.println("positiveRemoveLetterSFromString");
        List<String> actualOutput = converter.removeLetterS(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }
}

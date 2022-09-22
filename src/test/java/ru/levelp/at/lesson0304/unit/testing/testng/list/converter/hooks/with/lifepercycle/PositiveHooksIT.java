package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.with.lifepercycle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PositiveHooksIT extends BaseHooksIT {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    @Test
    public void positiveRemoveLetterS() {
        System.out.println(this.getClass().getName() + " positiveRemoveLetterS");
        List<String> actualOutput = converter.removeLetterS(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }
}

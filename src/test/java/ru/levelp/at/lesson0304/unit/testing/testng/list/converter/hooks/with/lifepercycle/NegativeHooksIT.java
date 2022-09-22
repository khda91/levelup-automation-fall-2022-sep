package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.with.lifepercycle;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NegativeHooksIT extends BaseHooksIT {

    @BeforeAll
    @Override
    public void beforeAll() {
        System.out.println("Своя логика");
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

    @AfterAll
    @Override
    public void afterAll() {
        System.out.println("Своя логика after all");
    }
}

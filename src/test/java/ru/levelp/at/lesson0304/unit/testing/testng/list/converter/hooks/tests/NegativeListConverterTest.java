package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.tests;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeListConverterTest extends BaseListConverterTest {

    @Test
    public void inputListIsNullAssertjTest() {
        System.out.println("inputListIsNullAssertJTest");
        List<String> actualOutput = converter.removeLetterS(null);
        // assertj
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @Test
    public void inputListIsNullTestNgAssertTest() {
        System.out.println("inputListIsNullTestNgAssertTest");
        List<String> actualOutput = converter.removeLetterS(null);
        // assert testng
        Assert.assertTrue(actualOutput.isEmpty());
    }

    @Test
    public void inputListIsEmptyTest() {
        System.out.println("inputListIsEmptyTest");
        List<String> actualOutput = converter.removeLetterS(Collections.emptyList());
        Assertions.assertThat(actualOutput).isEmpty();
    }
}

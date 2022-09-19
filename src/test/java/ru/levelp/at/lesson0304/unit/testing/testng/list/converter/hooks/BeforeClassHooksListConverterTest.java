package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks;

import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;

public class BeforeClassHooksListConverterTest {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    private ListConverter converter;

    @BeforeClass
    public void beforeClass() {
        System.out.println("before class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("before method");
        converter = new ListConverter();
    }

    @Test
    public void positiveRemoveLetterS() {
        System.out.println("positiveRemoveLetterSFromString");
        List<String> actualOutput = converter.removeLetterS(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }

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
        System.out.println("inputListIsNullTestNgAssertTest");
        List<String> actualOutput = converter.removeLetterS(Collections.emptyList());
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("after method");
        converter = null;
    }

    @AfterClass
    public void afterClass() {
        System.out.println("after class");
    }
}

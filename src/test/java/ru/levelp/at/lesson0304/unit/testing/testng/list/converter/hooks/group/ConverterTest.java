package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.group;

import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;
import java.util.Collections;
import java.util.List;

public class ConverterTest {

    private static final List<String> INPUT = List.of("sea", "Summer", "count", "Synchronisation");
    private static final List<String> EXPECTED_OUTPUT = List.of("ea", "ummer", "count", "ynchroniation");

    private ListConverter converter;

    // @BeforeMethod(groups = {"ListConverter", "Negative"}) так писать не надо группы.
    // Высока вероятность ошибки
    // правильный вариант ниже
    @BeforeMethod(groups = {TestGroupName.LIST_CONVERTER_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void beforeMethod() {
        System.out.println("before method");
        converter = new ListConverter();
    }

    @Test(groups = {TestGroupName.LIST_CONVERTER_GROUP})
    public void positiveRemoveLetterS() {
        System.out.println("positiveRemoveLetterSFromString");
        List<String> actualOutput = converter.removeLetterS(INPUT);
        Assertions.assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }

    @Test(groups = {TestGroupName.LIST_CONVERTER_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void inputListIsNullAssertjTest() {
        System.out.println("inputListIsNullAssertJTest");
        List<String> actualOutput = converter.removeLetterS(null);
        // assertj
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @Test(groups = {TestGroupName.LIST_CONVERTER_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void inputListIsNullTestNgAssertTest() {
        System.out.println("inputListIsNullTestNgAssertTest");
        List<String> actualOutput = converter.removeLetterS(null);
        // assert testng
        Assert.assertTrue(actualOutput.isEmpty());
    }

    @Test(groups = {TestGroupName.LIST_CONVERTER_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void inputListIsEmptyTest() {
        System.out.println("inputListIsNullTestNgAssertTest");
        List<String> actualOutput = converter.removeLetterS(Collections.emptyList());
        Assertions.assertThat(actualOutput).isEmpty();
    }

    @AfterMethod(groups = {TestGroupName.LIST_CONVERTER_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void afterMethod() {
        System.out.println("after method");
        converter = null;
    }
}

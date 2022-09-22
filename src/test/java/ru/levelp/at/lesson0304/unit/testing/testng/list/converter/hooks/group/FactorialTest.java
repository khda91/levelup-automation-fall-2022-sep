package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.group;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelp.at.lesson0304.unit.testing.Factorial;

public class FactorialTest {

    private Factorial factorial;

    // @BeforeMethod(groups = {"Factorial", "Negative"}) так писать не надо группы.
    // Высока вероятность ошибки
    // правильный вариант ниже
    @BeforeGroups(groups = {TestGroupName.FACTORIAL_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void beforeGroups() {
        System.out.println(this.getClass().getName() + " before groups");
    }

    @BeforeMethod(groups = {TestGroupName.FACTORIAL_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void beforeMethod() {
        System.out.println(this.getClass().getName() + " before method");
        factorial = new Factorial();
    }

    @Test(groups = {TestGroupName.FACTORIAL_GROUP})
    public void positiveCalculateTest() {
        System.out.println(this.getClass().getName() + " positiveCalculateTest");
        long actualOutput = factorial.calculate(5);
        Assertions.assertThat(actualOutput).isEqualTo(120);
    }

    @Test(groups = {TestGroupName.FACTORIAL_GROUP})
    public void oneCalculateTest() {
        System.out.println(this.getClass().getName() + " oneCalculateTest");
        long actualOutput = factorial.calculate(1);
        Assertions.assertThat(actualOutput).isEqualTo(1);
    }

    @Test(groups = {TestGroupName.FACTORIAL_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void zeroCalculateTest() {
        System.out.println(this.getClass().getName() + " zeroCalculateTest");
        long actualOutput = factorial.calculate(0);
        Assertions.assertThat(actualOutput).isEqualTo(1);
    }

    @Test(groups = {TestGroupName.FACTORIAL_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void negativeCalculateTest() {
        System.out.println(this.getClass().getName() + " negativeCalculateTest");
        Assertions.assertThatThrownBy(() -> factorial.calculate(-1))
                  .isInstanceOf(IllegalArgumentException.class);
    }

    @AfterMethod(groups = {TestGroupName.FACTORIAL_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void afterMethod() {
        System.out.println(this.getClass().getName() + " after method");
        factorial = null;
    }

    @AfterGroups(groups = {TestGroupName.FACTORIAL_GROUP, TestGroupName.NEGATIVE_GROUP})
    public void afterGroups() {
        System.out.println(this.getClass().getName() + " after groups");
    }
}

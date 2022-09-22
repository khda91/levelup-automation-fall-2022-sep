package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.providers;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.levelp.at.lesson0304.unit.testing.Factorial;

public class FactorialDataProviderTest {

    private Factorial factorial;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(this.getClass().getName() + " before method");
        factorial = new Factorial();
    }

    @DataProvider
    public Object[][] calculateDataProvider() {
        return new Object[][] {
            {5, 120},
            {0, 1},
            {1, 1},
            {7, 5040},
            {3, 6}
        };
    }

    @Test(dataProvider = "calculateDataProvider")
    public void calculateTest(long num, long expectedOutput) {
        System.out.println(this.getClass().getName() + " calculateTest factorial " + num
            + " should be equal " + expectedOutput);
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @DataProvider(name = "Negative Data Provider")
    public Object[][] negativeCalculateDataProvider() {
        return new Object[][] {
            {-1},
            {-4},
            {-100}
        };
    }

    @Test(dataProvider = "Negative Data Provider")
    public void negativeCalculateTest(int num) {
        System.out.println(this.getClass().getName() + " negativeCalculateTest factorial " + num);
        Assertions.assertThatThrownBy(() -> factorial.calculate(num))
                  .isInstanceOf(IllegalArgumentException.class);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println(this.getClass().getName() + " after method");
        factorial = null;
    }
}

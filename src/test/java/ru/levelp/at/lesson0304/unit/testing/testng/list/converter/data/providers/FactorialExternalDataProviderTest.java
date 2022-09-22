package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.providers;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.levelp.at.lesson0304.unit.testing.Factorial;

public class FactorialExternalDataProviderTest {

    private Factorial factorial;

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(this.getClass().getName() + " before method");
        factorial = new Factorial();
    }

    @Test(dataProvider = "calculateDataProvider", dataProviderClass = ExternalDataProvider.class)
    public void calculateTest(long num, long expectedOutput) {
        System.out.println(this.getClass().getName() + " calculateTest factorial " + num
            + " should be equal " + expectedOutput);
        long actualOutput = factorial.calculate(num);
        Assertions.assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test(dataProvider = "Negative Data Provider", dataProviderClass = ExternalDataProvider.class)
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

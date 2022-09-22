package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.data.providers;

import org.testng.annotations.DataProvider;

public class ExternalDataProvider {

    @DataProvider
    public static Object[][] calculateDataProvider() {
        return new Object[][] {
            {5, 120},
            {0, 1},
            {1, 1},
            {7, 5040},
            {3, 6}
        };
    }
    @DataProvider(name = "Negative Data Provider")
    public static Object[][] negativeCalculateDataProvider() {
        return new Object[][] {
            {-1},
            {-4},
            {-100}
        };
    }
}

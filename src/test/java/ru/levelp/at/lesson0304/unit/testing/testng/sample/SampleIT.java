package ru.levelp.at.lesson0304.unit.testing.testng.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleIT {

    @Test
    public void sumTest() {
        Assertions.assertEquals(4, 2 + 2);
    }
}

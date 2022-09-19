package ru.levelp.at.lesson0304.unit.testing.testng.sample;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest {

    @Test
    public void sumTest() {
        Assert.assertEquals(2 + 2, 4);
    }
}

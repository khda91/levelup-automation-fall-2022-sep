package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.without.lifepercycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;

public abstract class BaseHooksTest {

    protected ListConverter converter;

    @BeforeAll
    public static void beforeAll() {
        System.out.println(BaseHooksTest.class.getName() + " before all");
    }

    @BeforeEach
    public void setUp() {
        System.out.println(this.getClass().getName() + " before each");
        converter = new ListConverter();
    }

    @AfterEach
    public void tearDown() {
        System.out.println(this.getClass().getName() + " after each");
        converter = null;
    }

    @AfterAll
    public static void afterAll() {
        System.out.println(BaseHooksTest.class.getName() + " after all");
    }
}

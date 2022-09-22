package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.with.lifepercycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class BaseHooksIT {

    protected ListConverter converter;

    @BeforeAll
    public void beforeAll() {
        System.out.println(BaseHooksIT.class.getName() + " before all");
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
    public void afterAll() {
        System.out.println(BaseHooksIT.class.getName() + " after all");
    }
}

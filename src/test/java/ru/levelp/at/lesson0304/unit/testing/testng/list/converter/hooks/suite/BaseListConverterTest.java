package ru.levelp.at.lesson0304.unit.testing.testng.list.converter.hooks.suite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.levelp.at.lesson0304.unit.testing.ListConverter;

public abstract class BaseListConverterTest {

    protected ListConverter converter;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("before suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println(this.getClass().getName() + " before class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(this.getClass().getName() + " before method");
        converter = new ListConverter();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println(this.getClass().getName() + " after method");
        converter = null;
    }

    @AfterClass
    public void afterClass() {
        System.out.println(this.getClass().getName() + " after class");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("after suite");
    }
}

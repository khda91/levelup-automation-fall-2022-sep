package ru.levelp.at.lesson13.bdd.steps;

import org.openqa.selenium.WebDriver;
import ru.levelp.at.lesson13.bdd.context.TestContext;

public abstract class AbstractStep {

    protected WebDriver driver;

    protected AbstractStep() {
        driver = (WebDriver) TestContext.getInstance().getObject("driver");
    }
}

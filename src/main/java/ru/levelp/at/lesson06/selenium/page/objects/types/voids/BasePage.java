package ru.levelp.at.lesson06.selenium.page.objects.types.voids;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    private static final String USER_BUG_RED_URL = "http://users.bugred.ru";

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    protected void open(final String relativeUrl) {
        driver.navigate().to(USER_BUG_RED_URL + relativeUrl);
    }

    public abstract void open();
}

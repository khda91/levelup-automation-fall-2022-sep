package ru.levelp.at.taf.service.webdriver;

import org.openqa.selenium.WebDriver;

public final class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserName = System.getProperty("browser.name", "chrome");
            driver = WebDriverFactory.createDriver(browserName);
        }

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}

package ru.levelp.at.lesson12.design.patterns.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public final class WebDriverFactory {

    public static final String CHROME = "CHROME";
    public static final String FIREFOX = "FIREFOX";
    public static final String SAFARI = "SAFARI";

    private WebDriverFactory() {
    }

    public static WebDriver createDriver(final String browserName) {
        WebDriver driver;
        if (CHROME.equalsIgnoreCase(browserName)) {
            driver = createChromeDriver();
        } else if (FIREFOX.equalsIgnoreCase(browserName)) {
            driver = createFirefoxDriver();
        } else if (SAFARI.equalsIgnoreCase(browserName)) {
            driver = createSafariDriver();
        } else {
            throw new IllegalArgumentException("Не опознанный браузер " + browserName);
        }
        return driver;
    }

    private static WebDriver createSafariDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}

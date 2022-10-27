package ru.levelp.at.taf.service.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.levelp.at.taf.exception.UnsupportedBrowserException;

public final class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private WebDriverFactory() {

    }

    public static WebDriver createDriver(String browserName) {
        if (CHROME.equalsIgnoreCase(browserName)) {
            return createChromeDriver();
        } else if (FIREFOX.equalsIgnoreCase(browserName)) {
            return createFirefoxDriver();
        } else {
            throw new UnsupportedBrowserException(browserName);
        }
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

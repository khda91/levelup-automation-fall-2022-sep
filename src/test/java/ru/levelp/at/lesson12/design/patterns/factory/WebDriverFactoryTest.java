package ru.levelp.at.lesson12.design.patterns.factory;

import org.junit.jupiter.api.Test;
import ru.levelp.at.utils.SleepUtils;

class WebDriverFactoryTest {

    private static final String URL = "https://ya.ru";

    @Test
    void createChromeDriver() {
        var driver = WebDriverFactory.createDriver(WebDriverFactory.CHROME);
        driver.navigate().to(URL);
        SleepUtils.sleep(1500);
        driver.quit();
    }

    @Test
    void createFirefoxDriver() {
        var driver = WebDriverFactory.createDriver(WebDriverFactory.FIREFOX);
        driver.navigate().to(URL);
        SleepUtils.sleep(1500);
        driver.quit();
    }

    @Test
    void createSafariDriver() {
        var driver = WebDriverFactory.createDriver("safari");
        driver.navigate().to(URL);
        SleepUtils.sleep(1500);
        driver.quit();
    }
}

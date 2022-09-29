package ru.levelp.at.lesson05.selenium.basic.sample;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.levelp.at.utils.SleepUtils;

public class SeleniumChromeSampleTest {

    private static final String DZEN_URL = "https://dzen.ru/";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        SleepUtils.sleep(1500);
    }

    @Test
    public void openSomePage() {
        driver.navigate().to(DZEN_URL);
        SleepUtils.sleep(2000);
        assertThat(driver.getTitle()).isEqualTo("Дзен");
        SleepUtils.sleep(1000);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        SleepUtils.sleep(1000);
    }
}

package ru.levelp.at.lesson1011.cicd;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseSeleniumTest {

    protected static final String USER_BUG_RED_URL = "http://users.bugred.ru/user/login/index.html";

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Faker faker;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        boolean headless = Boolean.parseBoolean(System.getenv("HEADLESS"));
        if (headless) {
            options.setHeadless(true);
        }
        faker = new Faker();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

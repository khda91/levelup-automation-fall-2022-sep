package ru.levelp.at.lesson05.selenium.basic.locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.levelp.at.utils.SleepUtils;

public class SeleniumFrameTest {

    private static final String DZEN_URL = "https://dzen.ru/";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(DZEN_URL);
        SleepUtils.sleep(1500);
    }

    @Test
    public void frameFail() {
        driver.findElement(By.xpath("//input[contains(@class, 'arrow__input')]")).sendKeys("abc");
        SleepUtils.sleep(3000);
    }

    @Test
    public void frame() {
        WebElement iFrame = driver.findElement(By.cssSelector("iframe.dzen-search-arrow-common__frame"));
        SleepUtils.sleep(1000);
        driver.switchTo().frame(iFrame);

        driver.findElement(By.xpath("//input[contains(@class, 'arrow__input')]")).sendKeys("abc");
        SleepUtils.sleep(3000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

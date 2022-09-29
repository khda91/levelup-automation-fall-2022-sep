package ru.levelp.at.lesson05.selenium.basic.elements;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.levelp.at.utils.SleepUtils;

public class SeleniumElementsTest {

    private static final String MAIL_RU_URL = "https://mail.ru/";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(MAIL_RU_URL);
        SleepUtils.sleep(1500);
    }

    @Test
    public void openSomePage() {
        WebElement searchTextBox = driver.findElement(By.id("q"));
        System.out.println("search text box class attribute - > " + searchTextBox.getAttribute("class"));
        searchTextBox.sendKeys("ночь улица фонарь");
        SleepUtils.sleep(1500);

        driver.findElement(By.id("search:submit")).click();
        SleepUtils.sleep(1500);

        List<WebElement> searchResults = driver
            .findElements(By.cssSelector("[data-selector='SnippetResultTitle-link']"));
        System.out.println("searchResults size " + searchResults.size());
        for (WebElement searchResult : searchResults) {
            System.out.println(searchResult.getText());
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        SleepUtils.sleep(1000);
    }
}

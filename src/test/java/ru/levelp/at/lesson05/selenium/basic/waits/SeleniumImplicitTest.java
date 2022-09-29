package ru.levelp.at.lesson05.selenium.basic.waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumImplicitTest {

    private static final String MAIL_RU_URL = "https://mail.ru/";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        driver.navigate().to(MAIL_RU_URL);
    }

    @Test
    public void openSomePage() {
        WebElement searchTextBox = driver.findElement(By.id("q"));
        searchTextBox.sendKeys("ночь улица фонарь");

        driver.findElement(By.id("search:submit")).click();

        List<WebElement> searchResults = driver
            .findElements(By.cssSelector("[data-selector='SnippetResultTitle-link']"));
        System.out.println("searchResults size " + searchResults.size());
        for (WebElement searchResult : searchResults) {
            System.out.println(searchResult.getText());
        }
    }

    @Test
    public void openSomePageFail() {
        WebElement searchTextBox = driver.findElement(By.id("q1"));
        searchTextBox.sendKeys("ночь улица фонарь");

        driver.findElement(By.id("search:submit")).click();

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
    }
}

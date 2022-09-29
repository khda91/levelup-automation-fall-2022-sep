package ru.levelp.at.lesson05.selenium.basic.waits;

import static org.assertj.core.api.Assertions.assertThat;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumExplicitVsImplicitTest {

    private static final String MAIL_RU_URL = "https://mail.ru/";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to(MAIL_RU_URL);
    }

    @Test
    public void openSomePage() {
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
        searchTextBox.sendKeys("ночь улица фонарь");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("search:submit"))).click();

        List<WebElement> searchResults = wait.until(ExpectedConditions
            .numberOfElementsToBeMoreThan(By.cssSelector("[data-selector='SnippetResultTitle-link']"), 3));
        System.out.println("searchResults size " + searchResults.size());
        for (WebElement searchResult : searchResults) {
            System.out.println(searchResult.getText());
        }
    }

    // явное ожидание == неявное ожидание
    @Test
    public void openSomePageFailVersion1() {
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        long start = System.currentTimeMillis();

        try {
            WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
            searchTextBox.sendKeys("ночь улица фонарь");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("search:submit1"))).click();

            List<WebElement> searchResults = wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector("[data-selector='SnippetResultTitle-link']"), 3));
            System.out.println("searchResults size " + searchResults.size());
            for (WebElement searchResult : searchResults) {
                System.out.println(searchResult.getText());
            }
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("openSomePageFailVersion1 execution time: " + (end - start) + " ms");
        }
    }

    // явное ожидание > неявное ожидание
    @Test
    public void openSomePageFailVersion2() {
        wait = new WebDriverWait(driver, Duration.ofMillis(15000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        long start = System.currentTimeMillis();

        try {
            WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
            searchTextBox.sendKeys("ночь улица фонарь");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("search:submit1"))).click();

            List<WebElement> searchResults = wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector("[data-selector='SnippetResultTitle-link']"), 3));
            System.out.println("searchResults size " + searchResults.size());
            for (WebElement searchResult : searchResults) {
                System.out.println(searchResult.getText());
            }
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("openSomePageFailVersion2 execution time: " + (end - start) + " ms");
        }
    }

    // явное ожидание < неявное ожидание
    @Test
    public void openSomePageFailVersion3() {
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        long start = System.currentTimeMillis();

        try {
            WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
            searchTextBox.sendKeys("ночь улица фонарь");

            wait.until(ExpectedConditions.elementToBeClickable(By.id("search:submit1"))).click();

            List<WebElement> searchResults = wait.until(ExpectedConditions
                .numberOfElementsToBeMoreThan(By.cssSelector("[data-selector='SnippetResultTitle-link']"), 3));
            System.out.println("searchResults size " + searchResults.size());
            for (WebElement searchResult : searchResults) {
                System.out.println(searchResult.getText());
            }
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("openSomePageFailVersion3 execution time: " + (end - start) + " ms");
        }
    }

    // как можно использовать вместе явные и неявные ожидания
    @Test
    public void openSomePageRightOne() {
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
        searchTextBox.sendKeys("ночь улица фонарь");

        var implicitWaitDuration = driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            Boolean inVisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("search:submit1")));
            assertThat(inVisible).isTrue();
        } finally {
            driver.manage().timeouts().implicitlyWait(implicitWaitDuration);
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

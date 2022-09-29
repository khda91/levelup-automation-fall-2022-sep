package ru.levelp.at.lesson05.selenium.basic.locators;

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

public class SeleniumCssSelectorTest {

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
    public void byId() {
        // WebElement searchTextBox = driver.findElement(By.id("q")); // DOM
        WebElement searchTextBox = driver.findElement(By.cssSelector("#q"));
        searchTextBox.sendKeys("ночь улица фонарь");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byClassName() {
        // WebElement searchTextBox = driver.findElement(By.className("search__input")); // DOM
        WebElement searchTextBox = driver.findElement(By.cssSelector(".search__input"));
        searchTextBox.sendKeys("бесмысленный и тусклый свет");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byName() {
        // WebElement searchTextBox = driver.findElement(By.name("q")); // DOM
        WebElement searchTextBox = driver.findElement(By.cssSelector("[name='q']"));
        searchTextBox.sendKeys("живи ещё хоть четверть века");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byAnotherAttribute() {
        WebElement searchTextBox = driver.findElement(By.cssSelector("[data-testid='search-input']"));
        searchTextBox.sendKeys("и будет так и спору нет");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byTagName() {
        // List<WebElement> tags = driver.findElements(By.tagName("li")); // DOM
        List<WebElement> tags = driver.findElements(By.cssSelector("li"));
        System.out.println("tags size -> " + tags.size());
        SleepUtils.sleep(1500);
    }

    @Test
    public void combinedSearch() {
        // var text = driver.findElement(By.cssSelector("span[id='search:example']"));
        var text = driver.findElement(By.cssSelector("span#search\\:example"));
        System.out.println(text.getText());
        SleepUtils.sleep(1500);
    }

    @Test
    public void combinedSearchWithParent() {
        var mailServiceLink = driver.findElement(By.cssSelector("div a.mailbox-service"));
        System.out.println(mailServiceLink.getText());
        SleepUtils.sleep(1500);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        SleepUtils.sleep(1000);
    }
}

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

import java.util.List;

public class SeleniumXpathTest {

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
        // WebElement searchTextBox = driver.findElement(By.cssSelector("#q")); // css
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@id='q']"));
        searchTextBox.sendKeys("ночь улица фонарь");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byClassNameFail() {
        // WebElement searchTextBox = driver.findElement(By.className("search__input")); // DOM
        // WebElement searchTextBox = driver.findElement(By.cssSelector(".search__input")); // css
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@class='search__input']"));
        // Правильный но громоздкий вариант
        //            .xpath("//*[@class='input search__input svelte-10qu4cf']"));
        //        WebElement searchTextBox = driver.findElement(By
        //            .xpath("//*[@class='input search__input svelte-10qu4cf']"));
        searchTextBox.sendKeys("бесмысленный и тусклый свет");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byClassName() {
        // WebElement searchTextBox = driver.findElement(By.className("search__input")); // DOM
        // WebElement searchTextBox = driver.findElement(By.cssSelector(".search__input")); // css
        WebElement searchTextBox = driver.findElement(By.xpath("//*[contains(@class, 'search__input')]"));
        searchTextBox.sendKeys("бесмысленный и тусклый свет");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byName() {
        // WebElement searchTextBox = driver.findElement(By.name("q")); // DOM
        // WebElement searchTextBox = driver.findElement(By.cssSelector("[name='q']")); // css
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@name='q']"));
        searchTextBox.sendKeys("живи ещё хоть четверть века");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byAnotherAttribute() {
        // WebElement searchTextBox = driver.findElement(By.cssSelector("[data-testid='search-input']")); // css
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@data-testid='search-input']"));
        searchTextBox.sendKeys("и будет так и спору нет");
        SleepUtils.sleep(1500);
    }

    @Test
    public void byTagName() {
        // List<WebElement> tags = driver.findElements(By.tagName("li")); // DOM
        // List<WebElement> tags = driver.findElements(By.cssSelector("li")); // css
        List<WebElement> tags = driver.findElements(By.xpath("//li"));
        System.out.println("tags size -> " + tags.size());
        SleepUtils.sleep(1500);
    }

    @Test
    public void combinedSearch() {
        // var text = driver.findElement(By.cssSelector("span[id='search:example']"));
        // var text = driver.findElement(By.cssSelector("span#search\\:example")); // css
        var text = driver.findElement(By.xpath("//span[@id='search:example']"));
        System.out.println(text.getText());
        SleepUtils.sleep(1500);
    }

    @Test
    public void combinedSearchWithParent() {
        // var mailServiceLink = driver.findElement(By.cssSelector("div a.mailbox-service")); // css
        var mailServiceLink = driver.findElement(By
            .xpath("//div//a[contains(@class, 'mailbox-service')]"));
        System.out.println(mailServiceLink.getText());
        SleepUtils.sleep(1500);
    }

    @Test
    public void byLinkText() {
        // WebElement link = driver.findElement(By.linkText("Санкт-Петербург")); // DOM
        WebElement link = driver.findElement(By.xpath("//*[text()='Санкт-Петербург']"));
        link.click();
        SleepUtils.sleep(1500);
    }

    @Test
    public void byPartialLinkText() {
        // WebElement link = driver.findElement(By.partialLinkText("будет выдвигать претендента")); // DOM
        WebElement link = driver.findElement(By
            .xpath("//*[contains(text(), 'будет выдвигать претендента')]"));
        link.click();
        SleepUtils.sleep(1500);
    }

    @Test
    public void byChildParent() {
        WebElement link = driver.findElement(By.xpath("//*[text()='Санкт-Петербург']/.."));
        System.out.println(link.getText());
        SleepUtils.sleep(1500);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        SleepUtils.sleep(1000);
    }
}

package ru.levelp.at.lesson05.selenium.basic.handle;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.levelp.at.utils.SleepUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumHandleTest {

    private static final String DZEN_URL = "https://dzen.ru/";
    private static final String MAIL_RU_URL = "https://mail.ru/";

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

        driver = driver.switchTo().newWindow(WindowType.TAB);
        SleepUtils.sleep(1000);

        driver.navigate().to(MAIL_RU_URL);
        SleepUtils.sleep(2000);

        driver = driver.switchTo().newWindow(WindowType.WINDOW);
        SleepUtils.sleep(1000);
        driver.close();
        SleepUtils.sleep(1000);
    }

    @Test
    public void switchTab() {
        driver.navigate().to(DZEN_URL);
        SleepUtils.sleep(2000);
        System.out.println("Открыт Дзен -> " + driver.getWindowHandles());

        driver = driver.switchTo().newWindow(WindowType.TAB);
        SleepUtils.sleep(1000);

        driver.navigate().to(MAIL_RU_URL);
        SleepUtils.sleep(2000);
        System.out.println("Открыт Mail.ru -> " + driver.getWindowHandles());

        driver = driver.switchTo().newWindow(WindowType.WINDOW);
        SleepUtils.sleep(1000);
        System.out.println("Открыто пустое окно -> " + driver.getWindowHandles());
        driver.close();
        SleepUtils.sleep(2000);
        System.out.println("Закрыто пустое окно -> " + driver.getWindowHandles());

        var handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if ("Дзен".equals(driver.getTitle())) {
                driver.close();
                SleepUtils.sleep(3000);
                System.out.println("Закрыт Дзен -> " + driver.getWindowHandles());
            }
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        SleepUtils.sleep(1000);
    }
}

package ru.levelp.at.lesson15;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadFileTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.diffchecker.com/pdf-diff/");
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
    }

    @Test
    void loadFile() {
        WebElement first =
            wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='fileOriginal-PDF']/..")));

        //            wait.until(ExpectedConditions
        //            .visibilityOfElementLocated(By
        //                .xpath("//*[contains(@class, 'input-instruction_inputInstructionInner')][1]/div[2]")));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        first.sendKeys("/Users/d.khodakovskiy/Downloads"
            + "/075_1-okruzhayuschiy-mir_-2kl_-uchebn_-v-2ch_-ch_1_pleshakov-a_a__2012-144s.pdf");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement second = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By
                .xpath("//*[contains(@class, 'input-instruction_inputInstructionInner')][2]")));
        second.sendKeys("/Users/d.khodakovskiy/Downloads"
            + "/075_1-okruzhayuschiy-mir_-2kl_-uchebn_-v-2ch_-ch_1_pleshakov-a_a__2012-144s.pdf");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement button =
            wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//span[text()='Find difference']")));
        button.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

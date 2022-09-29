package ru.levelp.at.lesson06.selenium.page.objects.sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.lesson06.selenium.page.objects.BaseSeleniumTest;

public class SampleWithoutPageObjectsTest extends BaseSeleniumTest {

    @Test
    public void registrationTest() {
        driver.navigate().to(USER_BUG_RED_URL);

        String name = faker.funnyName().name();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//form[contains(@action, 'register')]//input[@name='name']")))
            .sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//form[contains(@action, 'register')]//input[@name='email']")))
            .sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//form[contains(@action, 'register')]//input[@name='password']")))
            .sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By
                .xpath("//form[contains(@action, 'register')]//input[@name='act_register_now']")))
            .click();

        String actualUserName = wait.until(ExpectedConditions.visibilityOfElementLocated(By
            .xpath("//li[@id='fat-menu']/a[@class='dropdown-toggle']"))).getText();
        assertThat(actualUserName).isEqualToIgnoringCase(name);
    }
}

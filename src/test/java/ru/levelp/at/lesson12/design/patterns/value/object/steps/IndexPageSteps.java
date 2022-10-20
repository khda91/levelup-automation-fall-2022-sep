package ru.levelp.at.lesson12.design.patterns.value.object.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.levelp.at.lesson1011.cicd.IndexPage;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexPageSteps {

    private final WebDriver driver;
    private final IndexPage indexPage;

    public IndexPageSteps(WebDriver driver) {
        this.driver = driver;
        indexPage = new IndexPage(driver);
    }

    @Step("Проверка, что имя пользователя совпадает с {expectedUsername}")
    public void assertRegisterUsername(String expectedUsername) {
        String actualUserName = indexPage.getUsernameFromUserMenu();
        assertThat(actualUserName).isEqualToIgnoringCase(expectedUsername);
    }
}

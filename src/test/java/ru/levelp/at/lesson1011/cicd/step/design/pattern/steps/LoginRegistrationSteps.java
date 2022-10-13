package ru.levelp.at.lesson1011.cicd.step.design.pattern.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.levelp.at.lesson1011.cicd.LoginRegistrationPage;

public class LoginRegistrationSteps {

    private final WebDriver driver;
    private final LoginRegistrationPage loginRegistrationPage;

    public LoginRegistrationSteps(WebDriver driver) {
        this.driver = driver;
        loginRegistrationPage = new LoginRegistrationPage(driver);
    }

    @Step("Открываем страницу Регистрации userbugred")
    public void openPage() {
        loginRegistrationPage.open();
    }

    @Step("Регистрация пользователя {username}, {email}")
    public void registerUser(String username, String email, String password) {
        loginRegistrationPage.fillNameTextField(username);
        loginRegistrationPage.fillEmailTextField(email);
        loginRegistrationPage.fillPasswordTextField(password);
        loginRegistrationPage.clickRegisterButton();
    }

    @Step("Провека что текст ошибки равен {expectedErrorText}")
    public void assertErrorText(String expectedErrorText) {
        var actualText = loginRegistrationPage.getErrorTextLabelText();

        assertThat(actualText).isEqualToIgnoringCase(expectedErrorText);
    }
}

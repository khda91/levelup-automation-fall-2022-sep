package ru.levelp.at.lesson12.design.patterns.value.object.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.levelp.at.lesson1011.cicd.LoginRegistrationPage;
import ru.levelp.at.lesson12.design.patterns.value.object.User;

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

    @Step("Регистрация пользователя {username}, {email}")
    public void registerUser(User user) {
        loginRegistrationPage.fillNameTextField(user.getUsername());
        loginRegistrationPage.fillEmailTextField(user.getEmail());
        loginRegistrationPage.fillPasswordTextField(user.getPassword());
        loginRegistrationPage.clickRegisterButton();
    }

    @Step("Провека что текст ошибки равен {expectedErrorText}")
    public void assertErrorText(String expectedErrorText) {
        var actualText = loginRegistrationPage.getErrorTextLabelText();

        assertThat(actualText).isEqualToIgnoringCase(expectedErrorText);
    }
}

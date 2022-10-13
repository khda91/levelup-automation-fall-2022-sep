package ru.levelp.at.lesson1011.cicd;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;

public class SamplePageObjectsTest extends BaseSeleniumTest {

    @Test
    public void registrationTest() {
        String name = faker.funnyName().name();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        var registrationPage = new LoginRegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameTextField(name);
        registrationPage.fillEmailTextField(email);
        registrationPage.fillPasswordTextField(password);

        Allure.step("Нажимаем на кнопку Зарегистрироваться", () -> registrationPage.clickRegisterButton());

        var indexPage = new IndexPage(driver);
        String actualUserName = indexPage.getUsernameFromUserMenu();
        Allure.step("Проверяем имя пользователя", () -> assertThat(actualUserName)
            .isEqualToIgnoringCase(name));
    }

    @Test
    public void registrationNegativeTest() {
        String name = faker.funnyName().name();
        String email = faker.internet().domainName();
        String password = faker.internet().password();

        var registrationPage = new LoginRegistrationPage(driver);
        registrationPage.open();
        registrationPage.fillNameTextField(name);
        registrationPage.fillEmailTextField(email);
        registrationPage.fillPasswordTextField(password);
        registrationPage.clickRegisterButton();
        var actualText = registrationPage.getErrorTextLabelText();

        assertThat(actualText).isEqualToIgnoringCase("register_not_correct_field (email)1");
    }
}

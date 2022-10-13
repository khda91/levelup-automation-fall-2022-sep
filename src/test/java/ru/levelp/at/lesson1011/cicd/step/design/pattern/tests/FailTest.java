package ru.levelp.at.lesson1011.cicd.step.design.pattern.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Регистрация")
@Feature("Feature 1")
@DisplayName("Тесты для страницы регистрации")
public class FailTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Тест не правильной Регистрации пользователя (fail)")
    @Description("Тест проверяет возможность регистрации с неправильными параметрами (fail)")
    @Story("Падающая регистрания")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LAQ-2")
    @Issue("LAQ-5")
    public void registrationNegativeTest() {
        String name = FAKER.funnyName().name();
        String email = FAKER.internet().domainName();
        String password = FAKER.internet().password();

        loginRegistrationSteps.openPage();
        loginRegistrationSteps.registerUser(name, email, password);
        loginRegistrationSteps.assertErrorText("register_not_correct_field (email)1");
    }
}

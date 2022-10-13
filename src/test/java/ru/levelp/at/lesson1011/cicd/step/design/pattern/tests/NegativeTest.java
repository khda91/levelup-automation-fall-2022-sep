package ru.levelp.at.lesson1011.cicd.step.design.pattern.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Регистрация 1")
@Feature("Feature 2")
@DisplayName("Тесты для страницы регистрации")
public class NegativeTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Тест не правильной Регистрации пользователя (success)")
    @Description("Тест проверяет возможность регистрации с неправильными параметрами (success)")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("LAQ-3")
    @Story("Успешная регистрация с неправельный именем")
    public void registrationNegative1Test() {
        String name = FAKER.funnyName().name();
        String email = FAKER.internet().domainName();
        String password = FAKER.internet().password();

        loginRegistrationSteps.openPage();
        loginRegistrationSteps.registerUser(name, email, password);
        loginRegistrationSteps.assertErrorText("register_not_correct_field (email)");
    }
}

package ru.levelp.at.lesson1011.cicd.step.design.pattern.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Epic("Регистрация 1")
@Feature("Feature 1")
@DisplayName("Тесты для страницы регистрации")
public class NegativeDdtTest extends BaseSeleniumTest {

    static Stream<Arguments> userTestData() {
        return Stream.of(
            Arguments.of(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password()),
            Arguments.of(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password()),
            Arguments.of(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password())
        );
    }

    @ParameterizedTest(name = "Тест не правильной Регистрации пользователя (success) {0}")
    @MethodSource("userTestData")
    @Description("Тест проверяет возможность регистрации с неправильными параметрами (success)")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("LAQ-3")
    @Story("Успешная регистрация с неправельный именем")
    void registrationNegative1Test(String name, String email, String password) {
        loginRegistrationSteps.openPage();
        loginRegistrationSteps.registerUser(name, email, password);
        loginRegistrationSteps.assertErrorText("register_not_correct_field (email)");
    }
}

package ru.levelp.at.lesson12.design.patterns.value.object.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.lesson12.design.patterns.value.object.User;

@Epic("Регистрация")
@Feature("Feature 1")
@DisplayName("Тесты для страницы регистрации")
public class FailTest extends BaseSeleniumTest {

    private static final User USER =
        new User(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password());

    // Data Provider approach
    static Stream<Arguments> dataProvider() {
        return Stream.of(
            Arguments.of(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password()),
            Arguments.of(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password()),
            Arguments.of(FAKER.funnyName().name(), FAKER.internet().domainName(), FAKER.internet().password())
        );
    }

    // Entity Driven Data Provider
    static Stream<Arguments> entityDataProvider() {
        return Stream.of(
            Arguments.of(new User(FAKER.funnyName().name(), FAKER.internet().domainName(),
                FAKER.internet().password())),
            Arguments.of(new User(FAKER.funnyName().name(), FAKER.internet().domainName(),
                FAKER.internet().password())),
            Arguments.of(new User(FAKER.funnyName().name(), FAKER.internet().domainName(),
                FAKER.internet().password()))
        );
    }

    @Test
    @DisplayName("Тест не правильной Регистрации пользователя (fail)")
    @Description("Тест проверяет возможность регистрации с неправильными параметрами (fail)")
    @Story("Падающая регистрания")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LAQ-2")
    @Issue("LAQ-5")
    public void registrationNegativeTest() {
        loginRegistrationSteps.openPage();
        loginRegistrationSteps.registerUser(USER);
        loginRegistrationSteps.assertErrorText("register_not_correct_field (email)1");
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    @DisplayName("Тест не правильной Регистрации пользователя (fail)")
    @Description("Тест проверяет возможность регистрации с неправильными параметрами (fail)")
    @Story("Падающая регистрания")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LAQ-2")
    @Issue("LAQ-5")
    public void registrationNegativeTest(String username, String email, String password) {
        loginRegistrationSteps.openPage();
        loginRegistrationSteps.registerUser(username, email, password);
        loginRegistrationSteps.assertErrorText("register_not_correct_field (email)1");
    }

    @ParameterizedTest
    @MethodSource("entityDataProvider")
    @DisplayName("Тест не правильной Регистрации пользователя (fail)")
    @Description("Тест проверяет возможность регистрации с неправильными параметрами (fail)")
    @Story("Падающая регистрания")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("LAQ-2")
    @Issue("LAQ-5")
    public void registrationNegativeTest(User user) {
        loginRegistrationSteps.openPage();
        loginRegistrationSteps.registerUser(user);
        loginRegistrationSteps.assertErrorText("register_not_correct_field (email)1");
    }
}

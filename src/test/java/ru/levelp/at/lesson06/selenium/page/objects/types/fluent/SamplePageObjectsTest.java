package ru.levelp.at.lesson06.selenium.page.objects.types.fluent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson06.selenium.page.objects.BaseSeleniumTest;

public class SamplePageObjectsTest extends BaseSeleniumTest {

    @Test
    public void registrationTest() {
        String name = faker.funnyName().name();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        var indexPage = new LoginRegistrationPage(driver)
            .open()
            .fillNameTextField(name)
            .fillEmailTextField(email)
            .fillPasswordTextField(password)
            .clickRegisterButton();

        String actualUserName = indexPage.getUsernameFromUserMenu();
        assertThat(actualUserName).isEqualToIgnoringCase(name);
    }

    @Test
    public void registrationTestV2() {
        String name = faker.funnyName().name();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        String actualUserName = new LoginRegistrationPage(driver)
            .open()
            .fillNameTextField(name)
            .fillEmailTextField(email)
            .fillPasswordTextField(password)
            .clickRegisterButton()
            .getUsernameFromUserMenu();

        assertThat(actualUserName).isEqualToIgnoringCase(name);
    }

    @Test
    public void registrationNegativeTest() {
        String name = faker.funnyName().name();
        String email = faker.internet().domainName();
        String password = faker.internet().password();

        var registerPage = new LoginRegistrationPage(driver)
            .open()
            .fillNameTextField(name)
            .fillEmailTextField(email)
            .fillPasswordTextField(password);

        registerPage.clickRegisterButton();
        var actualText = registerPage.getErrorTextLabelText();

        assertThat(actualText).isEqualToIgnoringCase("register_not_correct_field (email)");
    }

    // наиболее частый вариант
    @Test
    public void registrationNegativeV2Test() {
        String name = faker.funnyName().name();
        String email = faker.internet().domainName();
        String password = faker.internet().password();

        var actualText = new LoginRegistrationPage(driver)
            .open()
            .fillNameTextField(name)
            .fillEmailTextField(email)
            .fillPasswordTextField(password)
            .failClickRegisterButton()
            .getErrorTextLabelText();

        assertThat(actualText).isEqualToIgnoringCase("register_not_correct_field (email)");
    }

    // наиболее частый вариант
    @Test
    public void registrationV2Test() {
        String name = faker.funnyName().name();
        String email = faker.internet().domainName();
        String password = faker.internet().password();

        var actualText = new LoginRegistrationPage(driver)
            .open()
            .fillNameTextField(name)
            .fillEmailTextField(email)
            .fillPasswordTextField(password)
            .failClickRegisterButton()
            .successClickRegisterButton()
            .getUsernameFromUserMenu();

        assertThat(actualText).isEqualToIgnoringCase(name);
    }
}

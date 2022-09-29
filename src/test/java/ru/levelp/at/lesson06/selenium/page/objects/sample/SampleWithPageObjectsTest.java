package ru.levelp.at.lesson06.selenium.page.objects.sample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson06.selenium.page.objects.BaseSeleniumTest;

public class SampleWithPageObjectsTest extends BaseSeleniumTest {

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
        registrationPage.clickRegisterButton();

        var indexPage = new IndexPage(driver);
        String actualUserName = indexPage.getUsernameFromUserMenu();
        assertThat(actualUserName).isEqualToIgnoringCase(name);
    }
}

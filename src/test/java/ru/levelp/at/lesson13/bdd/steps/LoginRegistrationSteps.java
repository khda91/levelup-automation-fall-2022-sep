package ru.levelp.at.lesson13.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import ru.levelp.at.lesson13.bdd.LoginRegistrationPage;

public class LoginRegistrationSteps extends AbstractStep {

    @Given("I am on the Login-Registration page")
    public void openLoginRegistrationPage() {
        new LoginRegistrationPage(driver).open();
    }

    @When("I enter {string} to 'Имя' text field in the Registration section on the Login-Registration page")
    public void enterToNameTextFieldInRegistrationSection(String username) {
        new LoginRegistrationPage(driver).fillNameTextField(username);
    }

    @When("^I enter '(.*)' to 'Email' text field in the Registration section on the Login-Registration page$")
    public void enterToEmailTextFieldInRegistrationSection(String email) {
        new LoginRegistrationPage(driver).fillEmailTextField(email);
    }

    @When("I enter {string} to 'Password' text field in the Registration section on the Login-Registration page")
    public void enterToPasswordTextFieldInRegistrationSection(String password) {
        new LoginRegistrationPage(driver).fillPasswordTextField(password);
    }

    @When("I click to 'Зарегистрироваться' button in the Registration section on the Login-Registration page")
    public void clickToRegisterButtonInTheRegistrationSection() {
        new LoginRegistrationPage(driver).clickRegisterButton();
    }

    @Then("error message should be equal to {string} in the Registration section on the Registration page")
    public void errorMessageShouldBeEqualInTheRegistrationSection(String expectedErrorMessage) {
        var actualText = new LoginRegistrationPage(driver).getErrorTextLabelText();
        assertThat(actualText).isEqualToIgnoringCase(expectedErrorMessage);
    }

    @When("I register user with list data:")
    public void registerUserWithData(List<Map<String, String>> user) {
        var loginRegistrationPage = new LoginRegistrationPage(driver);
        Map<String, String> testUser = user.get(0);
        loginRegistrationPage.fillNameTextField(testUser.get("username"));
        loginRegistrationPage.fillEmailTextField(testUser.get("email"));
        loginRegistrationPage.fillPasswordTextField(testUser.get("password"));
        loginRegistrationPage.clickRegisterButton();
    }

    @When("I register user with map data:")
    public void registerUserWithMapData(Map<String, String> user) {
        var loginRegistrationPage = new LoginRegistrationPage(driver);
        loginRegistrationPage.fillNameTextField(user.get("username"));
        loginRegistrationPage.fillEmailTextField(user.get("email"));
        loginRegistrationPage.fillPasswordTextField(user.get("password"));
        loginRegistrationPage.clickRegisterButton();
    }

    @When("I register user with datatable data:")
    public void registerUserWithDataTableData(DataTable dataTable) {
        Map<String, String> user = dataTable.asMap();
        var loginRegistrationPage = new LoginRegistrationPage(driver);
        loginRegistrationPage.fillNameTextField(user.get("username"));
        loginRegistrationPage.fillEmailTextField(user.get("email"));
        loginRegistrationPage.fillPasswordTextField(user.get("password"));
        loginRegistrationPage.clickRegisterButton();
    }
}

package ru.levelp.at.lesson06.selenium.page.objects.types.voids;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginRegistrationPage extends BasePage {

    private static final String REGISTER_USER_BUG_RED_URL = "/user/login/index.html";

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='name']")
    private WebElement nameTextField;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='act_register_now']")
    private WebElement registerButton;

    @FindBy(xpath = "//form[contains(@action, 'register')]/p")
    private WebElement errorTextLabel;

    public LoginRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        open(REGISTER_USER_BUG_RED_URL);
    }

    public void fillNameTextField(String value) {
        wait.until(ExpectedConditions.visibilityOf(nameTextField)).sendKeys(value);
    }

    public void fillEmailTextField(String value) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField)).sendKeys(value);
    }

    public void fillPasswordTextField(String value) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(value);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public String getErrorTextLabelText() {
        return wait.until(ExpectedConditions.visibilityOf(errorTextLabel)).getText();
    }
}

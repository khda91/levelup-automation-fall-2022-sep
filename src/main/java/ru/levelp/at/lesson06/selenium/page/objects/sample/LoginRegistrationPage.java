package ru.levelp.at.lesson06.selenium.page.objects.sample;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginRegistrationPage {

    private static final String USER_BUG_RED_URL = "http://users.bugred.ru/user/login/index.html";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='name']")
    private WebElement nameTextField;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//form[contains(@action, 'register')]//input[@name='act_register_now']")
    private WebElement registerButton;

    public LoginRegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(USER_BUG_RED_URL);
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
}

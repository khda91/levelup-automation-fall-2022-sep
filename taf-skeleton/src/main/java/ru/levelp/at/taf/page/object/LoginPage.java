package ru.levelp.at.taf.page.object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    private static final String URL = "/login";

    @FindBy(id = "user")
    private WebElement emailTextField;

    @FindBy(id = "login")
    private WebElement continueButton;

    @FindBy(id = "password")
    private WebElement passwordTextField;

    @FindBy(id = "login-submit")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open(URL);
    }

    public void enterEmail(String username) {
        wait.until(ExpectedConditions.visibilityOf(emailTextField)).sendKeys(username);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordTextField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
}

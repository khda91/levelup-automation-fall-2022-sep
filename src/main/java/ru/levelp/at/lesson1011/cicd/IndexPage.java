package ru.levelp.at.lesson1011.cicd;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IndexPage extends BasePage {

    @FindBy(xpath = "//li[@id='fat-menu']/a[@class='dropdown-toggle']")
    private WebElement userMenu;

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        open("/");
    }

    @Step("Получаем имя зарегистрированного пользователя")
    public String getUsernameFromUserMenu() {
        return wait.until(ExpectedConditions.visibilityOf(userMenu)).getText();
    }
}

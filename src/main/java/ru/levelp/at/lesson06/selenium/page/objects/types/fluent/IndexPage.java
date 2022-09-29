package ru.levelp.at.lesson06.selenium.page.objects.types.fluent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IndexPage extends BasePage<IndexPage> {

    @FindBy(xpath = "//li[@id='fat-menu']/a[@class='dropdown-toggle']")
    private WebElement userMenu;

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public IndexPage open() {
        open("/");
        return this;
    }

    public String getUsernameFromUserMenu() {
        return wait.until(ExpectedConditions.visibilityOf(userMenu)).getText();
    }
}

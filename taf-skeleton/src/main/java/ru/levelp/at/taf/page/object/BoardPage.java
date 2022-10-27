package ru.levelp.at.taf.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.taf.page.components.ListColumnComponent;

public class BoardPage extends AbstractPage {

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public ListColumnComponent getList(String listName) {
        String locator = String
            .format("//div[@id='board']//div[contains(@class, 'js-list-content') and //h2[text()='%s']]", listName);
        WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        return new ListColumnComponent(driver, card);
    }
}

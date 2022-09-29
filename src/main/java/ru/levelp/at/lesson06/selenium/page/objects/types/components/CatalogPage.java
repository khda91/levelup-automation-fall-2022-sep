package ru.levelp.at.lesson06.selenium.page.objects.types.components;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends CitilinkBasePage {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public void selectCatalogCategory(String categoryName) {
        List<WebElement> categories = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
            .xpath("//*[@class='CatalogCategoryMenu__category']"), 2));

        for (WebElement category : categories) {
            wait.until(ExpectedConditions.elementToBeClickable(category));
            if (categoryName.equalsIgnoreCase(category.getText())) {
                category.click();
                break;
            }
        }
    }
}

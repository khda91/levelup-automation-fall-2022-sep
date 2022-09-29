package ru.levelp.at.lesson06.selenium.page.objects.types.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComparePage extends CitilinkBasePage {

    public ComparePage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductTitles() {
        List<WebElement> titles = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
            .xpath("//*[contains(@class, 'Compare__product-cell_name')]/a[contains(@class, 'Link')]"), 1));
        return titles.stream()
                     .map(WebElement::getText)
                     .collect(Collectors.toList());
    }
}

package ru.levelp.at.lesson06.selenium.page.objects.types.components.composite;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductComponent {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final WebElement root;

    public ProductComponent(WebDriver driver, WebElement root) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        this.root = root;
    }

    public String getName() {
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, By
                       .xpath(".//*[contains(@class, 'ProductCardHorizontal__title')]")))
                   .getText();
    }

    public void addToCompareList() {
        wait.until(ExpectedConditions.elementToBeClickable(root.findElement(By
            .xpath(".//*[contains(@class, 'ProductCardButton__icon')]")))).click();
    }
}

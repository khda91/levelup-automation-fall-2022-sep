package ru.levelp.at.lesson06.selenium.page.objects.types.components;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class CitilinkBasePage {

    private static final String URL = "https://www.citilink.ru/";

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    @FindBy(xpath = "//*[@data-label='Каталог товаров']")
    private WebElement catalogButton;

    protected CitilinkBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(URL);
    }

    public void clickCatalogButton() {
        wait.until(ExpectedConditions.elementToBeClickable(catalogButton)).click();
    }

    public void selectCatalog(final String catalogName) {
        List<WebElement> catalogs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By
            .xpath("//*[contains(@class, 'CatalogMenu__category-item')]"), 3));

        for (WebElement catalog : catalogs) {
            if (catalogName.equalsIgnoreCase(catalog.getText().trim())) {
                wait.until(ExpectedConditions.elementToBeClickable(catalog)).click();
                break;
            }
        }
    }
}

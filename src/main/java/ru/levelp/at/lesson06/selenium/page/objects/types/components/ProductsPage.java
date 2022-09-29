package ru.levelp.at.lesson06.selenium.page.objects.types.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelp.at.lesson06.selenium.page.objects.types.components.composite.ProductComponent;

public class ProductsPage extends CitilinkBasePage {

    @FindBy(xpath = "//section[contains(@class, 'ProductGroupList')]/div[contains(@class, 'product_data__gtm-js')]")
    private List<WebElement> products;

    @FindBy(xpath = "//*[@data-name='compare']")
    private WebElement compareButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductComponent> getProducts() {
        return products.stream()
                       .map(product -> new ProductComponent(driver, product))
                       .collect(Collectors.toList());
    }

    public void clickCompareButton() {
        wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();
    }
}

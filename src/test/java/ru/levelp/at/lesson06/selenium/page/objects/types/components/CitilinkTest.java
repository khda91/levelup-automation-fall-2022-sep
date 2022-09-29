package ru.levelp.at.lesson06.selenium.page.objects.types.components;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson06.selenium.page.objects.BaseSeleniumTest;
import ru.levelp.at.lesson06.selenium.page.objects.types.components.composite.ProductComponent;

public class CitilinkTest extends BaseSeleniumTest {

    @Test
    public void compareTest() {
        var indexPage = new IndexPage(driver);
        indexPage.open();

        indexPage.clickCatalogButton();
        indexPage.selectCatalog("Смартфоны и гаджеты");

        var catalogPage = new CatalogPage(driver);
        catalogPage.selectCatalogCategory("Смартфоны");

        var productPage = new ProductsPage(driver);
        List<ProductComponent> products = productPage.getProducts();

        var addedProductNames = new ArrayList<String>();
        addedProductNames.add(products.get(0).getName());
        products.get(0).addToCompareList();

        addedProductNames.add(products.get(1).getName());
        products.get(1).addToCompareList();

        productPage.clickCompareButton();

        var comparePage = new ComparePage(driver);
        var actualProductNames = comparePage.getProductTitles();

        assertThat(actualProductNames).containsExactlyInAnyOrderElementsOf(addedProductNames);
    }
}

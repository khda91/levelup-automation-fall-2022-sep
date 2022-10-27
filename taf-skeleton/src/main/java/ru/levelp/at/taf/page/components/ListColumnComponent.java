package ru.levelp.at.taf.page.components;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.levelp.at.taf.configuration.WebSiteConfiguration;

public class ListColumnComponent {

    private static final By ADD_A_CARD_BUTTON_LOCATOR = By.xpath(".//a/span[text()='Add a card']");
    private static final By CARD_NAME_TEXTAREA_LOCATOR =
        By.xpath("//textarea[contains(@class, 'list-card-composer-textarea')]");
    private static final By ADD_CARD_BUTTON_LOCATOR = By.xpath(".//input[@value='Add card']");

    private static final By LIST_CARDS_LOCATOR = By.xpath("//div[contains(@class, 'list-cards')]/a[contains(@class, 'list-card')]");

    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement root;

    public ListColumnComponent(WebDriver driver, WebElement root) {
        this.driver = driver;
        this.root = root;
        wait = new WebDriverWait(driver, Duration.ofMillis(WebSiteConfiguration.getInstance().waitTimeout()));
    }

    public void clickAddACardButton() {
        WebElement addCardButton = wait.until(ExpectedConditions
            .presenceOfNestedElementLocatedBy(root, ADD_A_CARD_BUTTON_LOCATOR));
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();
    }

    public void enterCardName(String cardName) {
        WebElement cardNameTextArea = wait.until(ExpectedConditions
            .presenceOfNestedElementLocatedBy(root, CARD_NAME_TEXTAREA_LOCATOR));
        wait.until(ExpectedConditions.visibilityOf(cardNameTextArea)).sendKeys(cardName);
    }

    public void clickAddCardButton() {
        WebElement addCardButton = wait.until(ExpectedConditions
            .presenceOfNestedElementLocatedBy(root, ADD_CARD_BUTTON_LOCATOR));
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();
    }

    public List<String> getAllCardsTitle() {
        List<WebElement> cards =
            wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(root, LIST_CARDS_LOCATOR));
        return cards.stream()
                    .map(card -> wait.until(ExpectedConditions.visibilityOf(card)).getText())
                    .collect(Collectors.toList());
    }
}

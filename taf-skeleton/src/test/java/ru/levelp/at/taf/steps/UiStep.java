package ru.levelp.at.taf.steps;

import org.openqa.selenium.WebDriver;
import ru.levelp.at.taf.page.components.ListColumnComponent;
import ru.levelp.at.taf.page.object.BoardPage;
import ru.levelp.at.taf.page.object.BoardsPage;
import ru.levelp.at.taf.page.object.LoginPage;
import ru.levelp.at.taf.service.webdriver.WebDriverSingleton;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UiStep {

    private LoginPage loginPage;
    private BoardsPage boardsPage;
    private BoardPage boardPage;

    public UiStep() {
        WebDriver driver = WebDriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        boardsPage = new BoardsPage(driver);
        boardPage = new BoardPage(driver);
    }

    public void openLoginPage() {
        loginPage.open();
    }

    public void login(String username, String password) {
        loginPage.enterEmail(username);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    public void openBoard(String boardName) {
        boardsPage.openBoard(boardName);
    }

    public void addCard(String listName, String cardTitle) {
        ListColumnComponent toDoList = boardPage.getList(listName);
        toDoList.clickAddACardButton();
        toDoList.enterCardName(cardTitle);
        toDoList.clickAddCardButton();
    }

    public void cardShouldBeAddedToBoard(String listName, String cardTitle) {
        List<String> cardsTitle = boardPage.getList(listName).getAllCardsTitle();
        assertThat(cardsTitle).contains(cardTitle);
    }
}

package ru.levelp.at.taf.page.object;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BoardsPage extends AbstractPage {

    public BoardsPage(WebDriver driver) {
        super(driver);
    }

    public void openBoard(String boardName) {
        List<WebElement> boards = wait.until(ExpectedConditions
            .numberOfElementsToBeMoreThan(By
                .xpath("//ul[@class='boards-page-board-section-list']/li"), 0));

        for (WebElement board : boards) {
            if (board.getText().contains(boardName)) {
                wait.until(ExpectedConditions.elementToBeClickable(board)).click();
                break;
            }
        }
    }
}

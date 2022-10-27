package ru.levelp.at.taf.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.levelp.at.taf.configuration.WebSiteConfiguration;
import ru.levelp.at.taf.service.api.model.BoardModel;

public class TrelloBoardTest extends AbstractBaseTest {

    @Test
    void boardTest() {
        final var boardName = new Faker().funnyName().name();
        boardClient.createBoard(BoardModel.builder().name(boardName).build());

        WebSiteConfiguration config = WebSiteConfiguration.getInstance();
        uiStep.openLoginPage();
        uiStep.login(config.username(), config.password());
        uiStep.openBoard(boardName);

        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

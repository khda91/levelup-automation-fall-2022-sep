package ru.levelp.at.taf.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import ru.levelp.at.taf.configuration.WebSiteConfiguration;

public class TrelloAddCardTest extends AbstractBaseTest {

    @Test
    void addCard() {
        final var cardName = new Faker().funnyName().name();

        WebSiteConfiguration config = WebSiteConfiguration.getInstance();
        uiStep.openLoginPage();
        uiStep.login(config.username(), config.password());
        uiStep.openBoard("Test Board");
        uiStep.addCard("To Do", cardName);
        uiStep.cardShouldBeAddedToBoard("To Do", cardName);
    }
}

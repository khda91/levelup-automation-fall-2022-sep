package ru.levelp.at.lesson13.bdd.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Then;
import ru.levelp.at.lesson13.bdd.IndexPage;

public class MainPageSteps extends AbstractStep {

    @Then("displayed username should be equal to {string} on the Main page")
    public void displayedUsernameShouldBeEqual(String expectedUsername) {
        var actualUsername = new IndexPage(driver).getUsernameFromUserMenu();
        assertThat(actualUsername).isEqualToIgnoringCase(expectedUsername);
    }
}

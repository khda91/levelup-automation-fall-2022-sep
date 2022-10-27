package ru.levelp.at.taf.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import ru.levelp.at.taf.configuration.ApiConfiguration;
import ru.levelp.at.taf.service.api.client.BoardClient;
import ru.levelp.at.taf.service.webdriver.WebDriverSingleton;
import ru.levelp.at.taf.steps.UiStep;
import java.util.Map;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractBaseTest {

    protected UiStep uiStep;
    protected BoardClient boardClient;

    @BeforeEach
    void restAssureSetUp() {
        var apiConfig = ApiConfiguration.getInstance();
        RestAssured.baseURI = apiConfig.url();
        RestAssured.basePath = apiConfig.version();

        var requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .addQueryParams(Map.of("key", apiConfig.key(),
                "token", apiConfig.token()))
            .build();

        var responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        boardClient = new BoardClient();
    }

    @BeforeEach
    void setUp() {
        WebDriverSingleton.getDriver();
        uiStep = new UiStep();
    }

    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebDriverSingleton.closeDriver();
    }
}

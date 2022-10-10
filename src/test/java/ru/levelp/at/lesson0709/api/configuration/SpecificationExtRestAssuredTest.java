package ru.levelp.at.lesson0709.api.configuration;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpecificationExtRestAssuredTest {

    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/srv-person-profile";

        requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    }

    @Test
    void addMessenger() {
        var faker = new Faker();
        var id = faker.funnyName().name().replaceAll("\\s+", "_");

        RestAssured
            .given()
            .pathParam("messengerId", id)
            .spec(requestSpecification)
            .when()
            .put("/messengers/{messengerId}")
            .then()
            .spec(responseSpecification)
            .statusCode(400)
            .body("type", Matchers.equalTo("validation"))
            .body("title", Matchers.equalTo("Bad Request"))
            .body("status", Matchers.equalTo("400"))
            .body("detail", Matchers.equalTo("A malformed request was sent"))
            .body("violations.code", Matchers.hasItem("invalid_field"))
            .body("violations.field", Matchers.hasItem("messengerId"))
            .body("violations.detail", Matchers.hasItem("must match \"([A-Z0-9_]*)\""));
    }

    @Test
    void addSocialNetwork() {
        var faker = new Faker();
        var id = faker.funnyName().name().replaceAll("\\s+", "_");

        RestAssured
            .given()
            .pathParam("socialNetworkId", id)
            .spec(requestSpecification)
            .when()
            .put("/social-networks/{socialNetworkId}")
            .then()
            .spec(responseSpecification)
            .statusCode(400)
            .body("type", Matchers.equalTo("validation"))
            .body("title", Matchers.equalTo("Bad Request"))
            .body("status", Matchers.equalTo("400"))
            .body("detail", Matchers.equalTo("A malformed request was sent"))
            .body("violations.code", Matchers.hasItem("invalid_field"))
            .body("violations.field", Matchers.hasItem("socialNetworkId"))
            .body("violations.detail", Matchers.hasItem("must match \"([A-Z0-9_]*)\""));
    }

    @Test
    void addMessengerCommonResponseSpec() {
        var faker = new Faker();
        var id = faker.funnyName().name().replaceAll("\\s+", "_");

        RestAssured
            .given()
            .pathParam("messengerId", id)
            .spec(requestSpecification)
            .when()
            .put("/messengers/{messengerId}")
            .then()
            .spec(badRequestResponseSpec("messengerId"));
    }

    @Test
    void addSocialNetworkCommonResponseSpec() {
        var faker = new Faker();
        var id = faker.funnyName().name().replaceAll("\\s+", "_");

        RestAssured
            .given()
            .pathParam("socialNetworkId", id)
            .spec(requestSpecification)
            .when()
            .put("/social-networks/{socialNetworkId}")
            .then()
            .spec(badRequestResponseSpec("socialNetworkId"));
    }

    @Test
    void deleteSocialNetworkCommonResponseSpec() {
        var faker = new Faker();
        var id = faker.funnyName().name().replaceAll("\\s+", "_");

        RestAssured
            .given()
            .pathParam("socialNetworkId", id)
            .spec(requestSpecification)
            .when()
            .delete("/social-networks/{socialNetworkId}")
            .then()
            .spec(badRequestResponseSpec("socialNetworkId"));
    }

    @Test
    void addSocialNetworkOneLetter() {
        RestAssured
            .given()
            .pathParam("socialNetworkId", "Y")
            .spec(requestSpecification)
            .when()
            .put("/social-networks/{socialNetworkId}")
            .then()
            .spec(badRequestResponseSpec("socialNetworkId"));
    }


    private ResponseSpecification badRequestResponseSpec(final String fieldName) {
        return new ResponseSpecBuilder()
            //            .log(LogDetail.ALL)
            .addResponseSpecification(responseSpecification)
            .expectStatusCode(400)
            .expectBody("type", Matchers.equalTo("validation"))
            .expectBody("title", Matchers.equalTo("Bad Request"))
            .expectBody("status", Matchers.equalTo("400"))
            .expectBody("detail", Matchers.equalTo("A malformed request was sent"))
            .expectBody("violations.code", Matchers.hasItem("invalid_field"))
            .expectBody("violations.field", Matchers.hasItem(fieldName))
            .expectBody("violations.detail", Matchers.hasItem("must match \"([A-Z0-9_]*)\""))
            .build();
    }
}

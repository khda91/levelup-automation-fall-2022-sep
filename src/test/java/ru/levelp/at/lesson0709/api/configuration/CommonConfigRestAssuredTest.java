package ru.levelp.at.lesson0709.api.configuration;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import java.util.Map;
import java.util.stream.Stream;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CommonConfigRestAssuredTest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/srv-person-profile";
    }

    @Test
    void getMessengers() {

        RestAssured
            .given()
            .log().all()
            .queryParams(Map.of("limit", 1, "offset", 2))
            .when()
            .get("/messengers")
            .then()
            .log().all()
            .statusCode(200)
            .body("data.id", Matchers.hasItems("WHATS_UP"),
                "meta.pagination.limit", Matchers.equalTo(1),
                "meta.pagination.offset", Matchers.equalTo(2),
                "meta.pagination.totalCount", Matchers.equalTo(3));
    }

    @Test
    void addSocialNetwork() {
        var faker = new Faker();
        var id = faker.funnyName().name().replaceAll("\\s+", "_").toUpperCase();

        RestAssured
            .given()
            .pathParam("socialNetworkId", id)
            .log().all()
            .when()
            .put("/social-networks/{socialNetworkId}")
            .then()
            .log().all()
            .statusCode(204);

        RestAssured
            .given()
            .log().all()
            .when()
            .get("/social-networks")
            .then()
            .log().all()
            .statusCode(200)
            .body("data.id", Matchers.hasItems(id));
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void getMessengersDataProvider(Integer limit, Integer offset, String expectedValue) {

        RestAssured
            .given()
            .log().all()
            .queryParams(Map.of("limit", limit, "offset", offset))
            .when()
            .get("/messengers")
            .then()
            .log().all()
            .statusCode(200)
            .body("data.id", Matchers.hasItems(expectedValue),
                "meta.pagination.limit", Matchers.equalTo(limit),
                "meta.pagination.offset", Matchers.equalTo(offset),
                "meta.pagination.totalCount", Matchers.equalTo(3));
    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
            Arguments.of(1, 2, "WHATS_UP"),
            Arguments.of(1, 1, "VIBER")
        );
    }
}

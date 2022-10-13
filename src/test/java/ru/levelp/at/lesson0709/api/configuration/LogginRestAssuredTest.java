package ru.levelp.at.lesson0709.api.configuration;

import io.restassured.RestAssured;
import java.util.Map;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class LogginRestAssuredTest {

    @Test
    void getMessengers() {

        RestAssured
            .given()
            .log().all()
            .queryParams(Map.of("limit", 1, "offset", 2))
            .when()
            .get("http://localhost:8080/srv-person-profile/messengers")
            .then()
            .log().all()
            .statusCode(200)
            .body("data.id", Matchers.hasItems("WHATS_UP"),
                "meta.pagination.limit", Matchers.equalTo(10),
                "meta.pagination.offset", Matchers.equalTo(2),
                "meta.pagination.totalCount", Matchers.equalTo(5));
    }

    @Test
    void addSocialNetwork() {

        RestAssured
            .given()
            .pathParam("socialNetworkId", "ODNOKLASSNIKI")
            .log().all()
            .when()
            .put("http://localhost:8080/srv-person-profile/social-networks/{socialNetworkId}")
            .then()
            .log().all()
            .statusCode(204);

        RestAssured
            .given()
            .log().all()
            .when()
            .get("http://localhost:8080/srv-person-profile/social-networks")
            .then()
            .log().all()
            .statusCode(200)
            .body("data.id", Matchers.hasItems("ODNOKLASSNIKI"));
    }
}

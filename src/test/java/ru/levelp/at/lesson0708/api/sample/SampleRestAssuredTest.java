package ru.levelp.at.lesson0708.api.sample;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class SampleRestAssuredTest {

    @Test
    void getMessengersShouldReturnOkStatusCode() {

        RestAssured.when()
                   .get("http://localhost:8080/srv-person-profile/messengers")
                   .then()
                   .statusCode(200);
    }

    @Test
    void getMessengersShouldReturnResponseBody() {

        RestAssured.when()
                   .get("http://localhost:8080/srv-person-profile/messengers")
                   .then()
                   .statusCode(200)
                   .body("data.id", Matchers.hasItems("VIBER", "TELEGRAM", "WHATS_UP"))
                   .body("meta.pagination.limit", Matchers.equalTo(10))
                   .body("meta.pagination.offset", Matchers.equalTo(0))
                   .body("meta.pagination.totalCount", Matchers.equalTo(3));
    }

    @Test
    void getMessengersShouldUseLimitOffset() {

        RestAssured
            .given()
            .queryParams(Map.of("limit", 1, "offset", 2))
            .when()
            .get("http://localhost:8080/srv-person-profile/messengers")
            .then()
            .statusCode(200)
            .body("data.id", Matchers.hasItems("WHATS_UP"))
            .body("meta.pagination.limit", Matchers.equalTo(1))
            .body("meta.pagination.offset", Matchers.equalTo(2))
            .body("meta.pagination.totalCount", Matchers.equalTo(3));
    }

    @Test
    void getMessengersSoftAssertion() {

        RestAssured
            .given()
            .queryParams(Map.of("limit", 1, "offset", 2))
            .when()
            .get("http://localhost:8080/srv-person-profile/messengers")
            .then()
            .statusCode(200)
            .body("data.id", Matchers.hasItems("WHATS_UP"),
                "meta.pagination.limit", Matchers.equalTo(10),
                "meta.pagination.offset", Matchers.equalTo(2),
                "meta.pagination.totalCount", Matchers.equalTo(5));
    }
}

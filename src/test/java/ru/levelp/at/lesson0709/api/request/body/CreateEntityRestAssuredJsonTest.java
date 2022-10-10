package ru.levelp.at.lesson0709.api.request.body;

import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateEntityRestAssuredJsonTest {

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
    void createPersonFromFile() {
        var email = new Faker().internet().emailAddress();

        String json = "";
        try {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(
                new FileInputStream("src/test/resources/ru/levelp/at"
                    + "/lesson0708/api/requests/createUserJson.json"), StandardCharsets.UTF_8.toString());
            json = JsonPath.parse(document).set("$.email", email).jsonString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(json);
        System.out.println();
        System.out.println();

        given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(json)
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("data.id", Matchers.not(Matchers.emptyString()))
            .body("data.email", Matchers.equalTo(email));
    }

    @Test
    void createPersonWithoutField() {
        var email = new Faker().internet().emailAddress();

        String json = "";
        try {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(
                new FileInputStream("src/test/resources/ru/levelp/at"
                    + "/lesson0708/api/requests/createUserJson.json"), StandardCharsets.UTF_8.toString());
            json = JsonPath.parse(document)
                           .set("$.email", email)
                           .delete("$.identity")
                           .jsonString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(json);
        System.out.println();
        System.out.println();

        given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(json)
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("data.id", Matchers.not(Matchers.emptyString()))
            .body("data.email", Matchers.equalTo(email));
    }

    @Test
    void createPersonWithNewField() {
        var email = new Faker().internet().emailAddress();

        String json = "";
        try {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(
                new FileInputStream("src/test/resources/ru/levelp/at"
                    + "/lesson0708/api/requests/createUserJson.json"), StandardCharsets.UTF_8.toString());
            json = JsonPath.parse(document)
                           .set("$.email", email)
                           .delete("$.identity")
                           .put("$.address", "newField", "value")
                           .jsonString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(json);
        System.out.println();
        System.out.println();

        given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(json)
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED);
    }
}

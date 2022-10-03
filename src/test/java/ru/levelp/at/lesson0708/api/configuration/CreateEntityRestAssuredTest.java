package ru.levelp.at.lesson0708.api.configuration;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateEntityRestAssuredTest {

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
    void createPerson() {
        var email = new Faker().internet().emailAddress();
        given()
            .spec(requestSpecification)
            // .header("Content-Type", "application/json")
            .contentType(ContentType.JSON)
            .body("{\n"
                + "  \"role\": \"LECTOR\",\n"
                + "  \"email\": \"" + email + "\",\n"
                + "  \"phoneNumber\": \"+79211234567\",\n"
                + "  \"placeOfWork\": \"Engineer\",\n"
                + "  \"identity\": {\n"
                + "    \"firstName\": \"Vasily\",\n"
                + "    \"lastName\": \"Pupkin\",\n"
                + "    \"middleName\": \"Ivanovich\",\n"
                + "    \"gender\": \"MALE\",\n"
                + "    \"dateOfBirth\": \"1980-02-07\",\n"
                + "    \"placeOfBirth\": \"Moscow\",\n"
                + "    \"passport\": {\n"
                + "      \"series\": \"1234\",\n"
                + "      \"number\": \"123456\",\n"
                + "      \"placeOfIssue\": \"\",\n"
                + "      \"dateOfIssue\": \"1980-02-07\",\n"
                + "      \"departmentCode\": \"123-456\"\n"
                + "    }\n"
                + "  },\n"
                + "  \"address\": {\n"
                + "    \"street\": \"Beethovenstrasse\",\n"
                + "    \"houseNumber\": 12,\n"
                + "    \"houseBuilding\": 1,\n"
                + "    \"houseLetter\": \"A\",\n"
                + "    \"flat\": 123,\n"
                + "    \"city\": \"Moscow\",\n"
                + "    \"postalCode\": \"123456\"\n"
                + "  }\n"
                + "}")
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("data.id", Matchers.not(Matchers.emptyString()))
            .body("data.email", Matchers.equalTo(email));
    }
}

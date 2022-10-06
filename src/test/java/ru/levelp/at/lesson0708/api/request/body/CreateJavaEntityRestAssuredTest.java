package ru.levelp.at.lesson0708.api.request.body;

import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.time.LocalDate;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson0708.api.model.CreatePersonData;
import ru.levelp.at.lesson0708.api.model.IdentityData;
import ru.levelp.at.lesson0708.api.model.PassportData;
import ru.levelp.at.lesson0708.api.model.PersonResponse;
import ru.levelp.at.lesson0708.api.model.ProblemResponse;

public class CreateJavaEntityRestAssuredTest {

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
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(createPersonData)
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .body("data.id", Matchers.not(Matchers.emptyString()))
            .body("data.email", Matchers.equalTo(createPersonData.getEmail()));
    }

    @Test
    void createPersonResponseToObject() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .role("WORK_INSPECTOR")
            .email(faker.internet().emailAddress())
            .phoneNumber(faker.phoneNumber().cellPhone())
            .placeOfWork(faker.company().name())
            .identity(IdentityData.builder()
                                  .firstName(faker.name().firstName())
                                  .lastName(faker.name().lastName())
                                  .middleName(faker.name().nameWithMiddle())
                                  .dateOfBirth(LocalDate.now().minusYears(25).toString())
                                  .gender("FEMALE")
                                  .placeOfBirth(faker.country().name())
                                  .passport(PassportData.builder()
                                                        .series("7898")
                                                        .number("401287")
                                                        .dateOfIssue(LocalDate.now().minusYears(20).toString())
                                                        .placeOfIssue(faker.address().fullAddress())
                                                        .departmentCode("785-999")
                                                        .build())
                                  .build())
            .build();

        PersonResponse response = given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(createPersonData)
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .as(PersonResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });
    }

    @Test
    void createPersonWithoutMandatoryFields() {
        var faker = new Faker();
        var createPersonData = CreatePersonData
            .builder()
            .placeOfWork(faker.company().name())
            .build();

        ProblemResponse response = given()
            .spec(requestSpecification)
            .contentType(ContentType.JSON)
            .body(createPersonData)
            .when()
            .post("/people")
            .then()
            .spec(responseSpecification)
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .extract()
            .as(ProblemResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getType()).isEqualTo("validation");
            softly.assertThat(response.getStatus()).isEqualTo("400");
            softly.assertThat(response.getViolations()).hasSize(3);
        });
    }
}

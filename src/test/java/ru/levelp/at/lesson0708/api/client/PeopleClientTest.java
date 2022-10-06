package ru.levelp.at.lesson0708.api.client;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson0708.api.model.CreatePersonData;
import ru.levelp.at.lesson0708.api.model.IdentityData;
import ru.levelp.at.lesson0708.api.model.PassportData;
import ru.levelp.at.lesson0708.api.model.PeopleListResponse;
import ru.levelp.at.lesson0708.api.model.PersonResponse;
import ru.levelp.at.lesson0708.api.model.ProblemResponse;

public class PeopleClientTest {

    private PeopleClient peopleClient;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/srv-person-profile";

        var requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build();

        var responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        peopleClient = new PeopleClient();
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

        PersonResponse response = peopleClient.createPerson(createPersonData)
                                              .then()
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

        ProblemResponse response = peopleClient.createPerson(createPersonData)
                                               .then()
                                               .statusCode(HttpStatus.SC_BAD_REQUEST)
                                               .extract()
                                               .as(ProblemResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getType()).isEqualTo("validation");
            softly.assertThat(response.getStatus()).isEqualTo("400");
            softly.assertThat(response.getViolations()).hasSize(3);
        });
    }

    @Test
    void getPeopleTest() {
        PeopleListResponse response = peopleClient.getPeople()
                                                  .then()
                                                  .statusCode(HttpStatus.SC_OK)
                                                  .extract()
                                                  .as(PeopleListResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData()).isNotEmpty();
        });
    }

    @Test
    void getPeopleWithQueryParamsTest() {
        PeopleListResponse response = peopleClient.getPeople(Map.of("limit", 1))
                                                  .then()
                                                  .statusCode(HttpStatus.SC_OK)
                                                  .extract()
                                                  .as(PeopleListResponse.class);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData()).isNotEmpty();
            softly.assertThat(response.getData()).hasSize(1);
            softly.assertThat(response.getMeta().getPagination().getLimit()).isEqualTo(1);
        });
    }

    @Test
    void getPersonMessengers() {
        peopleClient.getPersonMessengers("ec9655d2-e35d-4f45-be81-7ed4abc7cdc9")
                    .then()
                    .statusCode(HttpStatus.SC_OK);
    }
}

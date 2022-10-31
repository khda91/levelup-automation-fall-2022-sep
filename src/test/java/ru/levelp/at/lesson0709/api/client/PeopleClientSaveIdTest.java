package ru.levelp.at.lesson0709.api.client;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.levelp.at.lesson0709.api.model.CreatePersonData;
import ru.levelp.at.lesson0709.api.model.IdentityData;
import ru.levelp.at.lesson0709.api.model.PassportData;
import ru.levelp.at.lesson0709.api.model.PersonResponse;
import ru.levelp.at.lesson13.bdd.context.TestContext;

import java.time.LocalDate;

public class PeopleClientSaveIdTest {

    static String savedPersonId;

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

        savedPersonId = response.getData().getId();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.getData().getId()).isNotEmpty();
            softly.assertThat(response.getData().getEmail()).isEqualTo(createPersonData.getEmail());
            softly.assertThat(response.getData().getRole()).isEqualTo(createPersonData.getRole());
        });
    }

    @Test
    void createPersonResponseRestAssured() {
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

        String id = peopleClient.createPerson(createPersonData)
                                .then()
                                .statusCode(HttpStatus.SC_CREATED)
                                .extract()
                                .body().jsonPath().getString("data.id");

        TestContext.getInstance().addObject("createdPersonId", id);
        System.out.println("createdPersonId -> " + TestContext.getInstance().getObject("createdPersonId"));
    }
}

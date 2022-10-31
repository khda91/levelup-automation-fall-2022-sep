package ru.levelp.at.lesson0709.api.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.lesson0709.api.model.CreatePersonData;
import ru.levelp.at.lesson0709.api.model.ProblemResponse;
import ru.levelp.at.lesson0709.api.model.ViolationData;

public class NegativePeopleClientTest {

    private PeopleClient peopleClient;

    static Stream<Arguments> negativeDataProvider() {
        var faker = new Faker();
        return Stream.of(
            Arguments.of(CreatePersonData
                .builder()
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .build(), ProblemResponse.builder()
                                         .type("validation")
                                         .title("Bad Request")
                                         .status("400")
                                         .detail("A malformed request was sent")
                                         .violations(List.of(ViolationData.builder()
                                                                          .code("missing_field")
                                                                          .field("CreatePersonData.role")
                                                                          .detail("must not be null")
                                                                          .build()))
                                         .build()),
            Arguments.of(CreatePersonData
                .builder()
                .role("WORK_INSPECTOR")
                .phoneNumber(faker.phoneNumber().cellPhone())
                .build(), ProblemResponse.builder()
                                         .type("validation")
                                         .title("Bad Request")
                                         .status("400")
                                         .detail("A malformed request was sent")
                                         .violations(List.of(ViolationData.builder()
                                                                          .code("missing_field")
                                                                          .field("CreatePersonData.email")
                                                                          .detail("must not be null")
                                                                          .build()))
                                         .build()),
            Arguments.of(CreatePersonData
                .builder()
                .role("WORK_INSPECTOR")
                .email(faker.internet().emailAddress())
                .build(), ProblemResponse.builder()
                                         .type("validation")
                                         .title("Bad Request")
                                         .status("400")
                                         .detail("A malformed request was sent")
                                         .violations(List.of(ViolationData.builder()
                                                                          .code("missing_field")
                                                                          .field("CreatePersonData.phoneNumber")
                                                                          .detail("must not be null")
                                                                          .build()))
                                         .build()),
            Arguments.of(CreatePersonData
                .builder()
                .role("WORK_INSPECTOR123")
                .email(faker.internet().emailAddress())
                .phoneNumber(faker.phoneNumber().cellPhone())
                .build(), ProblemResponse.builder()
                                         .type("validation")
                                         .title("Bad Request")
                                         .status("400")
                                         .detail("A malformed request was sent")
                                         .violations(List
                                             .of(ViolationData.builder()
                                                              .code("invalid_field")
                                                              .field("CreatePersonData.role")
                                                              .detail("Unexpected value 'WORK_INSPECTOR123'")
                                                              .build()))
                                         .build())
        );
    }

    static Stream<Arguments> negativeDataProviderString() {
        var faker = new Faker();
        return Stream.of(
            Arguments.of(null, faker.internet().emailAddress(), faker.phoneNumber().cellPhone(),
                "validation", "Bad Request", "400"),
            Arguments.of("WORK_INSPECTOR", null, faker.phoneNumber().cellPhone(),
                "validation", "Bad Request", "400"),
            Arguments.of("WORK_INSPECTOR123", faker.internet().emailAddress(),
                faker.phoneNumber().cellPhone(), "validation", "Bad Request", "400"),
            Arguments.of("WORK_INSPECTOR123", faker.internet().emailAddress(),
                null, "validation", "Bad Request", "400"),
            Arguments.of(null, null, null, "validation", "Bad Request", "400")
        );
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/srv-person-profile";

        var requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build()
            .headers(Map.of("CUSTOM_HEADER", "VALUE"));

        var responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        peopleClient = new PeopleClient();
    }

    @ParameterizedTest
    @MethodSource("negativeDataProvider")
    void createPersonResponseToObject(CreatePersonData request, ProblemResponse expectedResponse) {
        ProblemResponse actualResponse = peopleClient.createPerson(request)
                                                     .then()
                                                     .statusCode(HttpStatus.SC_BAD_REQUEST)
                                                     .extract()
                                                     .as(ProblemResponse.class);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @ParameterizedTest
    @MethodSource("negativeDataProviderString")
    void createPersonResponseString(String role, String email, String phoneNumber,
                                    String errorType, String errorTitle, String errorStatus) {
        var request = CreatePersonData
            .builder()
            .role(role)
            .email(email)
            .phoneNumber(phoneNumber)
            .build();

        peopleClient.createPerson(request)
                    .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
            .body("type", Matchers.equalTo(errorType))
            .body("title", Matchers.equalTo(errorTitle))
            .body("status", Matchers.equalTo(errorStatus));
    }

    @AfterEach
    void tearDown() {
        RestAssured.requestSpecification = new RequestSpecBuilder().build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }
}

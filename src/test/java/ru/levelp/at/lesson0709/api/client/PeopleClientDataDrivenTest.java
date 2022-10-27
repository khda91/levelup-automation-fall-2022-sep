package ru.levelp.at.lesson0709.api.client;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.levelp.at.lesson0709.api.model.CreatePersonData;
import ru.levelp.at.lesson0709.api.model.PaginationData;
import ru.levelp.at.lesson0709.api.model.PeopleListResponse;
import ru.levelp.at.lesson0709.api.model.PersonData;
import ru.levelp.at.lesson0709.api.model.PersonResponse;

public class PeopleClientDataDrivenTest {

    private PeopleClient peopleClient;

    static Stream<Arguments> paginationDataProvider() {
        return Stream.of(
            Arguments.of(Map.of("limit", 5), PaginationData.builder()
                                                           .limit(5)
                                                           .offset(0)
                                                           .totalCount(1)
                                                           .build()),
            Arguments.arguments(Map.of("offset", 10), PaginationData.builder()
                                                                    .limit(0)
                                                                    .offset(10)
                                                                    .totalCount(1)
                                                                    .build()),
            Arguments.arguments(Map.of("offset", 10,
                "limit", 1), PaginationData.builder()
                                           .limit(1)
                                           .offset(10)
                                           .totalCount(1)
                                           .build())
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
            .build();

        var responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        peopleClient = new PeopleClient();
    }

    @ParameterizedTest
    @MethodSource("paginationDataProvider")
    void paginationTest(Map<String, Object> queryParams, PaginationData expectedPagination) {
        Response response = peopleClient.getPeople(queryParams);
        PeopleListResponse people = response.then()
                                            .statusCode(200)
                                            .extract().as(PeopleListResponse.class);

        assertThat(people.getMeta().getPagination()).isEqualTo(expectedPagination);
    }

    @Test
    void getPersonTest() {
        var personId = peopleClient.createPerson(CreatePersonData.builder()
                        .role("STUDENT")
                        .email(new Faker().internet().emailAddress())
                        .phoneNumber(new Faker().phoneNumber().cellPhone())
                                                  .build()).as(PersonResponse.class).getData().getId();

        var response = peopleClient.getPerson(personId)
                                    .then()
                                    .statusCode(200)
                                    .extract()
                                    .as(PersonResponse.class);

        System.out.println(response);
        // тут должны быть ассёрты
        // не забыть в конце теста удалить пользователя из БД
        // лучше конечно удалять в @AfterEach || @AfterAll методах
    }
}

package ru.levelp.at.lesson0709.api.client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import java.util.Collections;
import java.util.Map;
import ru.levelp.at.lesson0709.api.model.CreatePersonData;

public class PeopleClient {

    private static final String PEOPLE = "/people";
    private static final String PERSON = PEOPLE + "/{personId}";
    private static final String MESSENGERS = PERSON + "/messengers";

    public Response createPerson(CreatePersonData createPersonData) {
        return given()
            .body(createPersonData)
            .when()
            .post(PEOPLE)
            .thenReturn();
    }

    public Response getPerson(String id) {
        return given()
            .pathParam("personId", id)
            .when()
            .get(PERSON)
            .andReturn();
    }

    public Response getPeople() {
        return getPeople(Collections.emptyMap());
        //        return when()
        //            .get(PEOPLE)
        //            .thenReturn();
    }

    public Response getPeople(Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .when()
            .get(PEOPLE)
            .thenReturn();
    }

    public Response getPersonMessengers(final String personId) {
        return getPersonMessengers(personId, Collections.emptyMap());
    }

    public Response getPersonMessengers(final String personId, Map<String, Object> queryParams) {
        return given()
            .queryParams(queryParams)
            .when()
            .get(MESSENGERS, personId)
            .thenReturn();
    }
}

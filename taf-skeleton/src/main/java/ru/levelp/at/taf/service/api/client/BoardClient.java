package ru.levelp.at.taf.service.api.client;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import ru.levelp.at.taf.service.api.model.BoardModel;

public class BoardClient {

    private static final String BOARDS = "/boards";

    public Response createBoard(BoardModel body) {
        return given()
            .body(body)
            .when()
            .post(BOARDS)
            .andReturn();
    }
}

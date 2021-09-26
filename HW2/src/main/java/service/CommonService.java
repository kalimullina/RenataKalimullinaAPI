package service;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Properties;
import lombok.SneakyThrows;

public class CommonService {

    private RequestSpecification REQUEST_SPECIFICATION;

    @SneakyThrows
    private Properties getProperties() {
        Properties props = new Properties();
        String propFileName = "test.properties";
        props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
        return props;
    }

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri("https://api.trello.com/")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build()
            .queryParam("key", getProperties().get("key").toString())
            .queryParam("token", getProperties().get("token").toString());

    }

    public Response getNoParams(String uri) {
        return given(REQUEST_SPECIFICATION)
            .get(uri);
    }

    public Response postCreateBoard(String uri, String boardName) {
        return given(REQUEST_SPECIFICATION)
            .queryParam("name", boardName)
            .body("")
            .post(uri)
            .then()
            .extract().response();
    }

    public Response deleteBoard(String uri, String boardId) {
        return given(REQUEST_SPECIFICATION)
            .queryParam("id", boardId)
            .body("")
            .delete(uri)
            .then()
            .extract().response();
    }

    public Response postCreateList(String uri, String boardId, String listName) {
        return given(REQUEST_SPECIFICATION)
            .queryParam("idBoard", boardId)
            .queryParam("name", listName)
            .body("")
            .post(uri)
            .then()
            .extract().response();
    }

    public Response putListToAnotherBoard(String uri, String boardIdTo) {
        return given(REQUEST_SPECIFICATION)
            .queryParam("value", boardIdTo)
            .body("")
            .put(uri)
            .then()
            .extract().response();
    }

    public Response putNewListName(String uri, String newListName) {
        return given(REQUEST_SPECIFICATION)
            .queryParam("name", newListName)
            .body("")
            .put(uri)
            .then()
            .extract().response();
    }

    public Response getAllBoards(String uri) {
        return given(REQUEST_SPECIFICATION)
            .get(uri);
    }
}

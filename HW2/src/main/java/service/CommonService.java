package service;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class CommonService extends ServiceBase{

    private RequestSpecification request_specification;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        request_specification = new RequestSpecBuilder()
            .setBaseUri("https://api.trello.com/")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build()
            .queryParam("key", property.get("key"))
            .queryParam("token", property.get("token"));

    }

    public Response getNoParams(String uri) {
        return given(request_specification)
            .get(uri);
    }

    public Response postWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(request_specification);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.queryParam(param.getKey(), param.getValue());

        return specification
            .body("")
            .post(uri)
            .then()
            .extract().response();
    }

    public Response deleteNoParams(String uri) {
        return given(request_specification)
            .delete(uri)
            .then()
            .extract().response();
    }

    public Response putWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(request_specification);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.queryParam(param.getKey(), param.getValue());

        return specification
            .put(uri)
            .then()
            .extract().response();
    }
}

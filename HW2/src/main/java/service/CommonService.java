package service;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import utils.PropertyReader;

public class CommonService {

    private RequestSpecification requestSpecification;

    public CommonService() {
        PropertyReader property = new PropertyReader();        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri(property.getDomain())
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build()
            .queryParam("key", property.getKey())
            .queryParam("token", property.getToken());
    }

    public Response getNoParams(String uri) {
        return given(requestSpecification)
            .get(uri);
    }

    public Response postWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(requestSpecification);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.queryParam(param.getKey(), param.getValue());

        return specification
            .body("")
            .post(uri)
            .then()
            .extract().response();
    }

    public Response deleteNoParams(String uri) {
        return given(requestSpecification)
            .delete(uri)
            .then()
            .extract().response();
    }

    public Response putWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(requestSpecification);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.queryParam(param.getKey(), param.getValue());

        return specification
            .put(uri)
            .then()
            .extract().response();
    }
}

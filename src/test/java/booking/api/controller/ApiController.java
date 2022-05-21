package booking.api.controller;

import booking.api.client.ApiConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class ApiController {

    protected static final ThreadLocal<Cookie> TOKEN = new ThreadLocal<>();

    protected ResponseSpecBuilder respSpec;
    protected RequestSpecBuilder reqSpec;

    public ApiController(ApiConfig config) {
        this.reqSpec = config.getRequestSpecBuilder();
        this.respSpec = config.getResponseSpecBuilder();
    }

    protected Map<String, String> getAuthHeader() {
        return Collections.singletonMap("authorization", "Token " + getToken());
    }

    protected Cookie getToken() {
        return TOKEN.get();
    }

    protected void setToken(Cookie token) {
        TOKEN.set(token);
    }

    protected Response post(String url) {
        return given()
                .spec(reqSpec.build())
                .post(url)
                .then()
                .spec(respSpec.build())
                .extract()
                .response();
    }

    protected Response get(String url) {
        return given()
                .spec(reqSpec.build())
                .get(url)
                .then()
                .spec(respSpec.build())
                .extract()
                .response();
    }

    protected Response delete(String url) {
        return given()
                .spec(reqSpec.build())
                .delete(url)
                .then()
                .spec(respSpec.build())
                .extract()
                .response();
    }

    protected Response put(String url) {
        return given()
                .spec(reqSpec.build())
                .put(url)
                .then()
                .spec(respSpec.build())
                .extract()
                .response();
    }
}

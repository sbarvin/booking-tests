package booking.api.controller;

import booking.api.client.ApiConfig;
import booking.model.auth.Token;
import booking.model.auth.Auth;
import booking.data.TestData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthController extends ApiController {

    public AuthController(ApiConfig config) {
        super(config);
    }

    @Step("Login with body")
    public Response login(Auth auth) {
        reqSpec.setBody(auth);
        Response response = post("/auth/login");
        setToken(response.getDetailedCookie("token"));

        return response;
    }

    @Step("Login as default user")
    public Response loginAsDefaultUser() {
        return login(TestData.defaultUser());
    }

    @Step("Logout")
    public Response logout() {
        Token token = new Token();
        token
                .setToken(TOKEN.get().getValue());
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(token);
        return post("/auth/logout");
    }

    @Step("Validate token")
    public Response validate() {
        Token token = new Token();
        token
                .setToken(TOKEN.get().getValue());
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(token);
        return post("/auth/validate");
    }
}

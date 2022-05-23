package booking.api.controller;

import booking.api.client.ApiConfig;
import booking.api.model.auth.Token;
import booking.api.model.auth.User;
import booking.data.TestData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthController extends ApiController {

    public AuthController(ApiConfig config) {
        super(config);
    }

    @Step("Login with body")
    public Response login(User user) {
        reqSpec.setBody(user);
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

    @Step("Logout")
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

package booking.api.controller;

import booking.api.client.ApiConfig;
import booking.data.TestData;
import booking.model.booking.Booking;
import booking.model.message.Message;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class MessageController extends ApiController{

    public MessageController(ApiConfig config) {
        super(config);
    }

    @Step("Get all message")
    public Response getAll() {
        reqSpec.addCookie(TOKEN.get());

        return get("/message");
    }

    @Step("Get message by id")
    public Response getById(String id) {
        reqSpec.addCookie(TOKEN.get());

        return get("/message/" + id);
    }

    @Step("Create message")
    public Response create(Message message) {
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(message);

        return post("/message/");
    }

    @Step("Get message unreadble count")
    public Response count() {
        reqSpec.addCookie(TOKEN.get());

        return get("/message/count");
    }

    @Step("Read message")
    public Response read(String id) {
        reqSpec
                .addCookie(TOKEN.get());

        return put("/message/" + id + "/read");
    }

    @Step("Delete message")
    public Response del(String id) {
        reqSpec.addCookie(TOKEN.get());

        return delete("/message/" + id);
    }

}

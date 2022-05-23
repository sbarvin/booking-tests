package booking.api.controller;

import booking.api.client.ApiConfig;
import booking.api.model.room.Room;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class RoomController extends ApiController {

    public RoomController(ApiConfig config) {
        super(config);
    }

    @Step("Get all rooms")
    public Response getAll() {
        reqSpec.addCookie(TOKEN.get());

        return get("/room");
    }

    @Step("Get room by id")
    public Response getById(String id) {
        reqSpec.addCookie(TOKEN.get());

        return get("/room/" + id);
    }

    @Step("Create room")
    public Response create(Room room) {
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(room);

        return post("/room/");
    }

    @Step("Update room")
    public Response update(Room room) {
        reqSpec
                .addCookie(TOKEN.get())
                .setBody(room);

        return put("/room/" + room.getRoomid().toString());
    }

    @Step("Delete room")
    public Response del(String id) {
        reqSpec.addCookie(TOKEN.get());

        return delete("/room/" + id);
    }

}
package booking.api.client;

import booking.api.controller.AuthController;
import booking.api.controller.BookingController;
import booking.api.controller.MessageController;
import booking.api.controller.RoomController;

public class ApiClient {

    private ApiClient() {
    }

    public static ApiClient api() {
        return new ApiClient();
    }

    public AuthController auth() {
        return new AuthController(ApiConfig.config().build());
    }

    public BookingController booking() {
        return new BookingController(ApiConfig.config().build());
    }

    public RoomController room() {
        return new RoomController(ApiConfig.config().build());
    }

    public MessageController message() {
        return new MessageController(ApiConfig.config().build());
    }

}

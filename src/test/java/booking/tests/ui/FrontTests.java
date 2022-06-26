package booking.tests.ui;

import booking.annotations.JiraIssue;
import booking.annotations.Layer;
import booking.data.TestData;
import booking.model.booking.Booking;
import booking.model.message.Message;
import booking.model.room.Room;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;

@Layer("web")
@Owner("barvinsk")
@Feature("Front")
public class FrontTests extends TestBase{

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Booking room")
    void successFillBookingTest() throws Exception {

        apiClient.auth().loginAsDefaultUser();
        Room room = apiClient.room().create(TestData.newRoom())
                .then()
                .extract().as(Room.class);


        Booking booking = TestData.newBooking(room.getRoomid());

        frontPage
                .open()
                .letMeHack()
                .showBooking(room.getDescription())
                .fillBooking(
                        booking.getFirstname(),
                        booking.getLastname(),
                        booking.getEmail(),
                        booking.getPhone(),
                        "01",
                        "02"
                        /*booking.getBookingdates()
                                .getCheckin()
                                .split("-")[booking.getBookingdates()
                                .getCheckin()
                                .split("-").length - 1],
                        booking.getBookingdates()
                                .getCheckout()
                                .split("-")[booking.getBookingdates()
                                .getCheckout().split("-").length - 1]*/)
                .sendBooking()
                .checkBookingResult("Booking Successful!");
        //Selenide.sleep(5000);
        // .sendBooking()\
        // .checkBookingResult("Booking Successful!");

        //apiClient.room().del(room.getRoomid().toString());
        apiClient.auth().logout();
    }

    @Tag("UI")
    @Test
    @JiraIssue("HOMEWORK-401")
    @DisplayName("Send message")
    void successSendMessageTest() {
        Message message = TestData.newMessage();

        frontPage
                .open()
                .fillMessage(
                        message.getName(),
                        message.getEmail(),
                        message.getPhone(),
                        message.getSubject(),
                        message.getDescription()
                )
                .sendMessage()
                .checkMessageResult(message.getSubject())
                .checkMessageResult("Thanks for getting in touch")
                .checkMessageResult(message.getName());
    }

}

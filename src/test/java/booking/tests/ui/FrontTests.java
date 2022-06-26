package booking.tests.ui;

import booking.data.TestData;
import booking.model.booking.Booking;
import booking.model.booking.Bookings;
import booking.model.message.Message;
import booking.model.room.Room;
import booking.model.room.Rooms;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrontTests extends TestBase{

    @Test
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

    @Test
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

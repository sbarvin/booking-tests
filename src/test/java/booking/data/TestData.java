package booking.data;

import booking.model.auth.Auth;
import booking.model.booking.Booking;
import booking.model.booking.BookingDates;
import booking.model.message.Message;
import booking.model.room.Room;
import booking.config.App;
import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestData {

    private final static Faker faker = new Faker();
    private static Random rand = new Random();

    private TestData() {
    }

    public static Auth defaultUser() {
        Auth auth = new Auth();
        auth.setUsername(App.config.defaultLogin());
        auth.setPassword(App.config.defaultPassword());

        return auth;
    }

    public static Room newRoom() {

        Room room = new Room();

        room.setRoomName(faker.funnyName().name());
        room.setRoomPrice(faker.random().nextInt(1, 999));
        room.setAccessible(faker.bool().bool());
        room.setDescription(faker.gameOfThrones().quote());

        String[] typeItems = new String[] {"Single", "Twin", "Double", "Family", "Suite"};
        room.setType(typeItems[rand.nextInt(typeItems.length)]);

        String[] featureItems = new String[] {"WiFi", "TV", "Radio", "Refreshments", "Safe", "Views"};
        Integer cntFeatures = rand.nextInt(featureItems.length - 1) + 1;
        List<String> features = new ArrayList<>();
        for(int i = 0; i < cntFeatures; i++) {
            features.add(featureItems[i]);
        }
        room.setFeatures(features);

        return room;
    }

    public static Booking newBooking(int roomId) {
        Booking booking = new Booking();

        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setDepositpaid(faker.bool().bool());
        booking.setPhone(faker.phoneNumber().subscriberNumber(11));
        booking.setEmail(faker.bothify("????##@gmail.com"));
        booking.setRoomid(roomId);

        BookingDates bookingDates = new BookingDates();

        Date checkIn = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(checkIn);
        c.add(Calendar.DATE, (int)faker.random().nextInt(1, 10));
        Date checkOut = c.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        bookingDates.setCheckin(sdf.format(checkIn));
        bookingDates.setCheckout(sdf.format(checkOut));

        booking.setBookingdates(bookingDates);

        return booking;
    }

    public static Message newMessage() {
        Message message = new Message();

        message.setName(faker.name().firstName());
        message.setPhone(faker.phoneNumber().subscriberNumber(11));
        message.setEmail(faker.bothify("????##@gmail.com"));
        message.setSubject(faker.funnyName().name());
        message.setDescription(faker.gameOfThrones().quote());

        return message;
    }
}

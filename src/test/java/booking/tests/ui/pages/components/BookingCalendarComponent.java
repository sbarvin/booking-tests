package booking.tests.ui.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BookingCalendarComponent
{
    @Step("Go to current month")
    public BookingCalendarComponent currentMonth() {
        $(".room-firstname")
                .parent().parent().parent()
                .$(".rbc-calendar").$(byText("Today")).click();
        return this;
    }

    @Step("Go to next month")
    public BookingCalendarComponent nextMonth() {
        $(".room-firstname")
                .parent().parent().parent()
                .$(".rbc-calendar").$(byText("Next")).click();
        return this;
    }

    @Step("Go to last month")
    public BookingCalendarComponent lastMonth() {
        $(".rbc-calendar").shouldHave(text("Back")).click();
        return this;
    }

    @Step("Set dates")
    public BookingCalendarComponent setDates(String from, String to) {

        /*WebElement source = $(".room-firstname")
                .parent().parent().parent()
                .$(".rbc-calendar").$(byText(from)).parent();

        WebElement target = $(".room-firstname")
                .parent().parent().parent()
                .$(".rbc-calendar").$(byText(to));
*/
        //actions().dragAndDrop(source, target).perform();

        WebElement source = $(".room-firstname")
                .parent().parent().parent()
                .$(".rbc-calendar").$$(".rbc-day-bg").first();

        WebElement target = $(".room-firstname")
                .parent().parent().parent()
                .$(".rbc-calendar").$$(".rbc-day-bg").last();

        actions()
                .clickAndHold(source)
                .release(target)
                .perform();

        return this;
    }
}

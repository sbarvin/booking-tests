package booking.tests.ui.pages.components.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NavBarComponent {

    @Step("Go to rooms")
    public NavBarComponent goToRooms() {
        $$(".nav-item").find(text("Rooms")).click();
        return this;
    }

    @Step("Go to report")
    public NavBarComponent goToReport() {
        $$(".nav-item").find(text("Report")).click();
        return this;
    }

    @Step("Go to branding")
    public NavBarComponent goToBranding() {
        $$(".nav-item").find(text("Branding")).click();
        return this;
    }

    @Step("Go to messages")
    public NavBarComponent goToMessages() {
        $(".fa-inbox").click();
        return this;
    }

    @Step("Go to front page")
    public NavBarComponent goToFront() {
        $$(".nav-item").find(text("Front Page")).click();
        return this;
    }

    @Step("Logout")
    public NavBarComponent logout() {
        $$(".nav-item").find(text("Logout")).click();
        return this;
    }

    @Step("Check navbar brand")
    public NavBarComponent checkBrand() {
        $(".navbar-brand").shouldHave(text("B&B Booking Management"));
        return this;
    }

}

package booking.tests.ui.pages;

import booking.tests.ui.pages.components.LetMeHackComponent;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    protected static LetMeHackComponent letMeHackComponent = new LetMeHackComponent();

    @Step("Go to admin panel login page")
    public LoginPage open() {
        Selenide.open("/#/admin");
        return this;
    }

    @Step("Let me hack!")
    public LoginPage letMeHack() {
        letMeHackComponent.letMeHack();
        return this;
    }

    @Step("Fill username")
    public LoginPage fillName(String val) {
        $("#username").setValue(val);
        return this;
    }

    @Step("Fill password")
    public LoginPage fillPass(String val) {
        $("#password").setValue(val);
        return this;
    }

    @Step("Login")
    public LoginPage login() {
        $("#doLogin").click();
        return this;
    }

    @Step("Check login header")
    public LoginPage checkHeader() {
        $(".container").shouldHave(text("Log into your account"));
        return this;
    }
}

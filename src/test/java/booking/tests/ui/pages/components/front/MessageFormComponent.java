package booking.tests.ui.pages.components.front;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class MessageFormComponent {

    @Step("Fill name")
    public MessageFormComponent fillName(String val) {
        $(".contact").$("#name").setValue(val);
        return this;
    }

    @Step("Fill email")
    public MessageFormComponent fillEmail(String val) {
        $(".contact").$("#email").setValue(val);
        return this;
    }

    @Step("Fill phone")
    public MessageFormComponent fillPhone(String val) {
        $(".contact").$("#phone").setValue(val);
        return this;
    }

    @Step("Fill subject")
    public MessageFormComponent fillSubject(String val) {
        $(".contact").$("#subject").setValue(val);
        return this;
    }

    @Step("Fill description")
    public MessageFormComponent fillDescription(String val) {
        $(".contact").$("#description").setValue(val);
        return this;
    }

    @Step("Send")
    public MessageFormComponent send() {
        $("#submitContact").click();
        return this;
    }

    @Step("Check result")
    public MessageFormComponent checkResult(String val) {
        $(".contact").shouldHave(text(val));
        return this;
    }

}

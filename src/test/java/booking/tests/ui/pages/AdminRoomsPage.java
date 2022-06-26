package booking.tests.ui.pages;

import booking.tests.ui.pages.components.LetMeHackComponent;
import booking.tests.ui.pages.components.admin.NavBarComponent;
import io.qameta.allure.Step;

public class AdminRoomsPage {

    private final NavBarComponent navBarComponent = new NavBarComponent();
    private final LetMeHackComponent letMeHackComponent = new LetMeHackComponent();

    @Step("Let me hack!")
    public AdminRoomsPage letMeHack() {
        letMeHackComponent.letMeHack();
        return this;
    }
    @Step("Create room")
    public AdminRoomsPage createRoom() {
        return this;
    }
}

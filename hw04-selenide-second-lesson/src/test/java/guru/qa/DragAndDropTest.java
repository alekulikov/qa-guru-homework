package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

class DragAndDropTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void dragAndDropWithActionTest() {
        open("/drag_and_drop");
        actions()
                .moveToElement($(byId("column-a")))
                .clickAndHold()
                .moveToElement($(byId("column-b")))
                .release()
                .perform();
        $(byId("column-a")).shouldHave(text("B"));
        $(byId("column-b")).shouldHave(text("A"));
    }

    @Test
    void dragAndDropTest() {
        open("/drag_and_drop");
        $(byId("column-a")).dragAndDrop(to($(byId("column-b"))));
        $(byId("column-a")).shouldHave(text("B"));
        $(byId("column-b")).shouldHave(text("A"));
    }
}
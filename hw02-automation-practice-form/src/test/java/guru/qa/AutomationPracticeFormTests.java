package guru.qa;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AutomationPracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void successTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("FirstName");
        $("#lastName").setValue("LastName");
        $("#userEmail").setValue("test@test.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8005553535");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2003");
        $(".react-datepicker__month").$(byText("17")).click();
        $("#subjectsInput").setValue("arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img.png");
        $("#currentAddress").setValue("Test String");
        $("#state").click();
        $("#state").$(byText("Haryana")).click();
        $("#city").click();
        $("#city").$(byText("Karnal")).click();
        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).sibling(0).shouldHave(text("FirstName LastName"));
        $(".table-responsive").$(byText("Gender")).sibling(0).shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).sibling(0).shouldHave(text("8005553535"));
        $(".table-responsive").$(byText("Date of Birth")).sibling(0).shouldHave(text("17 July,2003"));
        $(".table-responsive").$(byText("Subjects")).sibling(0).shouldHave(text("Arts"));
        $(".table-responsive").$(byText("Subjects")).sibling(0).shouldHave(text("Arts"));
        $(".table-responsive").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture")).sibling(0).shouldHave(text("img.png"));
        $(".table-responsive").$(byText("Address")).sibling(0).shouldHave(text("Test String"));
        $(".table-responsive").$(byText("State and City")).sibling(0).shouldHave(text("Haryana Karnal"));
    }
}
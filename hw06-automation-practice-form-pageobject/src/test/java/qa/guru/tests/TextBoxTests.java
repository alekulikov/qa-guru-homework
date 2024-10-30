package qa.guru.tests;


import org.junit.jupiter.api.Test;
import qa.guru.pages.TextBoxPage;

public class TextBoxTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillFormTest() {
        textBoxPage.openPage()
                .setName("FirstName")
                .setEmail("test@test.com")
                .setCurrentAddress("Some street 1")
                .setPermanentAddress("Another street 1")
                .submit();

        textBoxPage
                .checkResultValue("FirstName")
                .checkResultValue("test@test.com")
                .checkResultValue("Some street 1")
                .checkResultValue("Another street 1");
    }
}
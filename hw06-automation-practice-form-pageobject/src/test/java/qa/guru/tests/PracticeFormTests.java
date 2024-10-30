package qa.guru.tests;

import org.junit.jupiter.api.Test;
import qa.guru.pages.PracticeFormPage;

class PracticeFormTests extends TestBase {

    public PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Test
    void fullFieldsTest() {
        practiceFormPage.openPage()
                .removeBanners()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setEmail("test@test.com")
                .setGender("Male")
                .setUserNumber("8005553535")
                .setDateOfBirth("17", "July", "2003")
                .setSubject("arts")
                .setHobby("Sports")
                .setPicture("img.png")
                .setCurrentAddress("Test String")
                .setState("Haryana")
                .setCity("Karnal")
                .submit();

        practiceFormPage
                .checkResultValue("Student Name", "FirstName LastName")
                .checkResultValue("Student Email", "test@test.com")
                .checkResultValue("Gender", "Male")
                .checkResultValue("Mobile", "8005553535")
                .checkResultValue("Date of Birth", "17 July,2003")
                .checkResultValue("Subjects", "Arts")
                .checkResultValue("Hobbies", "Sports")
                .checkResultValue("Picture", "img.png")
                .checkResultValue("Address", "Test String")
                .checkResultValue("State and City", "Haryana Karnal");
    }

    @Test
    public void onlyRequestedFields() {
        practiceFormPage.openPage()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setGender("Male")
                .setUserNumber("8005553535")
                .setDateOfBirth("17", "July", "2003")
                .submit();

        practiceFormPage
                .checkResultValue("Student Name", "FirstName LastName")
                .checkResultValueIsEmpty("Student Email")
                .checkResultValue("Gender", "Male")
                .checkResultValue("Mobile", "8005553535")
                .checkResultValue("Date of Birth", "17 July,2003")
                .checkResultValueIsEmpty("Subjects")
                .checkResultValueIsEmpty("Hobbies")
                .checkResultValueIsEmpty("Picture")
                .checkResultValueIsEmpty("Address")
                .checkResultValueIsEmpty("State and City");
    }

    @Test
    void incorrectPhoneNumberTest() {
        practiceFormPage.openPage()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setGender("Male")
                .setUserNumber("5553535")
                .setDateOfBirth("17", "July", "2003")
                .submit();

        practiceFormPage.checkResultIsNotVisible();
    }
}
package stepDefinitionsForAPI;


import com.github.javafaker.Faker;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import pojos.GetAuthToken.Get_Auth_Token;
import pojos.GetAuthToken.Token;
import pojos.HerokuApp.Hero_BookingDates_Pojo;
import pojos.HerokuApp.Hero_Booking_Pojo;
import pojos.HerokuApp.Hero_Pojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.ReusableMethods.generateRandomPassword;

public class HerokuappStepDefs {

    protected RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    Faker faker = new Faker();

    String token = "";
    String bookingID = "";


    @Given("Yeni bir kayit olusturulur")
    public void yeni_bir_kayit_olusturulur() {
        spec.pathParam("first", "booking");

        String name = faker.name().firstName();
        String lastname = faker.name().lastName();
        Integer price = Integer.parseInt(generateRandomPassword(3));
        Boolean depositPaid = true;
        String additionalNeeds = "Breakfast with black tea";

        // set the expected data
        Hero_BookingDates_Pojo bookingDates = new Hero_BookingDates_Pojo("2024-09-21", "2024-12-21");
        Hero_Booking_Pojo expectedData = new Hero_Booking_Pojo(name, lastname, price, depositPaid, bookingDates, additionalNeeds);

        System.out.println("expectedData = " + expectedData);
        // send post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON)
                .body(expectedData).when().post("/{first}");

        // response.prettyPrint();
        Hero_Pojo actualData = response.as(Hero_Pojo.class);

        System.out.println("actualData = " + actualData.toString());

        bookingID = response.jsonPath().getString("bookingid");


        // do assertions
        assertEquals(200, response.getStatusCode());

        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        assertEquals
                (expectedData.getBookingdates().getCheckin(), actualData.getBooking().getBookingdates().getCheckin());

        assertEquals
                (expectedData.getBookingdates().getCheckout(), actualData.getBooking().getBookingdates().getCheckout());


        assertEquals(expectedData.toString(), actualData.getBooking().toString());
    }

    @Then("Yeni kaydin varligi kontrol edilir")
    public void yeniKaydinVarligiKontrolEdilir() {
        spec.pathParams("first", "booking", "second", bookingID);
        Response response = given().spec(spec).contentType(ContentType.JSON)
                .when().get("/{first}/{second}");

        int statusCode = response.getStatusCode();
        System.out.println("HTTP Status Code for Get Booking by ID : " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response Body for Get Booking by ID : " + responseBody);
    }

    @Given("Yapilan kaydin silinmesi icin token alinir")
    public void yapilan_kaydin_silinmesi_icin_token_alinir() {
        spec.pathParam("first", "auth");

        String username = "admin";
        String password = "password123";

        Get_Auth_Token expectedData = new Get_Auth_Token(username, password);

        Response response = given().spec(spec).contentType(ContentType.JSON)
                .body(expectedData).when().post("/{first}");

        Token actualData = response.as(Token.class);

        token = response.jsonPath().getString("token");

        System.out.println("token = " + token);
    }

    @Then("Yapilan kayit token kullanilarak silinir")
    public void yapilan_kayit_token_kullanilarak_silinir() {
        spec.pathParams("first", "booking", "second", bookingID);

        Response response = given().spec(spec).contentType(ContentType.JSON).cookie("token", token)
                .when().delete("/{first}/{second}");

        // DELETE isteği sonrası cevabı işleme
        int statusCode = response.getStatusCode();
        System.out.println("HTTP Status Code for Delete Booking: " + statusCode);

        // Yanıt içeriğini alarak işleme
        String responseBody = response.getBody().asString();
        System.out.println("Response Body for Delete Booking : " + responseBody);
    }

    // Aşağıda ki adım opsiyonel olarak kullanılabilir.
    @Then("Silinen kaydin bulunmadigi dogrulanir")
    public void silinen_kaydin_bulunmadigi_dogrulanir() {
        Response response = null;
        try {
            String baseUrl = "https://restful-booker.herokuapp.com";

            String first = "booking";
            String second = bookingID;

            RestAssured.baseURI = baseUrl;

            response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/{first}/{second}", first, second);

            if (response.getStatusCode() == 404) {
                System.out.println("404 Not Found: Istenilen bookingID bulunamadı.");
            }
        } catch (Exception e) {
            assert response != null;
            if (response.getStatusCode() == 404) {
                System.out.println("404 Not Found: Istenilen bookingID bulunamadı.");
            }
            System.out.println("Hata olustu: " + e.getMessage());
        }

    }


}

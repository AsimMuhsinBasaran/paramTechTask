package stepDefinitionsForUI;

import baseURI.HerokuappBaseUrl;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.ParamPage;
import pojos.GetAuthToken.Get_Auth_Token;
import pojos.GetAuthToken.Token;
import pojos.HerokuApp.Hero_BookingDates_Pojo;
import pojos.HerokuApp.Hero_Booking_Pojo;
import pojos.HerokuApp.Hero_Pojo;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.ReusableMethods.*;

public class MyStepdefs extends HerokuappBaseUrl {
    String firstPageWHD = "";
    Actions actions = new Actions(Driver.getDriver());
    Faker faker = new Faker();
    String token = "";
    String bookingID = "";
    ParamPage prm = new ParamPage();



    @Given("Param anasayfaya gidilir")
    public void paramAnasayfayaGidilir() {
        Driver.getDriver().get(ConfigReader.getProperty("paramUrl"));
        firstPageWHD = Driver.getDriver().getWindowHandle();
    }

    @Then("Giris yap butonu tiklanir")
    public void girisYapButonuTiklanir() {
        prm.girisYapButonu.click();
    }

    @And("Kurumsal giris butonuna tiklanir")
    public void kurumsalGirisButonunaTiklanir() {
        prm.kurumsalGirisButonu.click();
    }

    @Then("GSM,TCKN ya da Kart Numarasi ve password alanina rastgele degerler girilir")
    public void gsmTCKNYaDaKartNumarasiVePasswordAlaninaRastgeleDegerlerGirilir() {

        // Yeni açılan login sayfasına geçmek için kullanacağımız method
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(firstPageWHD)) {
                Driver.getDriver().switchTo().window(windowHandle);
            }
        }
        ReusableMethods.waitFor(3);

        String randomNumber = generateRandomPassword(11);

        String randomPassword = generateRandomPassword(6);

        actions.click(prm.usernameBox).sendKeys(randomNumber + Keys.TAB).sendKeys(randomPassword).perform();
    }

    @And("Login butonuna tiklanir")
    public void loginButonunaTiklanir() {
        prm.loginButonu.click();
    }

    @Then("Giris yapilmadigi dogrulanir")
    public void girisYapilmadigiDogrulanir() {
        Assertions.assertTrue(prm.hataliGirisYaptinizYazisi.isDisplayed());
        Assertions.assertTrue(prm.hataliGirisYaptinizYazisi.getText().contains("Hatalı bilgi girişi yaptınız."));
        System.out.println(prm.hataliGirisYaptinizYazisi.getText());
        Driver.closeDriver();
    }

    @Then("Hesap olustur butonuna tiklanir")
    public void hesapOlusturButonunaTiklanir() {
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(firstPageWHD)) {
                Driver.getDriver().switchTo().window(windowHandle);
            }
        }
        prm.hesapOlusturButonu.click();
    }

    @And("Ad,soyad,eposta,GSM doldurulur")
    public void adSoyadEpostaGSMDoldurulur() {

        waitForClickablility(prm.adAlani, Duration.ofDays(10));
        prm.adAlani.sendKeys(faker.name().firstName());

        prm.soyadAlani.click();
        prm.soyadAlani.sendKeys(faker.name().lastName());

        prm.mailAlani.click();
        prm.mailAlani.sendKeys(faker.internet().emailAddress());

        prm.gsmAlani.click();
        prm.gsmAlani.sendKeys("544"+ generateRandomPassword(7));

    }

    @Then("Checkboxlar isaretlenir ve devam butonuna tiklanir")
    public void checkboxlarIsaretlenirVeDevamButonunaTiklanir() {
    prm.aydinlatmaMetniCheckbox.click();
    prm.acilisSozlesmesiCheckbox.click();
    prm.rizaMetniCheckbox.click();
    //waitForClickablility(prm.devamButonu, Duration.ofDays(10));
    prm.devamButonu.click();
    }

    @And("Onay kodu kismina {int} haneli random bir sayi girilir ve onayla butonu tiklanir")
    public void onayKoduKisminaHaneliRandomBirSayiGirilirVeOnaylaButonuTiklanir(int digit) {
        waitForClickablility(prm.OTPsifre, Duration.ofDays(10));
        prm.OTPsifre.click();
        prm.OTPsifre.sendKeys(generateRandomPassword(digit));
        prm.onaylaButonu.click();
    }

    @Then("Kayit olunamadigi dogrulanir")
    public void kayitOlunamadigiDogrulanir() {
        Assertions.assertTrue(prm.onayKoduHataliYazisi.isDisplayed());
        Driver.getDriver().quit();
    }

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
    @Given("Yapilan kaydin silinmesi icin token alinir")
    public void yapilan_kaydin_silinmesi_icin_token_alinir() {
        spec.pathParam("first", "auth");

        String username = "admin";
        String password = "password123";

        Get_Auth_Token expectedData = new Get_Auth_Token(username, password);

        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON)
                .body(expectedData).when().post("/{first}");


        Token actualData = response.as(Token.class);

        System.out.println("actualData = " + actualData.toString());

        token = response.jsonPath().getString("token");
    }
    @Then("Yapilan kayit token kullanilarak silinir")
    public void yapilan_kayit_token_kullanilarak_silinir() {
        spec.pathParams("first", "booking", "second", bookingID);

        Response response = given().spec(spec).contentType(ContentType.JSON).cookie("token", token)
                .when().delete("/{first}/{second}");

        // DELETE isteği sonrası cevabı işleme
        int statusCode = response.getStatusCode();
        System.out.println("HTTP Status Code: " + statusCode);

        // Yanıt içeriğini alarak işleme
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
    }


}

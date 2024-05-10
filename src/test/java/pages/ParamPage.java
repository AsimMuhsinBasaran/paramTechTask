package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class ParamPage {


    public ParamPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath= "(//div[@class='col-auto d-sm-inline-block d-none'])[1]")
    public WebElement girisYapButonu;

    @FindBy(xpath= "(//div[@class='col-auto'])[3]")
    public WebElement kurumsalGirisButonu;

    @FindBy(xpath = "(//div[@class='inputContainter___3G7h1 undefined'])[1]")
    public WebElement usernameBox;

    @FindBy(xpath = "(//div[@class='inputContainter___3G7h1 undefined'])[2]")
    public WebElement passwordBox;

    @FindBy(xpath = "//button[@id='loginbutton']")
    public WebElement loginButonu;

    @FindBy(xpath = "//span[@class=\"signUp___3OOhy\"]")
    public WebElement hesapOlusturButonu;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[2]/div[2]/div/div/input")
    public WebElement adAlani;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[2]/div[3]/div/div/input")
    public WebElement soyadAlani;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[3]/div[2]/div/div/input")
    public WebElement mailAlani;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[4]/div[2]/div/div/div[2]/div/div/input")
    public WebElement gsmAlani;

    @FindBy(xpath = "//*[@class=\"modalTitle___WE5UD\"]")
    public WebElement hataliGirisYaptinizYazisi;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[5]/div[2]/div/div")
    public WebElement aydinlatmaMetniCheckbox;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[6]/div[2]/div/div")
    public WebElement rizaMetniCheckbox;

    @FindBy(xpath = "//*[@id=\"page_signup\"]/div/div[1]/div[7]/div[2]/div/div")
    public WebElement acilisSozlesmesiCheckbox;

    @FindBy(xpath = "//button[@id=\"continue_from_signup\"]")
    public WebElement devamButonu;

    @FindBy(xpath = "//*[@id=\"page_otp\"]/div/div[1]/div[5]/div/div/div[1]/input")
    public WebElement OTPsifre;

    @FindBy(xpath = "//*[@id=\"continue_from_otp\"]")
    public WebElement onaylaButonu;

    @FindBy(xpath = "//*[@id=\"errorVerificationNo\"]")
    public WebElement onayKoduHataliYazisi;

}

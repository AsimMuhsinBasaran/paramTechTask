package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver() {

    }

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            if (ConfigReader.getProperty("browser").equals("safari")) {
                WebDriverManager.safaridriver().setup();
            } else {
                WebDriverManager.chromedriver().setup();
            }
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            long implicitWaitInSeconds = 10;
            long implicitWaitInMilliseconds = implicitWaitInSeconds * 1000;
            driver.manage().timeouts().implicitlyWait(implicitWaitInMilliseconds, TimeUnit.MILLISECONDS);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }


}

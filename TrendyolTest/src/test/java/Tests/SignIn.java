package Tests;

import Helper.*;
import com.beust.jcommander.Parameter;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Yiğithan Kadıoğlu
 */
public class SignIn {
    //region public
    RemoteWebDriver driver;

    DriverClass driverClass = new DriverClass();

    // endregion

    //region @BeforeTest
    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) throws Exception {
        //Check if parameter passed from TestNG is 'firefox'
        if (browser.equalsIgnoreCase("firefox")) {
            //create firefox instance
            driver = driverClass.setRemoteWebDriver("SignIn", "1.0", "Firefox", "61x64", "Windows 10", "1920x1080", "true", "false");
        }
        //Check if parameter passed as 'chrome'
        else if (browser.equalsIgnoreCase("chrome")) {
            //set path to chromedriver.exe
            driver = driverClass.setRemoteWebDriver("SignIn", "1.0", "Chrome", "65x64", "Windows 10", "1920x1080", "true", "false");
        }
        //Check if parameter passed as 'Edge'
        else if (browser.equalsIgnoreCase("Edge")) {
            //set path to Edge.exe
            driver = driverClass.setRemoteWebDriver("SignIn", "1.0", "Edge", "17", "Windows 10", "1920x1080", "true", "false");
        } else {
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    //endregion

    //region @Test
    @Test
    @Parameter(names = {"email", "password"})
    public void signIn(String email, String password) throws Exception {

        driver.get("https://www.trendyol.com");
        System.out.println("Loading Url");

        driver.manage().window().maximize();
        System.out.println("Maximizing window");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        System.out.println("Waiting..");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"icon icon-trendyol\"]")));

        try {
            driver.findElementByCssSelector("[class=\"fancybox-item fancybox-close\"]").click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElementByCssSelector("[class=\"icon icon-user\"]").click();
        System.out.println("SignIn Button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"authentication-input email\"]")));

        driver.findElementByCssSelector("[class=\"authentication-input email\"]").sendKeys(email);
        System.out.println("Entering username");

        driver.findElementByCssSelector("[class=\"authentication-input password\"]").sendKeys(password);
        System.out.println("Entering password");
        driver.findElementByCssSelector("[class=\"login-submit send-button\"]").submit();
        System.out.println("Submiting Form");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"icon icon-trendyol\"]")));
    }
    //endregion

    @AfterTest
    public void endSession() throws UnirestException {
        driver.quit();
    }

}
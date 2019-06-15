import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        //LOCAL APPIUM SETTINGS
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Honor10");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app",
                "/Users/nglebanov/IdeaProjects/AppiumStudyProject/apks/org.wikipedia_10280_apps.evozi.com.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test() throws InterruptedException {

        WebElement skip = driver.
                findElement(By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"));
        skip.click();
        WebElement searchTap = driver.findElement(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']"));
        searchTap.click();
        WebElement searchField = waitForElement(By.id("org.wikipedia:id/search_src_text"));
        searchField.click();
        driver.getKeyboard().sendKeys("Java");
        waitForElement(By.xpath("//*[@text='Язык программирования']"));

    }

    private WebElement waitForElement(By by, String errorMessage, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.withMessage(errorMessage);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElement(By by, String errorMessage) {
        return waitForElement(by, errorMessage, 5);
    }

    private WebElement waitForElement(By by) {
        return waitForElement(by, "EXCEPTION", 5);
    }


}

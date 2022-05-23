package org.task;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task.commons.Constants;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public AndroidDriver driver;

    public void waitForElement(AndroidDriver driver, By byLocator, int timeLimitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator));
    }

    public void waitForElement(AndroidDriver driver, AndroidElement elem, int timeLimitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }

    public boolean isElementNotPresent(By by) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(by);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
        finally {
            driver.manage().timeouts().implicitlyWait(Constants.WAIT_FOR_ELEMENTS_IN_SEC, TimeUnit.SECONDS);
        }
    }

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = getCapabilities();
        URL url = new URL(Constants.URL_TO_APPIUM);
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(Constants.WAIT_FOR_ELEMENTS_IN_SEC, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.closeApp();
        driver.removeApp(Constants.APP_PACKAGE);
        driver.quit();
    }

    protected DesiredCapabilities getCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, Constants.AUTOMATION_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, Constants.PLATFORM_NAME);
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, Constants.PLATFORM_VERSION);
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, Constants.DEVICE_NAME);
        caps.setCapability(MobileCapabilityType.APP, Constants.PATH_TO_APP.getPath().substring(1));
        return caps;
    }
}

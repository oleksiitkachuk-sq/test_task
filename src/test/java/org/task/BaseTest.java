package org.task;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.task.commons.Constants;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public AndroidDriver driver;

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

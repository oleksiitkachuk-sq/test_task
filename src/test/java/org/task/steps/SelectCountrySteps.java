package org.task.steps;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.task.BaseTest;
import org.task.commons.Constants;
import org.task.helpers.TextHelper;
import org.task.pages.CountryConfirmActivityPage;
import org.testng.Assert;

import java.io.IOException;

import static org.task.helpers.TextHelper.getTextFromJson;

public class SelectCountrySteps extends BaseTest {
    AndroidDriver driver;
    public CountryConfirmActivityPage countryConfirmPage;

    public SelectCountrySteps(AndroidDriver driver) {
        this.driver = driver;
    }

    private static final Logger logger = LoggerFactory.getLogger(SelectCountrySteps.class);

    @Step("Verify welcome text.")
    public void verifyWelcomeText() {
        countryConfirmPage = new CountryConfirmActivityPage(driver);
        // wait for CountryConfirmActivity
        waitForElement(driver, By.id(String.format("%s:id/tvChooseCountryText", Constants.APP_PACKAGE)),
                Constants.WAIT_FOR_ELEMENTS);
        Assert.assertEquals(Constants.APP_PACKAGE, driver.getCurrentPackage());
        Assert.assertTrue(countryConfirmPage.isHeaderExist(), "CountryConfirmActivity is not displayed!");
        try {
            String expectedHeaderText = getTextFromJson(Constants.PATH_TO_ACTIVITIES_TEXT,
                    "countryConfirmActivity",
                    "welcomeText");
            TextHelper.verifyText(countryConfirmPage.getHeaderText(), expectedHeaderText);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
    @Step("Select {0} country")
    public void selectCountry(String country){
        countryConfirmPage.selectCountry(country);
    }

}

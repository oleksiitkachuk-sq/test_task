package org.task.steps;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.fwork.objects.Car;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.task.BaseTest;
import org.task.commons.Constants;
import org.task.commons.JsonFields;
import org.task.commons.Views;
import org.task.helpers.TextHelper;
import org.task.pages.ModifyCarPage;
import org.testng.Assert;

import java.io.IOException;

import static org.task.helpers.TextHelper.getTextFromJson;

public class ModifyCarSteps extends BaseTest {
    public ModifyCarPage carPage;

    public ModifyCarSteps(AndroidDriver driver) {
        this.driver = driver;
    }

    protected static final Logger logger = LoggerFactory.getLogger(ModifyCarSteps.class);

    @Step("{2}")
    public void modifyCarStep(Car car, Views view, String msg) {
        carPage = new ModifyCarPage(driver);
        verifyHeaderText(view);
        fillAddCarFields(car);
        if (view == Views.EDIT_CAR_VIEW)
            carPage.tapSaveCarButton();
    }

    @Step("Delete car in 'modify car' view")
    public void deleteCarStep(){
        if (carPage == null)
            carPage = new ModifyCarPage(driver);
        carPage.tapDeleteCarButton();
        boolean isContentPanelNotPresent =
                isElementNotPresent(By.id("de.autodoc.gmbh:id/swipeContent"));
        Assert.assertTrue(isContentPanelNotPresent, "ContentPanel is not present on view");
    }

    @Step("Fill car dialog fields.")
    public void fillAddCarFields(Car car) {
        carPage.tapCarMakerField();
        tapItemInSearchResult(car.getMaker());
        tapItemInSearchResult(car.getModel());
        tapItemInSearchResult(car.getModification());
    }

    private void tapItemInSearchResult(String text) {
        carPage.fillSearchField(text);
        carPage.tapItemInSearchResult(text);
    }

    private void verifyHeaderText(Views view) {
        String headerText = null;
        if (view == Views.ADD_CAR_VIEW) {
            headerText = JsonFields.ADD_CAR_HEADER_TEXT;
        } else if (view == Views.EDIT_CAR_VIEW) {
            headerText = JsonFields.EDIT_CAR_HEADER_TEXT;
        }
        try {
            String expectedHeaderText = getTextFromJson(Constants.PATH_TO_ACTIVITIES_TEXT,
                    JsonFields.CAR_VIEW, headerText);
            if (view == Views.ADD_CAR_VIEW)
                TextHelper.verifyText(carPage.getHeaderText(), expectedHeaderText);
            else if (view == Views.EDIT_CAR_VIEW)
                System.out.println();
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
}

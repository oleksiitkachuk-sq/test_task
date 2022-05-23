package org.task.car_tests;

import io.qameta.allure.*;
import org.fwork.objects.Car;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.task.BaseTest;
import org.task.commons.CountryNames;
import org.task.commons.Views;
import org.task.helpers.MobileHelper;
import org.task.steps.MainActivitySteps;
import org.task.steps.ModifyCarSteps;
import org.task.steps.SelectCountrySteps;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

import static org.task.helpers.TextHelper.getAllCars;

@Severity(SeverityLevel.NORMAL)
public class DeleteCarTests extends BaseTest {

    @Test
    @Description("This is a positive test that checks for delete a car.")
    @Feature("Delete a car via swipe panel")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteCarInSwipeToolbarTest() {
        SelectCountrySteps selectCountrySteps = new SelectCountrySteps(driver);
        selectCountrySteps.verifyWelcomeText();
        selectCountrySteps.selectCountry(CountryNames.AUSTRIA);

        MainActivitySteps mainActivitySteps = new MainActivitySteps(driver);
        mainActivitySteps.openCarDialog(true, false);

        ModifyCarSteps modifyCarSteps = new ModifyCarSteps(driver);
        Car[] cars = getAllCars();
        Car firstCar = cars[0];
        modifyCarSteps.modifyCarStep(firstCar, Views.ADD_CAR_VIEW, "Add car step");
        mainActivitySteps.verifyCarInSwipePanel(firstCar);
        mainActivitySteps.deleteCarInSwipePanel();
        Allure.addAttachment("View after delete vehicle", new ByteArrayInputStream(((TakesScreenshot) driver).
                getScreenshotAs(OutputType.BYTES)));

    }

    @Test
    @Ignore
    public void deleteCarOnModifyCarViewTest() {

    }

}

package org.task.car_tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.fwork.objects.Car;
import org.task.BaseTest;
import org.task.commons.CountryNames;
import org.task.commons.Views;
import org.task.steps.MainActivitySteps;
import org.task.steps.ModifyCarSteps;
import org.task.steps.SelectCountrySteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.task.helpers.TextHelper.getAllCars;

@Severity(SeverityLevel.NORMAL)
public class ModifyCarTests extends BaseTest {

    @Test
    @Description("This is a positive test that checks for modify a car.")
    @Feature("Modify a car")
    @Severity(SeverityLevel.BLOCKER)
    public void modifyCarTest() {
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

        mainActivitySteps.openCarDialog(false, true);
        Car secondCar = cars[1];
        modifyCarSteps.modifyCarStep(secondCar, Views.EDIT_CAR_VIEW, "Edit car step");
        mainActivitySteps.verifyCarInSwipePanel(secondCar);
    }
}

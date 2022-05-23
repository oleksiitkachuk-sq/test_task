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
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static org.task.helpers.TextHelper.getCars;

@Severity(SeverityLevel.NORMAL)
public class DeleteCarTests extends BaseTest {

    @Test
    @Description("This is a positive test that checks for delete a car.")
    @Feature("Delete a car")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteCarInSwipeToolbarTest() {
        SelectCountrySteps selectCountrySteps = new SelectCountrySteps(driver);
        selectCountrySteps.verifyWelcomeText();
        selectCountrySteps.selectCountry(CountryNames.AUSTRIA);

        MainActivitySteps mainActivitySteps = new MainActivitySteps(driver);
        mainActivitySteps.openCarDialog(true, false);

        ModifyCarSteps modifyCarSteps = new ModifyCarSteps(driver);
        Car[] cars = getCars();
        Assert.assertNotNull(cars, "Assert for Car[] validation is failed. Car[] is null!");
        Car firstCar = cars[0];
        modifyCarSteps.modifyCarSteps(firstCar, Views.ADD_CAR_VIEW, "Add car step");
        mainActivitySteps.verifyCarInSwipePanel(firstCar);

        mainActivitySteps.deleteCar();
        // TODO check that no car in swipe panel
    }

    @Ignore
    @Test
    public void deleteCarOnModifyCarViewTest() {
    }
}

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

import static org.task.helpers.TextHelper.getCarByIndex;

@Severity(SeverityLevel.NORMAL)
public class AddCarTests extends BaseTest {

    @Test
    @Description("This is a positive test that checks for the addition of a car.")
    @Feature("Adding a car")
    @Severity(SeverityLevel.BLOCKER)
    public void addCarTest() {
        SelectCountrySteps selectCountrySteps = new SelectCountrySteps(driver);
        selectCountrySteps.verifyWelcomeText();
        selectCountrySteps.selectCountry(CountryNames.AUSTRIA);

        MainActivitySteps mainActivitySteps = new MainActivitySteps(driver);
        mainActivitySteps.openCarDialog(true, false);

        ModifyCarSteps addCarSteps = new ModifyCarSteps(driver);
        Car car = getCarByIndex(0);
        addCarSteps.modifyCarStep(car, Views.ADD_CAR_VIEW, "Add car step");
        mainActivitySteps.verifyCarInSwipePanel(car);
    }
}

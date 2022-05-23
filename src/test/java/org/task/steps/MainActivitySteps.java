package org.task.steps;


import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.fwork.objects.Car;
import org.openqa.selenium.By;
import org.task.BaseTest;
import org.task.commons.Constants;
import org.task.helpers.MobileHelper;
import org.task.pages.MainActivityPage;
import org.testng.Assert;

import java.util.List;

public class MainActivitySteps extends BaseTest {

    public MainActivityPage mainActivityPage;

    public MainActivitySteps(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Open car dialog.")
    public void openCarDialog(boolean isAppOpenFirstTime, boolean isCarHasBeenAdded) {
        mainActivityPage = new MainActivityPage(driver);
        if (isAppOpenFirstTime) {
            waitForElement(driver, By.id(String.format("%s:id/btnCheckIn", Constants.APP_PACKAGE)),
                    Constants.WAIT_FOR_ELEMENTS);
            mainActivityPage.closeCheckInPopup();
        }
        if (isCarHasBeenAdded) {
            swipeSwipePanelToLeft();
            mainActivityPage.tapToEditCarButton();
        } else {
            Assert.assertTrue(mainActivityPage.isAddCarButtonExist(), "AddCarButton exist.");
            mainActivityPage.tapToAddCarButton();
        }
    }

    @SneakyThrows
    @Step("Delete car in swipe panel.")
    public void deleteCar() {
        swipeSwipePanelToLeft();
        mainActivityPage.tapToDeleteCarButton();
    }

    @Step("Verify added car in swipe panel")
    public void verifyCarInSwipePanel(Car car) {
        if (mainActivityPage == null)
            mainActivityPage = new MainActivityPage(driver);
        List<String> carDetailStrings = mainActivityPage.getCarDetails();
        Assert.assertEquals(carDetailStrings.get(0), String.format("%s %s", car.getMaker(), car.getModel()));
        Assert.assertEquals(carDetailStrings.get(1), car.getModification());
    }


    @SneakyThrows
    @Step()
    private void swipeSwipePanelToLeft(){
        mainActivityPage.tapToSwipeContentPanel();
        Thread.sleep(500);
        // waiting animation. TODO wait for 'add new car' panel
        MobileHelper.swipeMobileElement(driver, mainActivityPage.swipeElement(), Direction.LEFT, "swipePanel");
        waitForElement(driver, mainActivityPage.editCarButton(), Constants.WAIT_FOR_ELEMENTS);
    }
}

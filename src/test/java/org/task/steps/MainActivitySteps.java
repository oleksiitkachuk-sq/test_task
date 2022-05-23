package org.task.steps;


import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.android.AndroidDriver;
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

import static org.task.helpers.MobileHelper.isElementNotPresent;

public class MainActivitySteps extends BaseTest {

    public MainActivityPage mainActivityPage;

    public MainActivitySteps(AndroidDriver driver) {
        this.driver = driver;
    }

    @Step("Open car dialog.")
    public void openCarDialog(boolean isAppOpenFirstTime, boolean isCarHasBeenAdded) {
        mainActivityPage = new MainActivityPage(driver);
        if (isAppOpenFirstTime) {
            MobileHelper.waitForElement(driver, By.id(String.format("%s:id/btnCheckIn", Constants.APP_PACKAGE)),
                    Constants.WAIT_FOR_ELEMENTS);
            mainActivityPage.closeCheckInPopup();
        }
        if (isCarHasBeenAdded) {
            swipeSwipePanelToLeft();
            mainActivityPage.tapEditCarButton();
        } else {
            Assert.assertTrue(mainActivityPage.isAddCarButtonExist(), "AddCarButton exist.");
            mainActivityPage.tapAddCarButton();
        }
    }

    @Step("Delete car in swipe panel.")
    public void deleteCarInSwipePanel() {
        swipeSwipePanelToLeft();
        mainActivityPage.tapDeleteCarButton();
        boolean isContentPanelNotPresent =
                isElementNotPresent(driver, By.id("de.autodoc.gmbh:id/swipeContent"));
        Assert.assertTrue(isContentPanelNotPresent, "ContentPanel is not present on view");
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
    private void swipeSwipePanelToLeft() {
        mainActivityPage.tapSwipeContentPanel();
        mainActivityPage.isAddNewVehicleExist("Add a new vehicle");
        Thread.sleep(500);
        // waiting animation. TODO wait for 'add new car' panel
        MobileHelper.swipeMobileElement(driver, mainActivityPage.swipeElement(), Direction.LEFT, "swipePanel");
        MobileHelper.waitForElement(driver, mainActivityPage.editCarButton(), Constants.WAIT_FOR_ELEMENTS);
    }
}

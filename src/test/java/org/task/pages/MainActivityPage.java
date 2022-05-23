package org.task.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.task.commons.Constants;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPage {

    private AndroidDriver driver;

    public MainActivityPage() {
    }

    public MainActivityPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "de.autodoc.gmbh:id/btnCheckIn")
    private AndroidElement checkIn;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/ibClose")
    private AndroidElement closeButton;

    @AndroidFindBy(xpath = "//*[@resource-id='de.autodoc.gmbh:id/lvParentModal']/android.widget.ImageButton")
    private AndroidElement addCarButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/btnTry")
    private AndroidElement tryButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/swipeContent")
    private AndroidElement swipeContentPanel;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/btnEdit")
    private AndroidElement editCarButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/btnDelete")
    private AndroidElement deleteCarButton;

    // TODO needs id for this field
    public boolean isAddNewVehicleExist(String text) {
        List<AndroidElement> elements = driver.findElements(By.id(String.format("%s:id/tvTitle", Constants.APP_PACKAGE)));
        for (AndroidElement elem : elements) {
            if (elem.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheckInExist() {
        return checkIn.isDisplayed();
    }

    public boolean isTryButtonExist() {
        return tryButton.isDisplayed();
    }

    public AndroidElement editCarButton() {
        return editCarButton;
    }

    public AndroidElement swipeContentPanel() {
        return swipeContentPanel;
    }

    public boolean isAddCarButtonExist() {
        return addCarButton.isDisplayed();
    }

    public List<String> getCarDetails() {
        List<MobileElement> elems = swipeContentPanel.findElements(By.xpath("//android.widget.TextView"));
        List<String> carDetailStrings = new ArrayList<>();
        elems.forEach(elem -> carDetailStrings.add(elem.getText()));
        return carDetailStrings;
    }

    public MobileElement swipeElement() {
        return swipeContentPanel.findElement(By.xpath("//android.widget.ImageView"));
    }

    @Step("Tap 'add car' button.")
    public void tapAddCarButton() {
        addCarButton.click();
    }

    @Step("Tap 'delete car button'")
    public void tapDeleteCarButton() {
        deleteCarButton.click();
    }

    @Step("Close 'CheckIn' popup.")
    public void closeCheckInPopup() {
        if (isCheckInExist()) closeButton.click();
        else Assert.fail("CheckIn button is not displayed!");
    }

    @Step("Close 'try now' popup.")
    public void tapTryButtonExist() {
        tryButton.click();
    }

    @Step("Tap 'swipe content panel'")
    public void tapSwipeContentPanel() {
        swipeContentPanel.click();
    }

    @Step("Tap 'edit car button'")
    public void tapEditCarButton() {
        editCarButton.click();
    }
}

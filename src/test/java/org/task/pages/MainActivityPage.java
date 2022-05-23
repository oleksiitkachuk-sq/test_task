package org.task.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
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

    public boolean isCheckInExist() {
        return checkIn.isDisplayed();
    }

    public boolean isTryButtonExist() {
        return tryButton.isDisplayed();
    }

    @Step("Tap to 'swipe content panel'")
    public void tapToSwipeContentPanel(){
        swipeContentPanel.click();
    }

    @Step("Tap to 'edit car button'")
    public void tapToEditCarButton(){
        editCarButton.click();
    }

    public AndroidElement editCarButton(){
        return editCarButton;
    }

    @Step("Tap to 'delete car button'")
    public void tapToDeleteCarButton(){
        deleteCarButton.click();
    }

    public AndroidElement swipeContentPanel(){
        return swipeContentPanel;
    }

    @Step("Close 'try now' popup.")
    public void tapToTryButtonExist() {
        tryButton.click();
    }

    @Step("Close 'CheckIn' popup.")
    public void closeCheckInPopup() {
        if (isCheckInExist()) closeButton.click();
        else Assert.fail("CheckIn button is not displayed!");
    }

    public boolean isAddCarButtonExist() {
        return addCarButton.isDisplayed();
    }

    @Step("Tap 'add car' button.")
    public void tapToAddCarButton() {
        addCarButton.click();
    }

    public List<String> getCarDetails() {
        List<MobileElement> elems = swipeContentPanel.findElements(By.xpath("//android.widget.TextView"));
        List<String> carDetailStrings = new ArrayList<>();
        elems.forEach(elem -> carDetailStrings.add(elem.getText()));
        return carDetailStrings;
    }

    public MobileElement swipeElement(){
        return swipeContentPanel.findElement(By.xpath("//android.widget.ImageView"));
    }
}

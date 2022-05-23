package org.task.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.task.commons.Constants;
import org.testng.Assert;

import java.util.List;

public class ModifyCarPage {
    private AndroidDriver driver;

    public ModifyCarPage() {
    }

    public ModifyCarPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "de.autodoc.gmbh:id/tvAddCarModel")
    private AndroidElement modifyCarDialogHeader;

    @AndroidFindBy(xpath = "//*[@resource-id='de.autodoc.gmbh:id/lvCarMenu']/android.view.ViewGroup[1]")
    private AndroidElement carMakerField;

    @AndroidFindBy(xpath = "//*[@resource-id='de.autodoc.gmbh:id/lvCarMenu']/android.view.ViewGroup[2]")
    private AndroidElement modelField;

    @AndroidFindBy(xpath = "//*[@resource-id='de.autodoc.gmbh:id/lvCarMenu']/android.view.ViewGroup[3]")
    private AndroidElement modificationField;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/etSearchField")
    private AndroidElement searchField;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/tsbSaveCar")
    private AndroidElement saveButton;

    @AndroidFindBy(id = "de.autodoc.gmbh:id/deleteCar")
    private AndroidElement deleteButton;

    public String getHeaderText() {
        return modifyCarDialogHeader.getText();
    }

    public boolean isHeaderExist() {
        return modifyCarDialogHeader.isDisplayed();
    }

    @Step("Tap to save button")
    public void tapToSaveCarButton() {
        saveButton.click();
    }

    @Step("Tap to delete car button")
    public void tapToDeleteCarButton() {
        deleteButton.click();
    }

    @Step("Tap to car maker field")
    public void tapToCarMakerField() {
        carMakerField.click();
    }

    @Step("Tap to model field")
    public void tapToModelField() {
        modelField.click();
    }

    @Step("Tap to modification field")
    public void tapToModificationField() {
        modificationField.click();
    }

    public boolean isSearchFieldExist() {
        return searchField.isDisplayed();
    }

    @Step("Type [{0}] to 'search field'")
    public void fillSearchField(String text) {
        if (isSearchFieldExist())
            searchField.sendKeys(text);
        else Assert.fail("Search field is not displayed!");
    }

    @Step("Tap item in search result: [{0}]")
    public void tapToItemInSearchResult(String text) {
        List<AndroidElement> elements = driver.findElements(By.id(String.format("%s:id/title", Constants.APP_PACKAGE)));
        for (AndroidElement elem : elements) {
            if (elem.getText().equals(text)) {
                elem.click();
            }
        }
    }
}

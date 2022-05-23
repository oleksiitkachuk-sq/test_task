package org.task.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;


public class CountryConfirmActivityPage {
    private AndroidDriver driver;

    public CountryConfirmActivityPage() {
    }

    public CountryConfirmActivityPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "de.autodoc.gmbh:id/tvChooseCountryText")
    private AndroidElement header;

    public String getHeaderText() {
        return header.getText();
    }

    public boolean isHeaderExist() {
        return header.isDisplayed();
    }

    @Step("Select {0} country")
    public void selectCountry(String countryName) {
        driver.findElement(By.xpath(String.format("//*[@text='%s']", countryName))).click();
    }
}

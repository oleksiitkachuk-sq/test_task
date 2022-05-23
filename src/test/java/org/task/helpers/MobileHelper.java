package org.task.helpers;

import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.time.Duration;

public class MobileHelper {
    /**
     * Performs swipe inside an element
     *
     * @param driver AndroidDriver
     * @param el     the element to swipe
     * @param dir    the direction of swipe
     **/
    @Step("Swipe [{3}] to [{2}]")
    public static void swipeMobileElement(AndroidDriver driver, MobileElement el, Direction dir, String name) {
        final int ANIMATION_TIME = 300;
        final int PRESS_TIME = 200;
        int edgeBorder;
        PointOption pointOptionStart, pointOptionEnd;

        Rectangle rect = el.getRect();
        edgeBorder = 0;

        switch (dir) {
            case DOWN:
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                break;
            case UP:
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                break;
            case LEFT:
                pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                break;
            case RIGHT:
                pointOptionStart = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
        }

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static File takeScreenshot(AndroidDriver driver){
        return  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    }
}

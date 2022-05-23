package org.task.commons;

import java.net.URL;

public class Constants {
    final public static String AUTOMATION_NAME = "UiAutomator2";
    final public static String PLATFORM_NAME = "ANDROID";
    final public static String PLATFORM_VERSION = "11";
    final public static String DEVICE_NAME = "Pixel";
    public static String APP_PACKAGE = "de.autodoc.gmbh";
    static String appFileName = "AUTODOC_1.9.9.apk";

    final public static String PATH_TO_ACTIVITIES_TEXT = Constants.class.getClassLoader().
            getResource("activityText.json").getPath().substring(1);
    final public static String PATH_TO_CAR_TEXT = Constants.class.getClassLoader().
            getResource("carText.json").getPath().substring(1);
    public static URL PATH_TO_APP = Constants.class.getClassLoader().getResource(appFileName);
    // TODO download apk from Jenkins or diff place
    private static int port = 4723;
    final public static String URL_TO_APPIUM = String.format("http://127.0.0.1:%d/wd/hub", port);
    final public static int WAIT_FOR_ELEMENTS = 500;
}

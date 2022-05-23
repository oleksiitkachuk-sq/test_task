package org.task.helpers;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.fwork.objects.Car;
import org.task.commons.Constants;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextHelper {
    public static void verifyText(String actualText, String expectedText) {
        Assert.assertEquals(actualText, expectedText);
    }

    public static String getOneLineStringFromJsonFile(String pathToFile) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(pathToFile))) {
            return String.join("", lines.collect(Collectors.toList()));
        }
    }

    public static String getTextFromJson(String pathToFile, String parentKey, String childKey) throws IOException {
        JsonObject jsonObject = JsonParser.parseString(getOneLineStringFromJsonFile(pathToFile)).getAsJsonObject();
        return jsonObject.getAsJsonObject(parentKey).get(childKey).getAsString();
    }

    public static Car[] getAllCars() {
        Gson gson = new Gson();
        try {
            Car[] cars = gson.fromJson(new FileReader(Constants.PATH_TO_CAR_TEXT), Car[].class);
            Assert.assertNotNull(cars, "Assert for Car[] validation is failed. Car[] is null!");
            return cars;
        } catch (FileNotFoundException e) {
            Assert.fail(String.format("File from path [%s] not found! %s",
                    Constants.PATH_TO_CAR_TEXT, e));
        }
        return null;
    }

    public static Car getCarByIndex(int index) {
        try {
            Car[] cars = getAllCars();
            return cars[index];
        }catch (ArrayIndexOutOfBoundsException exception){
            Assert.fail(String.format("Index [%d] negative, greater than, or equal to the length of the array. [%s]",
                    index, exception));
        }
        return null;
    }
}

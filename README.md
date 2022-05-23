# test_task
#### Maven, TestNG, Appium, Allure.
### Running tests
```
mvn clean test
```
### Create allure report
```
mvn io.qameta.allure:allure-maven:serve
```

# Directories

## CAR_TESTS
This package contains tests (add, modify and delete a car tests).


## STEPS
This package contains steps of tests. 
#### SelectCountrySteps
This class contains the steps of the 'Select your country' view.
#### MainActivitySteps
This class contains the steps of the main view.
#### ModifyCarSteps
This class contains the steps of the add\modify a car view.

## PAGES
This package contains pages of views. 
#### CountryConfirmActivityPage
This class contains the page of the 'Select your country' view.
#### MainActivityPage
This class contains the page of the main view.
#### ModifyCarPage
This class contains the page of the add\modify a car view.

## COMMONS
This package contains common classes (constants and enum).


### HELPERS
This package contains helpers: MobileHelper (swipe and takeScreenshots methods).


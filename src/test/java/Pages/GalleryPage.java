package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * That is Gallery Page of the site, where it can be check signals based on which city is chosen.
 * Different filters are presented as a region, status and type.
 */
public class GalleryPage extends BasePage {
    private final static By VALIDATE_CITY_MESSAGE_ELEMENT = By.cssSelector(".pac-target-input");
    private final static By SUBMIT_CITY_MESSAGE_ELEMENT = By.cssSelector("button.btn.btn-primary.submit-signal.modal-button");
    private final static By SIGNAL_DESCRIPTION_ELEMENT = By.cssSelector("a div.description");
    private final static By STATUS_IN_SIGNAL_CHANGES_ELEMENT = By.id("status-changes");
    private final static By STATUS_FILTER_ELEMENT = By.xpath("//div[contains(text(),'статус')]/following-sibling::div");
    private final static By STATUS_FILTER_ALL_OPTIONS_ELEMENTS = By.xpath("//div[contains(text(),'статус')]/following-sibling::div/ul/li");
    private final static By APOLOGIZED_MESSAGES_ELEMENTS = By.cssSelector("span.apology-text");
    private final static By RESOLVED_SIGNALS_ELEMENTS = By.cssSelector("div.statuschange-wrap.resolved");
    private final static By BEING_PROCESSED_SIGNALS_ELEMENTS = By.cssSelector("div.statuschange-wrap.in-progress");
    private final static By UPLOADED_SIGNALS_ELEMENTS = By.cssSelector("div.statuschange-wrap.submitted");
    private final static By AREA_FILTER_ELEMENT = By.xpath("//div[contains(text(),'район')]/following-sibling::div");
    private final static By AREA_FILTER_ALL_OPTIONS_ELEMENTS = By.xpath("//div[contains(text(),'район')]/following-sibling::div/ul/li");
    private final static By TYPE_FILTER_ELEMENT = By.xpath("//div[contains(text(),'вид')]/following-sibling::div");
    private final static By TYPE_FILTER_ALL_OPTIONS_ELEMENTS = By.xpath("//div[contains(text(),'вид')]/following-sibling::div/ul/li");


    /**
     * It will enter the given City and Country names in message appeared when Gallery page is loaded
     *
     * @param cityName    Accepting String with the chosen city name
     * @param countryName Accepting String with the chosen country name
     */
    public void enterCityAndCountryName(String cityName, String countryName) {
        WebElement element = driver.findElement(VALIDATE_CITY_MESSAGE_ELEMENT);
        element.click();
        element.clear();
        String place = cityName + ", " + countryName;
        typeText(element, place);
        waitSeconds(1);
        clickOnButton(element);
        element.sendKeys(Keys.DOWN);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * It will click on the submit button in the message which appears when Gallery page is loaded
     */
    public void clickOnSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(SUBMIT_CITY_MESSAGE_ELEMENT)));
        Actions action = new Actions(driver);
        action.click(driver.findElement(SUBMIT_CITY_MESSAGE_ELEMENT)).perform();
    }

    /**
     * It will click randomly on a signal presented on the page (usually the first one from left and top side of the page,
     * because of Selenium)
     */
    public void clickOnRandomSignal() {
        System.out.println("Opening signal...");
        clickOnButton(driver.findElement(SIGNAL_DESCRIPTION_ELEMENT));
    }

    /**
     * it will check if the loaded signal is loaded successfully
     */
    public void isSignalLoaded() {
        Assert.assertTrue(driver.findElement(STATUS_IN_SIGNAL_CHANGES_ELEMENT).isDisplayed(),
                "Something went wrong! Signal has not been loaded successfully!");
        System.out.println("Signal has been loaded successfully!");
    }

    /**
     * It will go to Status Filter and will click on one of the presented options from the dropdown menu
     *
     * @param status Accepting String with status option and one of the presented:
     *               Всички(default); Извинил се; Разрешен; Обработва се; Качен
     */
    public void chooseStatusOption(String status) {
        waitSeconds(1);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(STATUS_FILTER_ELEMENT)).perform();
        int count = 0;
        List<WebElement> allElements = driver.findElements(STATUS_FILTER_ALL_OPTIONS_ELEMENTS);
        for (WebElement element : allElements) {
            System.out.println("Searching for option from status options: \"" + status + "\"...");
            if (element.getText().trim().equalsIgnoreCase(status)) {
                clickOnButton(element);
                count += 1;
                break;
            }
        }
        if (count == 0) {
            System.out.println("Option status with this name isn't exist!");
        }
    }

    /**
     * It will check if the reported signals which has been located in "Apologized" are loaded successfully
     */
    public void isApologizedSignalsLoaded() {
        Assert.assertTrue(driver.findElement(APOLOGIZED_MESSAGES_ELEMENTS).isDisplayed(),
                "Something went wrong! Apologized signals are not loaded successfully!");
        System.out.println("Apologized signals are loaded successfully!");
    }

    /**
     * it will check if the reported signals which has been located in "Resolved" section are loaded successfully
     */
    public void isResolvedSignalsLoaded() {
        Assert.assertEquals(driver.findElement(RESOLVED_SIGNALS_ELEMENTS).getText().trim(),
                "РАЗРЕШЕН",
                "Something went wrong! Resolved signals are not loaded successfully!");
        System.out.println("Resolved signals are loaded successfully!");
    }

    /**
     * it will check if the reported signals which has been located in "Being Processed" section are loaded successfully
     */
    public void isBeingProcessedSignalsLoaded() {
        Assert.assertTrue(driver.findElement(BEING_PROCESSED_SIGNALS_ELEMENTS).isDisplayed(),
                "Something went wrong! Being Processed signals are not loaded successfully!");
        System.out.println("Being Processed signals are loaded successfully!");
    }

    /**
     * it will check if the reported signals which has been located in "Uploaded" section are loaded successfully
     */
    public void isUploadedSignalsLoaded() {
        Assert.assertTrue(driver.findElement(UPLOADED_SIGNALS_ELEMENTS).isDisplayed(),
                "Something went wrong! Uploaded signals are not loaded successfully!");
        System.out.println("Uploaded signals are loaded successfully!");
    }

    /**
     * It will check if the signals are presented on the page, based on given signal option
     *
     * @param statusName Accepting string with the name of signal option.
     *                   The available options are: Качен; Извинил се; Обработва се; Разрешен
     */
    public void isStatusSignalsLoaded(String statusName) {
        if (statusName.equalsIgnoreCase("Качен"))
            isUploadedSignalsLoaded();
        if (statusName.equalsIgnoreCase("Извинил се"))
            isApologizedSignalsLoaded();
        if (statusName.equalsIgnoreCase("Обработва се"))
            isBeingProcessedSignalsLoaded();
        if (statusName.equalsIgnoreCase("Разрешен"))
            isResolvedSignalsLoaded();
    }

    /**
     * It will choose option from area dropdown menu based on given option name
     *
     * @param areaName Accepting string with option name.
     *                 The available options are: Всички; Друг; Previous entered City name (if there is any)
     */
    public void chooseAreaOption(String areaName) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(AREA_FILTER_ELEMENT)).perform();
        int count = 0;
        List<WebElement> allElements = driver.findElements(AREA_FILTER_ALL_OPTIONS_ELEMENTS);
        for (WebElement element : allElements) {
            System.out.println("Searching for option from area dropdown: \"" + areaName + "\"...");
            if (element.getText().trim().equalsIgnoreCase(areaName)) {
                clickOnButton(element);
                count += 1;
                break;
            }
        }
        if (count == 0) {
            System.out.println("Option with this name isn't exist!");
        }
    }

    /**
     * It will check if the given City name (area) is successfully loaded
     *
     * @param cityName Accepting string with the name of the City (area)
     */
    public void isAreaLoaded(String cityName) {
        int count = 0;
        List<WebElement> allSignalsDescription = driver.findElements(SIGNAL_DESCRIPTION_ELEMENT);
        if (allSignalsDescription.isEmpty()) {
            Assert.fail("There is no signals loaded in this Area!");
        }
        for (WebElement signalDescription : allSignalsDescription) {
            System.out.println("Searching the area: \"" + cityName + "\" in signals description...");
            if (signalDescription.getText().trim().contains(cityName)) {
                Assert.assertTrue(true);
                System.out.println("Area is loaded successfully!");
                count += 1;
                break;
            }
        }
        if (count == 0)
            Assert.fail("Area has been not found!");
    }

    /**
     * It will choose option from types dropdown menu based on given option name
     *
     * @param optionName Accepting string with option name.
     */
    public void chooseTypeOption(String optionName) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(TYPE_FILTER_ELEMENT)).perform();
        int count = 0;
        List<WebElement> allElements = driver.findElements(TYPE_FILTER_ALL_OPTIONS_ELEMENTS);
        for (WebElement element : allElements) {
            System.out.println("Searching for option from types dropdown: \"" + optionName + "\"...");
            if (element.getText().trim().equalsIgnoreCase(optionName)) {
                clickOnButton(element);
                count += 1;
                break;
            }
        }
        if (count == 0) {
            System.out.println("Option with this name ISN'T exist!");
        }
    }

    /**
     * It will check if the given Type name (in options from dropdown menu) is successfully loaded
     *
     * @param typeName Accepting string with the name of type option
     */
    public void isTypeLoaded(String typeName) {
        int count = 0;
        List<WebElement> allSignalsDescription = driver.findElements(SIGNAL_DESCRIPTION_ELEMENT);
        if (allSignalsDescription.isEmpty()) {
            Assert.fail("There is no signals loaded from this type!");
        }
        for (WebElement signalDescription : allSignalsDescription) {
            System.out.println("Searching type: \"" + typeName + "\" in signals description...");
            if (signalDescription.getText().trim().contains(typeName)) {
                Assert.assertTrue(true);
                System.out.println("Type option has been loaded successfully!");
                count += 1;
                break;
            }
        }
        if (count == 0)
            Assert.fail("Type option has been NOT loaded successfully!");
    }


}

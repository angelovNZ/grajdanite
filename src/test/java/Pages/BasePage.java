package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * That is the BasePage Page of 'Grajdanite.bg' website where all base methods are defined
 * As a Starting and Configure the browser, Open 'Grajdanite.bg' page,
 * Typing and Get text from elements and so on
 */
public class BasePage {
    private final static String BROWSER = "chrome";
    private final static String PAGE_URL = "https://grajdanite.bg/home";
    protected static WebDriver driver;


    /**
     * It will open web page (in this case: "Grajdanite.bg")
     */
    public void openPage() {
        startBrowser(BROWSER);
        System.out.println("PAGE is loading...");
        driver.navigate().to(PAGE_URL);
    }

    /**
     * It will start the specified browser, which can be changed in constant field named 'BROWSER' only
     * Also is configure that browser
     *
     * @param browserName represents the name of the desired browser: accept Chrome and Firefox (for now)
     */
    private static void startBrowser(String browserName) {
        System.out.println("BROWSER is loading...");
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            configureBrowser();
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            configureBrowser();
        }
    }

    /**
     * It will configure the browser: deleting cookies, maximize the page and set a implicit wait (10 seconds)
     */
    private static void configureBrowser() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    /**
     * It will type a text to a given element
     *
     * @param element represent the web element where the text will be entered
     * @param text    represent the text which will be entered
     */
    public static void typeText(WebElement element, String text) {
        Objects.requireNonNull(element, "Element should not be null!");
        System.out.println("Typing text: \"" + text + "\"");
        element.sendKeys(text);
    }

    /**
     * It will get the text from a given element
     *
     * @param element represent the web element from which the text will be gather
     * @return String with the taken text
     */
    public static String getText(WebElement element) {
        Objects.requireNonNull(element, "Element should not be null!");
        System.out.println("Getting text...");
        return element.getText().trim();
    }

    /**
     * It will click on the given element
     *
     * @param element represent the web element which will be clicked on
     */
    public static void clickOnButton(WebElement element) {
        Objects.requireNonNull(element, "Element should not be null!");
        System.out.println("Clicking on the element... ");
        element.click();
    }

    /**
     * It will refresh the page
     */
    public void refreshThePage() {
        driver.navigate().refresh();
    }

    /**
     * it will check if an specific error message has been presented on the page
     *
     * @param element accepts WebElement of the error message
     * @return true or false
     */
    public boolean isErrorMessagePresented(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * It will quit the Browser directly
     */
    public void quitBrowser() {
        System.out.println("Quiting the browser...");
        driver.quit();
    }

    /**
     * It will put the work to sleep for that many seconds which is given in the param.
     *
     * @param seconds Accepting integer with seconds
     */
    public void waitSeconds(int seconds) {
        if (seconds <= 0) {
            Assert.fail("Negative numbers are not allowed!");
        } else {
            //Dummy wait here for places where Implicit or Explicit are not working well
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException ignored) {

            }
        }
    }

    /**
     * It will go to the previous page
     */
    public void goToPreviousPage() {
        System.out.println("Navigating back to previous page...");
        driver.navigate().back();
    }
}

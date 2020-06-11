package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * That is the Login Page of "Grajdanite.bg" website where all methods
 * related to login to the account will be defined
 * Extends Base Page
 */
public class LoginPage extends BasePage {
    private final static By LOGIN_PAGE_BUTTON_ELEMENT = By.linkText("Вход");
    private final static By EMAIL_FIELD_ELEMENT = By.xpath("//input[@type='email']");
    private final static By PASSWORD_FIELD_ELEMENT = By.xpath("//input[@type='password']");
    private final static By LOGIN_BUTTON_ELEMENT = By.xpath("//form[@class='ng-untouched ng-dirty ng-valid']//button");
    private final static By SEARCH_BAR_ELEMENT = By.cssSelector(".search-bar");
    private final static By ERROR_MESSAGE_ELEMENT = By.cssSelector(".text-danger.ng-star-inserted");
    private final static By LOGOUT_BUTTON_ELEMENT = By.cssSelector("li.link.border-top a");
    private final static By PROFILE_DROPDOWN_ELEMENT = By.cssSelector("a.dropdown-toggle");

    /**
     * It will open "Grajdanite.bg" website and will navigate to Login Page
     */
    public void goToLoginPage() {
        openPage();
        System.out.println("Loading Login page...");
        clickOnButton(driver.findElement(LOGIN_PAGE_BUTTON_ELEMENT));
    }

    /**
     * It will enter the needed credentials for login in to the account
     *
     * @param email    accepts String with email
     * @param password accepts String with password
     */
    public void enterCredentials(String email, String password) {
        WebElement emailField = driver.findElement(EMAIL_FIELD_ELEMENT);
        WebElement passwordField = driver.findElement(PASSWORD_FIELD_ELEMENT);
        emailField.clear();
        passwordField.clear();
        typeText(emailField, email);
        typeText(passwordField, password);
    }

    /**
     * It will click on the login button which is presented on the login page
     */
    public void clickOnLoginButton() {
        System.out.println("Logging in... ");
        clickOnButton(driver.findElement(LOGIN_BUTTON_ELEMENT));
    }

    /**
     * It will check if the login was successful or not
     */
    public void isLoginSuccessful() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("Гражданите"));
        Assert.assertTrue(driver.findElement(SEARCH_BAR_ELEMENT).isDisplayed(),
                "Login was not successful!");
        System.out.println("Login was successful!");
    }

    /**
     * It will check that if the presented error message on the page is as expected
     *
     * @param errorMessage accepts String with expected error message
     */
    public void isErrorMessageCorrect(String errorMessage) {
        Assert.assertEquals(getText(driver.findElement(ERROR_MESSAGE_ELEMENT)),
                errorMessage,
                "Error message is not correct!");
        System.out.println("Error message is correct!");
    }

    /**
     * It will logout from account
     */
    public void clickOnLogoutButton() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(PROFILE_DROPDOWN_ELEMENT)).perform();
        System.out.println("Logging out...");
        clickOnButton(driver.findElement(LOGOUT_BUTTON_ELEMENT));
    }

    /**
     * It will check if the process of logging out is successful
     */
    public void isLoggingOutSuccessful() {
        Assert.assertTrue(driver.findElement(EMAIL_FIELD_ELEMENT).isDisplayed(),
                "Logging out was not successful!");
        System.out.println("Logging out was successful!");
    }
}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * That is the Header of the page where is presented pages like Login, For us, Gallery and Products.
 * Methods related to them are defined here.
 */
public class HeaderPage extends BasePage {
    private final static By FOR_US_PAGE_ELEMENT = By.xpath("//header//nav//a[@routerlink='/about-us']");
    private final static By FOR_US_PAGE_HEADER_ELEMENT = By.cssSelector(".header-left");
    private final static By USER_HOMEPAGE_ELEMENT = By.cssSelector(".logo");
    private final static By USER_HOMEPAGE_HEADER_ELEMENT = By.xpath("//*[@id='header relative grid-container']");
    private final static By GALLERY_PAGE_ELEMENT = By.xpath("//header//nav//a[@routerlink='/signals/gallery']");
    private final static By GALLERY_SEARCH_BAR_ELEMENT = By.id("search-bar");
    private final static By PRODUCTS_DROPDOWN_ELEMENT = By.cssSelector(".dropdown-toggle");
    private final static By PRODUCTS_DROPDOWN_MENU_ELEMENTS = By.cssSelector(".dropdown-menu li");
    private final static By DROPDOWN_PRODUCT_PAGE_ELEMENT = By.cssSelector(".lead-content-wrap.relative h1");

    /**
     * It will navigate to "For us" page from header menu
     */
    public void goToForUsPage() {
        clickOnButton(driver.findElement(FOR_US_PAGE_ELEMENT));
        System.out.println("Loading page \"For Us\"... ");

    }

    /**
     * It will check if the page "For us" is loaded successfully
     */
    public void isForUsPageLoaded() {
        Assert.assertTrue(driver.findElement(FOR_US_PAGE_HEADER_ELEMENT).isDisplayed(),
                "The Page \"For Us\" isn't loaded successfully!");
        System.out.println("The Page \"For Us\" is loaded successfully!");
    }

    /**
     * It will navigate to User Homepage
     */
    public void goToUserHomepage() {
        clickOnButton(driver.findElement(USER_HOMEPAGE_ELEMENT));
        System.out.println("Loading User Homepage...");
    }

    /**
     * It will check if the User Homepage is loaded successfully
     */
    public void isUserHomepageLoaded() {
        Assert.assertTrue(driver.findElement(USER_HOMEPAGE_HEADER_ELEMENT).isDisplayed(),
                "The User Homepage isn't loaded successfully!");
        System.out.println("The User Homepage is loaded successfully!");
    }

    /**
     * It will navigate to Gallery Page
     */
    public void goToGalleryPage() {
        clickOnButton(driver.findElement(GALLERY_PAGE_ELEMENT));
        System.out.println("Loading Gallery Page...");
    }

    /**
     * It will check if the Gallery page is loaded successfully
     */
    public void isGalleryPageLoaded() {
        Assert.assertTrue(driver.findElement(GALLERY_SEARCH_BAR_ELEMENT).isDisplayed(),
                "The Gallery Page isn't loaded successfully!");
        System.out.println("The Gallery Page is loaded successfully!");
    }

    /**
     * It will navigate to Products from header menu and click on it to show the options from the presented dropdown
     */
    public void goToProductsDropdown() {
        clickOnButton(driver.findElement(PRODUCTS_DROPDOWN_ELEMENT));
        System.out.println("Loading Products Dropdown menu...");
    }

    /**
     * It will click on the option (if exist) from the provided Products dropdown menu.
     * @param option Accepts String with correct name of the option presented in Products dropdown menu.
     *               Options can be: За Институции; За Бизнеса; За Адвокати и НПО-та; За Медии
     */
    public void chooseProductOption(String option) {
        int count = 0;
        List<WebElement> elements = driver.findElements(PRODUCTS_DROPDOWN_MENU_ELEMENTS);

        for (WebElement element : elements) {
            System.out.println("Searching for option " + option + "...");
            if (getText(element).equalsIgnoreCase(option)) {
                System.out.println("Option " + option + " has been found!");
                clickOnButton(element);
                System.out.println("Option " + option + " from dropdown menu is now loading...");
                count += 1;
                break;
            }
        }
        if (count == 0) {
            System.out.println("Option what was entered isn't exist...");
        }
    }

    /**
     * It will check if the page "For Institutions" is loaded successfully
     */
    public void isForInstitutionsPageLoaded() {
        Assert.assertEquals(driver.findElement(DROPDOWN_PRODUCT_PAGE_ELEMENT).getText().trim(), "За Институции",
                "The page \"For Institutions\" hasn't been loaded successfully!");
        System.out.println("The page \"For Institutions\" has been loaded successfully!");
    }

    /**
     * It will check if the page "For Business" is loaded successfully
     */
    public void isForBusinessPageLoaded() {
        Assert.assertEquals(driver.findElement(DROPDOWN_PRODUCT_PAGE_ELEMENT).getText().trim(), "За Бизнеса",
                "The page \"For Business\" hasn't been loaded successfully!");
        System.out.println("The page \"For Business\" has been loaded successfully!");
    }

    /**
     * It will check if the page "For Lawyers" is loaded Successfully
     */
    public void isForLawyersPageLoaded() {
        Assert.assertEquals(driver.findElement(DROPDOWN_PRODUCT_PAGE_ELEMENT).getText().trim(), "За Aдвокати и НПО-та",
                "The page \"For Lawyers\" hasn't been loaded successfully!");
        System.out.println("The page \"For Lawyers\" has been loaded successfully!");
    }

    /**
     * It will check if the page "For Media" is loaded successfully
     */
    public void isForMediaPageLoaded() {
        Assert.assertEquals(driver.findElement(DROPDOWN_PRODUCT_PAGE_ELEMENT).getText().trim(), "За Медии",
                "The page \"For Media\" hasn't been loaded successfully!");
        System.out.println("The page \"For Media\" has been loaded successfully!");
    }

}

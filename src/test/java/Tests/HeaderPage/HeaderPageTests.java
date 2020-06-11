package Tests.HeaderPage;

import Pages.LoginPage;
import Pages.HeaderPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HeaderPageTests {
    LoginPage loginPage = new LoginPage();
    HeaderPage headerPage = new HeaderPage();

    @BeforeClass
    public void setUp() {
        loginPage.openPage();
    }

    @Test
    public void canNavigateToForUsPage() {
        headerPage.goToForUsPage();
        headerPage.isForUsPageLoaded();
    }

    @Test
    public void canNavigateToGalleryPage() {
        headerPage.goToGalleryPage();
        headerPage.isGalleryPageLoaded();
    }

    @Test
    public void canNavigateToForInstitutionsPage() {
        headerPage.goToProductsDropdown();
        headerPage.chooseProductOption("За Институции");
        headerPage.isForInstitutionsPageLoaded();
    }

    @Test
    public void canNavigateToForLawyersPage() {
        headerPage.goToProductsDropdown();
        headerPage.chooseProductOption("За Адвокати и НПО-та");
        headerPage.isForLawyersPageLoaded();
    }

    @Test
    public void canNavigateToForBusinessPage() {
        headerPage.goToProductsDropdown();
        headerPage.chooseProductOption("За Бизнеса");
        headerPage.isForBusinessPageLoaded();
    }

    @Test
    public void canNavigateToForMediaPage() {
        headerPage.goToProductsDropdown();
        headerPage.chooseProductOption("За Медии");
        headerPage.isForMediaPageLoaded();
    }


    @AfterClass
    public void tearDown() {
        loginPage.quitBrowser();
    }
}

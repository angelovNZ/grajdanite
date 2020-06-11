package Tests.GalleryPage;

import Pages.GalleryPage;
import Pages.HeaderPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AreaFilterTests {
    GalleryPage galleryPage = new GalleryPage();
    HeaderPage headerPage = new HeaderPage();

    @DataProvider
    public Object[][] testData() {
        return new String[][]{
                new String[]{"Varna", "Bulgaria"},
                new String[]{"Burgas", "Bulgaria"},
                new String[]{"Plovdiv", "Bulgaria"},
                new String[]{"Stara Zagora", "Bulgaria"},
                new String[]{"Veliko Tarnovo", "Bulgaria"},
                new String[]{"Stuttgart", "Germany"}, //should fail the test(no signals on the page)
        };
    }

    @BeforeClass
    public void setUp() {
        headerPage.openPage();
        headerPage.goToGalleryPage();
        galleryPage.waitSeconds(1);
        galleryPage.enterCityAndCountryName("Sofia", "Bulgaria");
        galleryPage.clickOnSubmitButton();
    }

    @Test(dataProvider = "testData")
    public void canChangeCity(String cityName, String countryName) {
        galleryPage.chooseAreaOption("Друг");
        galleryPage.waitSeconds(1);
        galleryPage.enterCityAndCountryName(cityName, countryName);
        galleryPage.clickOnSubmitButton();
        galleryPage.isAreaLoaded(cityName);
    }

    @AfterClass
    public void tearDown() {
        headerPage.quitBrowser();
    }
}

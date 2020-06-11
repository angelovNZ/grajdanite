package Tests.GalleryPage;

import Pages.GalleryPage;
import Pages.HeaderPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusFilterTests {
    HeaderPage headerPage = new HeaderPage();
    GalleryPage galleryPage = new GalleryPage();

    @DataProvider
    public Object[][] testData() {
        return new String[][]{
                new String[]{"Извинил се"},
                new String[]{"Обработва се"},
                new String[]{"Качен"},
                new String[]{"Разрешен"},
        };
    }

    @BeforeClass
    public void setUp() {
        headerPage.openPage();
        headerPage.goToGalleryPage();
        galleryPage.waitSeconds(1);
        galleryPage.enterCityAndCountryName("Sofia", "Bulgaria");
    }

    @Test(dataProvider = "testData")
    public void e_canLoadUploadedSignals(String status) {
        galleryPage.clickOnSubmitButton();
        galleryPage.chooseStatusOption(status);
        galleryPage.isStatusSignalsLoaded(status);
        galleryPage.clickOnRandomSignal();
        galleryPage.isSignalLoaded();
        galleryPage.goToPreviousPage();
    }

    @AfterClass
    public void tearDown() {
        headerPage.quitBrowser();
    }

}

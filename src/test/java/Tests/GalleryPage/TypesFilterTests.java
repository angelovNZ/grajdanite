package Tests.GalleryPage;

import Pages.GalleryPage;
import Pages.HeaderPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TypesFilterTests {
    HeaderPage headerPage = new HeaderPage();
    GalleryPage galleryPage = new GalleryPage();

    @DataProvider
    public Object[][] testData() {
        return new String[][]{
                new String[]{"Агресивен шофьор"},
                new String[]{"Блокиран гараж/изход"},
                new String[]{"Боклуци на детска площадка"},
                new String[]{"Влошено качество на водата"},
                new String[]{"Вредители (гризачи, насекоми)"},
                new String[]{"Занемарена фасада"},
                new String[]{"Липсващ капак на шахта"},
                new String[]{"Замърсен въздух"},
                new String[]{"Неправилно паркиране"},
                new String[]{"Насилие над животни"},
                new String[]{"Незаконнии афиши"},
                new String[]{"Обществен транспорт"},
                //There is a lot more...
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
    public void test(String optionName) {
        galleryPage.chooseTypeOption(optionName);
        galleryPage.isTypeLoaded(optionName);
    }

    @AfterClass
    public void tearDown() {
        headerPage.quitBrowser();
    }
}

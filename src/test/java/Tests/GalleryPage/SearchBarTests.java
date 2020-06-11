//package Tests.GalleryPage;
//
//import Pages.GalleryPage;
//import Pages.HeaderPage;
//
//public class SearchBarTests {
//    HeaderPage headerPage = new HeaderPage();
//    GalleryPage galleryPage = new GalleryPage();
//
//    @DataProvider
//    public Object[][] data() {
//        return new String[][]{
//                new String[]{"шахта"},
//                new String[]{"шофьор"},
//                new String[]{"автомобил"},
//                new String[]{"дърво"},
//                new String[]{"мпс"},
//        };
//    }
//
//    @BeforeClass
//    public void setUp() {
//        headerPage.openPage();
//        headerPage.goToGalleryPage();
//        galleryPage.waitSeconds(1);
//        galleryPage.enterCityAndCountryName("Sofia", "Bulgaria");
//    }
//
//
//    @AfterClass
//    public void tearDown() {
//        galleryPage.quitBrowser();
//    }
//}

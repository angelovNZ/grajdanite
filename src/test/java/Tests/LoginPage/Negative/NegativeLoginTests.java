package Tests.LoginPage.Negative;

import Pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLoginTests {
    LoginPage loginPage = new LoginPage();

    @BeforeClass
    public void setUp() {
        loginPage.goToLoginPage();
    }

    @DataProvider
    public Object[][] invalidCredentials() {
        return new String[][]{
                new String[]{"thisemailisntexist@abv.bg", "123456789a", "Invalid username/password."},
                new String[]{"kaboooooom", "123456789a", "Invalid username/password."},
                new String[]{"%$bl]£[*@abv.bg", "123456789a", "Invalid username/password."},
                new String[]{"kaboomqa@abv.bg", "thatpasswordisnvalid", "Invalid username/password."},
                new String[]{"kaboomqa@abv.bg", "b", "Invalid username/password."},
                new String[]{"kaboomqa@abv.bg", "iamnotsurehowlongpasswordicanenterhereandwhatwillhappenwiththisone?", "Invalid username/password."},
                new String[]{"invalidemailheretocheckit@abv.bg", "invalidpasswordaswell", "Invalid username/password."},
                new String[]{"invalidemaillikeatallhere", "somethinginvalidforpassword", "Invalid username/password."},
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void testWithInvalidCredentials(String email, String password, String errorMessage) {
        loginPage.enterCredentials(email, password);
        loginPage.clickOnLoginButton();
        loginPage.isErrorMessageCorrect(errorMessage);
        loginPage.refreshThePage();
    }

    @AfterClass
    public void tearDown() {
        loginPage.quitBrowser();
    }
}

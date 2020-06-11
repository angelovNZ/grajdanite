package Tests.LoginPage.Positive;

import Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PositiveLoginTest {
    LoginPage loginPage = new LoginPage();

    @BeforeMethod
    public void setUp(){
        loginPage.goToLoginPage();
    }

    @Test
    public void login(){
        loginPage.enterCredentials("kaboomqa@abv.bg","123456789a");
        loginPage.clickOnLoginButton();
        loginPage.isLoginSuccessful();
        loginPage.clickOnLogoutButton();
        loginPage.isLoggingOutSuccessful();
    }

    @AfterMethod
    public void tearDown(){
        loginPage.quitBrowser();
    }
}

package praktikum;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.PasswordRecoveryPage;
import praktikum.page.RegistrationPage;
import praktikum.util.WebDriverFactory;

@RunWith(Parameterized.class)
public class LoginTest {
  private WebDriver webDriver;
  private final String browser;
  private final String email;
  private final String password;
  private final String loginMethod;

  public LoginTest(String browser, String email, String password, String loginMethod) {
    this.browser = browser;
    this.email = email;
    this.password = password;
    this.loginMethod = loginMethod;
  }

  @Parameterized.Parameters
  public static Object[][] getData() {
    return new Object[][] {
      {"chrome", RandomStringUtils.randomAlphabetic(6) + "@test.com",
        RandomStringUtils.randomAlphabetic(6), "main"},
      {"chrome", RandomStringUtils.randomAlphabetic(6) + "@test.com",
        RandomStringUtils.randomAlphabetic(6),"personalAccount"},
      {"chrome", RandomStringUtils.randomAlphabetic(6) + "@test.com",
        RandomStringUtils.randomAlphabetic(6), "registration"},
      {"chrome", RandomStringUtils.randomAlphabetic(6) + "@test.com",
        RandomStringUtils.randomAlphabetic(6), "passwordRecovery"}
    };
  }

  @Before
  public void setUp() {
    webDriver = WebDriverFactory.getWebDriver(browser);
    webDriver.get("https://stellarburgers.nomoreparties.site/");
  }

  @Test
  @DisplayName("Вход в систему различными способами")
  public void loginTest() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);

    switch (loginMethod) {
      case "main":
        mainPage.clickEnterButton();
        break;
      case "personalAccount":
        mainPage.clickPersonalAccountButton();
        break;
      case "registration":
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegistrationLink();
        break;
      case "passwordRecovery":
        mainPage.clickPersonalAccountButton();
        loginPage.clickPasswordRecoveryLink();
        new PasswordRecoveryPage(webDriver).clickEnterButton();
        break;
    }

    loginPage.userLogin(email, password);
    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
  }

  @After
  public void tearDown() {
    webDriver.quit();
  }
}
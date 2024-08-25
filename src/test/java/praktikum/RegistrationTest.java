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
import praktikum.page.RegistrationPage;
import praktikum.util.WebDriverFactory;

@RunWith(Parameterized.class)
public class RegistrationTest {
  private WebDriver webDriver;
  private final String browser;
  private final String name;
  private final String email;
  private final String password;
  private final boolean isValidPassword;

  public RegistrationTest(String browser, String name, String email, String password, boolean isValidPassword) {
    this.browser = browser;
    this.name = name;
    this.email = email;
    this.password = password;
    this.isValidPassword = isValidPassword;
  }

  @Parameterized.Parameters
  public static Object[][] getData() {
    return new Object[][] {
      {"chrome", RandomStringUtils.randomAlphabetic(6),
        RandomStringUtils.randomAlphabetic(6) + "@test.com", RandomStringUtils.randomAlphabetic(6), true},
      {"chrome", RandomStringUtils.randomAlphabetic(6),
        RandomStringUtils.randomAlphabetic(6) + "@test.com", RandomStringUtils.randomAlphabetic(5), false}
    };
  }

  @Before
  public void setUp() {
    webDriver = WebDriverFactory.getWebDriver(browser);
    webDriver.get("https://stellarburgers.nomoreparties.site/");
  }

  @Test
  @DisplayName("Регистрация нового пользователя")
  public void userRegistration() {
    MainPage mainPage = new MainPage(webDriver);
    mainPage.clickEnterButton();

    LoginPage loginPage = new LoginPage(webDriver);
    loginPage.clickRegistrationLink();

    RegistrationPage registrationPage = new RegistrationPage(webDriver);
    registrationPage.userRegistration(name, email, password);

    if (isValidPassword) {
      assertTrue("Пользователь не смог зарегистрироваться", mainPage.isUserEntered());
    } else {
      assertTrue("Ошибка о некорректном пароле не отображается", registrationPage.isPasswordErrorDisplayed());
    }
  }

    @After
    public void tearDown() {
      webDriver.quit();
    }
}

package praktikum;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.LoginPage;
import praktikum.page.MainPage;
import praktikum.page.PersonalAccountPage;
import praktikum.util.WebDriverFactory;

public class PersonalAccountTest {
  private WebDriver webDriver;
  private final String email = "test@example.com";
  private final String password = "password123";

  @Before
  public void setUp() {
    webDriver = WebDriverFactory.getWebDriver("chrome");
    webDriver.get("https://stellarburgers.nomoreparties.site/");
  }

  @Test
  @DisplayName("Переход в личный кабинет")
  public void transitionToPersonalAccount() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.userLogin(email, password);
    mainPage.clickPersonalAccountButton();

    assertTrue("Страница личного кабинета не открылась", personalAccountPage.isPersonalAccountPageOpened());
  }

  @Test
  @DisplayName("Переход в раздел \"Конструктор\" по ссылке")
  public void transitionToConstructorByLink() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.userLogin(email, password);
    mainPage.clickPersonalAccountButton();
    personalAccountPage.clickConstructorButton();

    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
  }
  @Test
  @DisplayName("Переход в раздел \"Конструктор\" по логотипу")
  public void transitionToConstructorByLogo() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.userLogin(email, password);
    mainPage.clickPersonalAccountButton();
    personalAccountPage.clicklogo();

    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
  }

  @Test
  @DisplayName("Выход из аккаунта")
  public void logoutFromAccount() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    PersonalAccountPage personalAccountPage = new PersonalAccountPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.userLogin(email, password);
    mainPage.clickPersonalAccountButton();
    personalAccountPage.clickExitButton();

    assertTrue("Выход из аккаунта не выполнен", mainPage.isUserEntered());
  }

  @After
  public void tearDown() {
    webDriver.quit();
  }
}

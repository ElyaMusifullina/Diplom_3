package praktikum;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
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
  private String email;
  private String password;
  private String name;
  private String accessToken;

  @Before
  public void setUp() {
    webDriver = WebDriverFactory.getWebDriver();
    webDriver.get("https://stellarburgers.nomoreparties.site/");
    email = RandomStringUtils.randomAlphabetic(6) + "@test.com";
    password = RandomStringUtils.randomAlphabetic(6);
    name = RandomStringUtils.randomAlphabetic(6);

    // Регистрация пользователя через API
    Response response = RestAssured.given()
      .contentType("application/json")
      .body(String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}", email, password, name))
      .when()
      .post("https://stellarburgers.nomoreparties.site/api/auth/register");

    accessToken = response.then().extract().path("accessToken");
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

    assertTrue("Выход из аккаунта не выполнен", loginPage.isUserExited());
  }

  @After
  public void tearDown() {
    // Удаление пользователя через API
    if (accessToken != null) {
      RestAssured.given()
        .header("Authorization", accessToken)
        .when()
        .delete("https://stellarburgers.nomoreparties.site/api/auth/user")
        .then()
        .statusCode(202);
    }

    webDriver.quit();
  }
}
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
import praktikum.page.PasswordRecoveryPage;
import praktikum.util.WebDriverFactory;

public class LoginTest {
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
  @DisplayName("Вход в систему через главную страницу")
  public void loginTestMainPage() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.userLogin(email, password);

    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
  }

  @Test
  @DisplayName("Вход в систему через личный кабинет")
  public void loginTestPersonalAccount() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);

    mainPage.clickPersonalAccountButton();
    loginPage.userLogin(email, password);

    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
  }

  @Test
  @DisplayName("Вход в систему через форму регистрации")
  public void loginTestRegistration() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);

    mainPage.clickPersonalAccountButton();
    loginPage.userLogin(email, password);

    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
  }

  @Test
  @DisplayName("Вход в систему через форму восстановления пароля")
  public void loginTestPasswordRecovery() {
    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(webDriver);

    mainPage.clickPersonalAccountButton();
    loginPage.clickPasswordRecoveryLink();
    passwordRecoveryPage.clickEnterButton();
    loginPage.userLogin(email, password);

    assertTrue("Вход в систему не выполнен", mainPage.isUserEntered());
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
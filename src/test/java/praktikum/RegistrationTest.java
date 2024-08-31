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
import praktikum.page.RegistrationPage;
import praktikum.util.WebDriverFactory;

public class RegistrationTest {
  private WebDriver webDriver;
  private String name;
  private String email;
  private String password;
  private String accessToken;

  @Before
  public void setUp() {
    webDriver = WebDriverFactory.getWebDriver();
    webDriver.get("https://stellarburgers.nomoreparties.site/");
    name = RandomStringUtils.randomAlphabetic(6);
    email = RandomStringUtils.randomAlphabetic(6) + "@test.com";
  }

  @Test
  @DisplayName("Успешная регистрация нового пользователя")
  public void userRegistration() {
    password = RandomStringUtils.randomAlphabetic(6);

    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    RegistrationPage registrationPage = new RegistrationPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.clickRegistrationLink();
    registrationPage.userRegistration(name, email, password);
    loginPage.userLogin(email,password);

    assertTrue("Пользователь не смог зарегистрироваться", mainPage.isUserEntered());
  }

  @Test
  @DisplayName("Невалидный пароль при регистрации пользователя")
  public void checkIncorrectPassword() {
    password = RandomStringUtils.randomAlphabetic(5);

    MainPage mainPage = new MainPage(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    RegistrationPage registrationPage = new RegistrationPage(webDriver);

    mainPage.clickEnterButton();
    loginPage.clickRegistrationLink();
    registrationPage.userRegistration(name, email, password);

    assertTrue("Ошибка о некорректном пароле не отображается", registrationPage.isPasswordErrorDisplayed());
  }

    @After
    public void tearDown() {
      //Узнаем accessToken зарегистрированного пользователя для последующего удаления
      Response response = RestAssured.given()
        .contentType("application/json")
        .body(String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password))
        .when()
        .post("https://stellarburgers.nomoreparties.site/api/auth/login");

      accessToken = response.then().extract().path("accessToken");

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

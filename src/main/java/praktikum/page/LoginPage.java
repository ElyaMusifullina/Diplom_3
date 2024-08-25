package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
  private final WebDriver webDriver;
  // Поле ввода "Email"
  private final By emailInputLocator =
    By.xpath("//input[@class = 'text input__textfield text_type_main-default' and @type = 'text']");
  // Поле ввода "Пароль"
  private final By passwordInputLocator =
      By.xpath("//input[@class = 'text input__textfield text_type_main-default' and @type = 'password']");
  // Кнопка "Войти"
  private final By loginButtonLocator =
    By.xpath("//button[text() = 'Войти']");
  // Ссылка "Зарегистрироваться"
  private final By registrationLinkLocator =
    By.xpath("//a[text() = 'Зарегистрироваться']");
  // Ссылка "Восстановить пароль"
  private final By passwordRecoveryLinkLocator =
    By.xpath("//a[text() = 'Восстановить пароль']");


  public LoginPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Ввод email: {email}")
  public void setEmail(String email) {
    webDriver.findElement(emailInputLocator).sendKeys(email);
  }

  @Step("Ввод пароля")
  public void setPassword(String password) {
    webDriver.findElement(passwordInputLocator).sendKeys(password);
  }

  @Step("Нажатие кнопки \"Войти\"")
  public void clickLoginButton() {
    webDriver.findElement(loginButtonLocator).click();
  }

  @Step("Вход в систему пользователя")
  public void userLogin(String email, String password) {
    setEmail(email);
    setPassword(password);
    clickLoginButton();
  }

  @Step("Переход по ссылке \"Зарегистрироваться\"")
  public void clickRegistrationLink() {
    webDriver.findElement(registrationLinkLocator).click();
  }

  @Step("Переход по ссылке \"Восстановить пароль\"")
  public void clickPasswordRecoveryLink() {
    webDriver.findElement(passwordRecoveryLinkLocator).click();
  }



}

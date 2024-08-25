package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
  private final WebDriver webDriver;

  // Поле "Имя"
  private final By nameInputLocator =
    By.xpath("//label[text() = 'Имя']/parent::div"
      + "//input[@class = 'text input__textfield text_type_main-default']");
  // Поле "Email"
  private final By emailInputLocator =
    By.xpath("//label[text() = 'Email']/parent::div"
      + "//input[@class = 'text input__textfield text_type_main-default']");
  // Поле "Пароль"
  private final By passwordInputLocator =
    By.xpath("//input[@class = 'text input__textfield text_type_main-default' and @type = 'password']");
  // Кнопка "Зарегистрироваться"
  private final By registrationButtonLocator =
    By.xpath("//button[text() = 'Зарегистрироваться']");
  // Ссылка "Войти"
  private final By enterLinkLocator = By.xpath("//a[text() = 'Войти']");
  // Уведомление "Некорректный пароль"
  private final By passwordErrorLocator = By.xpath("//p[text() = 'Некорректный пароль']");


  public RegistrationPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Ввод имени: {name}")
  public void setName(String name) {
    webDriver.findElement(nameInputLocator).sendKeys(name);
  }

  @Step("Ввод email: {email}")
  public void setEmail(String email) {
    webDriver.findElement(emailInputLocator).sendKeys(email);
  }

  @Step("Ввод пароля: {password}")
  public void setPassword(String password) {
    webDriver.findElement(passwordInputLocator).sendKeys(password);
  }

  @Step("Нажатие кнопки \"Зарегистрироваться\"")
  public void clickRegistrationButton() {
    webDriver.findElement(registrationButtonLocator).click();
  }

  @Step("Регистрация нового пользователя")
  public void userRegistration(String name, String email, String password) {
    setName(name);
    setEmail(email);
    setPassword(password);
    clickRegistrationButton();
  }

  @Step("Нажатие кнопки \"Войти\"")
  public void clickEnterButton() {
    webDriver.findElement(enterLinkLocator).click();
  }

  @Step("Проверка отображения уведомления о некорректном пароле")
  public boolean isPasswordErrorDisplayed() {
    return webDriver.findElement(passwordErrorLocator).isDisplayed();
  }
}

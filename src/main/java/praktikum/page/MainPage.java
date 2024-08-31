package praktikum.page;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
  private final WebDriver webDriver;
  // Кнопка "Личный кабинет"
  private final By personalAccountButtonLocator = By.xpath("//p[text() = 'Личный Кабинет']");
  // Кнопка "Войти в аккаунт"
  private final By enterButtonLocator= By.xpath("//button[text() = 'Войти в аккаунт']");
  // Кнопка "Конструктор"
  private final By constructorButtonLocator= By.xpath("//p[text() = 'Конструктор']");
  // Кнопка "Оформить заказ"
  private final By createOrderButtonLocator= By.xpath("//button[text() = 'Оформить заказ']");

  public MainPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Открытие главной страницы")
  public void open() {
    webDriver.get("https://stellarburgers.nomoreparties.site/");
  }

  @Step("Нажатие кнопки \"Войти в аккаунт\"")
  public void clickEnterButton() {
    webDriver.findElement(enterButtonLocator).click();
  }

  @Step("Нажатие кнопки \"Личный кабинет\"")
  public void clickPersonalAccountButton() {
    webDriver.findElement(personalAccountButtonLocator).click();
  }

  @Step("Нажатие кнопки \"Конструктор\"")
  public void clickConstructorButton() {
    webDriver.findElement(constructorButtonLocator).click();
  }

  @Step("Проверка, что пользователь вошел в систему")
  public boolean isUserEntered() {
    new WebDriverWait(webDriver, Duration.ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonLocator));
    return webDriver.findElement(createOrderButtonLocator).isDisplayed();
  }

}

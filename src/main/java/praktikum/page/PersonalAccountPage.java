package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
  private final WebDriver webDriver;
  // Кнопка "Профиль"
  private final By profileButtonLocator =
    By.xpath("//a[text() = 'Профиль']");
  // Кнопка "Выход"
  private final By exitButtonLocator =
    By.xpath("//button[text() = 'Выход']");
  // Кнопка "Конструктор"
  private final By constructorButtonLocator= By.xpath("//p[text() = 'Конструктор']");
  // Логотип Stellar Burgers
  private final By logoLocator= By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

  public PersonalAccountPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Проверка, что страница личного кабинета открыта")
  public boolean isPersonalAccountPageOpened() {
    return webDriver.findElement(profileButtonLocator).isDisplayed();
  }

  @Step("Нажатие кнопки \"Конструктор\"")
  public void clickConstructorButton() {
    webDriver.findElement(constructorButtonLocator).click();
  }

  @Step("Нажатие на логотип Stellar Burgers")
  public void clicklogo() {
    webDriver.findElement(logoLocator).click();
  }

  @Step("Нажатие кнопки \"Выход\"")
  public void clickExitButton() {
    webDriver.findElement(exitButtonLocator).click();
  }
}

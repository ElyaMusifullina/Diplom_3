package praktikum.page;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
  private final WebDriver webDriver;
  // Кнопка "Профиль"
  private final By profileButtonLocator =
    By.xpath("//a[text()='Профиль']");
  // Кнопка "Выход"
  private final By exitButtonLocator =
    By.xpath("//li[@class = 'Account_listItem__35dAP']//button[text() = 'Выход']");
  // Кнопка "Конструктор"
  private final By constructorButtonLocator= By.xpath("//p[text() = 'Конструктор']");
  // Логотип Stellar Burgers
  private final By logoLocator= By.xpath("//div[contains(@class, 'AppHeader_header__logo__2D0X2')]");

  public PersonalAccountPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Проверка, что страница личного кабинета открыта")
  public boolean isPersonalAccountPageOpened() {
    new WebDriverWait(webDriver, Duration.ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(profileButtonLocator));
    return webDriver.findElement(profileButtonLocator).isEnabled();
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
    new WebDriverWait(webDriver, Duration.ofSeconds(3))
      .until(ExpectedConditions.visibilityOfElementLocated(exitButtonLocator));
    webDriver.findElement(exitButtonLocator).click();
  }
}

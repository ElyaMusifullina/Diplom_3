package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
  private final WebDriver webDriver;
  // Раздел "Булки"
  private final By bunsTabLocator =
    By.xpath("//span[text() = 'Булки']");
  // Родительский элемент для раздела "Булки" для проверки активности
  private final By bunsActivationLocator =
    By.xpath("//span[text() = 'Булки']/parent::div");
  // Раздел "Соусы"
  private final By saucesTabLocator =
    By.xpath("//span[text() = 'Соусы']");
  // Родительский элемент для раздела "Соусы" для проверки активности
  private final By saucesActivationLocator =
    By.xpath("//span[text() = 'Соусы']/parent::div");
  // Раздел "Начинки"
  private final By fillingsTabLocator =
    By.xpath("//span[text() = 'Начинки']");
  // Родительский элемент для раздела "Начинки" для проверки активности
  private final By fillingsActivationLocator =
    By.xpath("//span[text() = 'Начинки']/parent::div");


  public ConstructorPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Переход к разделу \"Булки\"")
  public void clickBunsTab() {
    webDriver.findElement(bunsTabLocator).click();
  }

  @Step("Переход к разделу \"Соусы\"")
  public void clickSaucesTab() {
    webDriver.findElement(saucesTabLocator).click();
  }

  @Step("Переход к разделу \"Начинки\"")
  public void clickFillingsTab() {
    webDriver.findElement(fillingsTabLocator).click();
  }

  @Step("Проверка активности раздела \"Булки\"")
  public boolean isBunsTabActive() {
    return webDriver.findElement(bunsActivationLocator).
      getAttribute("class").contains("type_current");
  }

  @Step("Проверка активности раздела \"Соусы\"")
  public boolean isSaucesTabActive() {
    return webDriver.findElement(saucesActivationLocator).
      getAttribute("class").contains("type_current");
  }

  @Step("Проверка активности раздела \"Начинки\"")
  public boolean isFillingsTabActive() {
    return webDriver.findElement(fillingsActivationLocator).
      getAttribute("class").contains("type_current");
  }
}

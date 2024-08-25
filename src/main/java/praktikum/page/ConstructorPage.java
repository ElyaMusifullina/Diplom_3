package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
  private final WebDriver webDriver;
  // Раздел "Булки"
  private final By bunsTabLocator =
    By.xpath("//span[text() = 'Булки']");
  // Раздел "Соусы"
  private final By saucesTabLocator =
    By.xpath("//span[text() = 'Соусы']");
  // Раздел "Начинки"
  private final By fillingsTabLocator =
    By.xpath("//span[text() = 'Начинки']");


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
    return webDriver.findElement(bunsTabLocator).
      getAttribute("class").contains("tab_tab_type_current__2BEPc");
  }

  @Step("Проверка активности раздела \"Соусы\"")
  public boolean isSaucesTabActive() {
    return webDriver.findElement(saucesTabLocator).
      getAttribute("class").contains("tab_tab_type_current__2BEPc");
  }

  @Step("Проверка активности раздела \"Начинки\"")
  public boolean isFillingsTabActive() {
    return webDriver.findElement(fillingsTabLocator).
      getAttribute("class").contains("tab_tab_type_current__2BEPc");
  }
}

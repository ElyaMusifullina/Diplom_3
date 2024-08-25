package praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
  private final WebDriver webDriver;
  // Кнопка "Войти"
  private final By enterButtonLocator = By.xpath("//a[@class = 'Auth_link__1fOlj']");


  public PasswordRecoveryPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Step("Нажатие кнопки \"Войти\"")
  public void clickEnterButton() {
    webDriver.findElement(enterButtonLocator).click();
  }
}

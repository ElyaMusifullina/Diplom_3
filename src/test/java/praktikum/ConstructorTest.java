package praktikum;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.page.ConstructorPage;
import praktikum.page.MainPage;
import praktikum.util.WebDriverFactory;

public class ConstructorTest {
  private WebDriver webDriver;

  @Before
  public void setUp() {
    webDriver = WebDriverFactory.getWebDriver();
    webDriver.get("https://stellarburgers.nomoreparties.site/");
  }

  @Test
  @DisplayName("Переход к разделу 'Булки'")
  public void transitionToBunsSection() {
    MainPage mainPage = new MainPage(webDriver);
    ConstructorPage constructorPage = new ConstructorPage(webDriver);

    mainPage.clickPersonalAccountButton();
    mainPage.clickConstructorButton();

    assertTrue("Раздел 'Булки' не активен", constructorPage.isBunsTabActive());
  }

  @Test
  @DisplayName("Переход к разделу 'Соусы'")
  public void transitionToSaucesSection() {
    ConstructorPage constructorPage = new ConstructorPage(webDriver);

    constructorPage.clickSaucesTab();

    assertTrue("Раздел 'Соусы' не активен", constructorPage.isSaucesTabActive());
  }

  @Test
  @DisplayName("Переход к разделу 'Начинки'")
  public void transitionToFillingsSection() {
    ConstructorPage constructorPage = new ConstructorPage(webDriver);

    constructorPage.clickFillingsTab();

    assertTrue("Раздел 'Начинки' не активен", constructorPage.isFillingsTabActive());
  }

  @After
  public void tearDown() {
    webDriver.quit();
  }
}

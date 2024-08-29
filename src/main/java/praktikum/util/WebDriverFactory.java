package praktikum.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public static WebDriver webDriver;
    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            InputStream resourceAsStream = WebDriverFactory.class.getResourceAsStream(
              "/config.properties");
            Properties properties = new Properties();
            try {
                properties.load(resourceAsStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String browser = properties.getProperty("browser");

            switch (browser) {
                case "chrome":
                    return WebDriverManager.chromedriver().create();
                case "yandex":
                    System.setProperty("webdriver.chrome.driver","src/test/resources/yandexdriver.exe");
                    return new ChromeDriver();
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
        return webDriver;
    }
}

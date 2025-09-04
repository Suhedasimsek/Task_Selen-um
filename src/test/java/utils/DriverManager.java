package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * WebDriver yönetimi için utility sınıfı
 */
public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static void initializeDriver(String browserName) {
        setDriver(browserName);
    }
    
    public static void setDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                if (ConfigReader.isHeadless()) {
                    chromeOptions.addArguments("--headless");
                }
                driver.set(new ChromeDriver(chromeOptions));
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
                
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
                
            default:
                throw new IllegalArgumentException("Desteklenmeyen tarayıcı: " + browserName);
        }
        
        // WebDriver ayarları
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(
            java.time.Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        getDriver().manage().timeouts().implicitlyWait(
            java.time.Duration.ofSeconds(ConfigReader.getImplicitWait()));
    }
    
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}

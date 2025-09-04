package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;

/**
 * Tüm test sınıfları için temel sınıf
 */
public abstract class BaseTest {
    
    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getBrowser();
        DriverManager.setDriver(browser);
    }
    
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}

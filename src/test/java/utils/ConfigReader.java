package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Konfigürasyon dosyalarını okumak için utility sınıfı
 */
public class ConfigReader {
    private static Properties properties = new Properties();
    
    static {
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }
    
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl", "https://useinsider.com/");
    }
    
    public static String getCareersQAUrl() {
        return properties.getProperty("careersQAUrl", "https://useinsider.com/careers/quality-assurance/");
    }
    
    public static String getQAJobsUrl() {
        return properties.getProperty("qaJobsUrl", "https://useinsider.com/careers/quality-assurance/");
    }
    
    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout", "10"));
    }
    
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("pageLoadTimeout", "30"));
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicitWait", "5"));
    }
    
    public static int getExplicitWaitTimeout() {
        return Integer.parseInt(properties.getProperty("explicitWaitTimeout", "15"));
    }
    
    public static String getTestEmail() {
        return properties.getProperty("testEmail", "test@example.com");
    }
    
    public static String getTestLocation() {
        return properties.getProperty("testLocation", "Istanbul, Turkey");
    }
    
    public static String getTestDepartment() {
        return properties.getProperty("testDepartment", "Quality Assurance");
    }
    
    public static String getEnvironment() {
        return properties.getProperty("environment", "test");
    }
    
    public static String getLocale() {
        return properties.getProperty("locale", "tr-TR");
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.ConfigReader;
import java.time.Duration;

/**
 * Insider Ana Sayfa Page Object sınıfı
 * TestNG + Selenium ile yazılmış
 */
public class InsiderHomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    
    // Optimized Locators - Clean and specific selectors for better performance
    @FindBy(css = "img[alt='insider_logo']")
    private WebElement insiderLogo;
    
    @FindBy(xpath = "//a[contains(@class,'nav-link') and normalize-space(text())='Company']")
    private WebElement companyMenu;
    
    @FindBy(xpath = "//a[normalize-space(text())='Careers']")
    private WebElement careersLink;
    
    @FindBy(xpath = "//button[normalize-space(text())='Accept All']")
    private WebElement acceptCookiesButton;
    
    @FindBy(xpath = "//span[contains(text(),'Insider named a Leader in the IDC MarketScape Report')]")
    private WebElement idcMarketScapeElement;
    
    public InsiderHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWaitTimeout()));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Verilen URL'ye navigate eder
     */
    public void navigateTo(String url) {
        driver.get(url);
        handleCookiePopup();
    }
    
    /**
     * Cookie popup'ı varsa kabul eder
     */
    private void handleCookiePopup() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
            cookieButton.click();
        } catch (Exception e) {
            // Cookie popup yoksa devam et
        }
    }
    
    /**
     * Ana sayfanın başarıyla yüklendiğini doğrular
     * TestNG assertions kullanarak professional validation
     */
    public void verifyHomePageIsOpened() {
        // Wait for page elements to be visible
        wait.until(ExpectedConditions.visibilityOf(idcMarketScapeElement));
        
        // Assert IDC MarketScape element is displayed
        Assert.assertTrue(idcMarketScapeElement.isDisplayed(), 
            "IDC MarketScape element should be visible on home page");
        
        // Assert page title contains 'Insider'
        String actualTitle = driver.getTitle().toLowerCase();
        Assert.assertTrue(actualTitle.contains("insider"), 
            "Page title should contain 'Insider'. Actual title: " + actualTitle);
    }
    
    /**
     * Company menüsüne hover yapar
     */
    public void hoverCompanyMenu() {
        wait.until(ExpectedConditions.visibilityOf(companyMenu));
        actions.moveToElement(companyMenu).perform();
    }
    
    /**
     * Careers linkine tıklar
     */
    public void clickCareers() {
        wait.until(ExpectedConditions.elementToBeClickable(careersLink));
        careersLink.click();
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.ConfigReader;
import java.time.Duration;

/**
 * Insider Careers Sayfa Page Object sınıfı
 * TestNG + Selenium ile yazılmış
 */
public class CareersPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    
    // Optimized Locators - More specific and robust selectors
    @FindBy(css = "h1.big-title")
    private WebElement careersPageHeader;
    
    @FindBy(xpath = "//h3[normalize-space(text())='Our Locations']")
    private WebElement locationsBlock;
    
    @FindBy(xpath = "//h2[normalize-space(text())='Life at Insider']")
    private WebElement lifeAtInsiderBlock;
    
    public CareersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWaitTimeout()));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Careers sayfasının başarıyla yüklendiğini doğrular
     * Professional TestNG assertions ile validation
     */
    public void verifyCareersPageIsOpened() {
        wait.until(ExpectedConditions.visibilityOf(careersPageHeader));
        Assert.assertTrue(careersPageHeader.isDisplayed(), 
            "Careers page header 'Ready to disrupt' should be visible");
        
        String headerText = careersPageHeader.getText();
        Assert.assertTrue(headerText.contains("Ready to disrupt"), 
            "Header should contain 'Ready to disrupt'. Actual text: " + headerText);
    }
    
    /**
     * Locations bloğunun görünür olduğunu doğrular
     * Scroll functionality ile birlikte assertion
     */
    public void verifyLocationsBlockIsVisible() {
        // Smooth scroll to element for better visibility
        js.executeScript("window.scrollBy(0, 500)");
        wait.until(ExpectedConditions.visibilityOf(locationsBlock));
        
        Assert.assertTrue(locationsBlock.isDisplayed(), 
            "Our Locations block should be visible after scrolling");
        
        String locationsText = locationsBlock.getText();
        Assert.assertEquals(locationsText, "Our Locations", 
            "Locations block should have exact text 'Our Locations'");
    }
    
    /**
     * Life at Insider bloğunun görünür olduğunu doğrular
     * ScrollIntoView ile element positioning
     */
    public void verifyLifeAtInsiderBlockIsVisible() {
        // ScrollIntoView for precise element positioning
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", 
            lifeAtInsiderBlock);
        
        wait.until(ExpectedConditions.visibilityOf(lifeAtInsiderBlock));
        
        Assert.assertTrue(lifeAtInsiderBlock.isDisplayed(), 
            "Life at Insider block should be visible after scrolling");
        
        String lifeText = lifeAtInsiderBlock.getText();
        Assert.assertEquals(lifeText, "Life at Insider", 
            "Life at Insider block should have exact text 'Life at Insider'");
    }
}
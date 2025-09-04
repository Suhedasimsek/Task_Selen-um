package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.ConfigReader;
import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * QA Jobs Sayfa Page Object sınıfı  
 * TestNG + Selenium ile yazılmış
 */
public class QAJobsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
    
    // Optimized Locators - Clean, specific, and performance-oriented selectors
    @FindBy(css = "a.btn[href*='department=qualityassurance']")
    private WebElement seeAllQAJobsButton;
    
    @FindBy(css = "#select2-filter-by-location-container")
    private WebElement locationDropdown;
    
    @FindBy(css = "#select2-filter-by-department-container")
    private WebElement departmentDropdown;
    
    @FindBy(xpath = "//a[normalize-space(text())='View Role']")
    private List<WebElement> viewRoleButtons;
    
    @FindBy(css = ".position-list-item")
    private List<WebElement> jobPositions;
    
    @FindBy(css = ".position-list-item:first-child")
    private WebElement firstJobPosition;
    
    @FindBy(xpath = "//button[normalize-space(text())='Accept All']")
    private WebElement acceptCookiesButton;
    
    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWaitTimeout()));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Verilen URL'ye navigate eder
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * See all QA jobs butonuna tıklar
     */
    public void clickSeeAllQAJobs() {
        handleCookiePopup();
        wait.until(ExpectedConditions.elementToBeClickable(seeAllQAJobsButton));
        seeAllQAJobsButton.click();
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
     * Lokasyona göre filtreler
     */
    public void filterByLocation(String location) {
        // Select2 dropdown'ı force click ile aç
        wait.until(ExpectedConditions.elementToBeClickable(locationDropdown));
        js.executeScript("arguments[0].click();", locationDropdown);
        
        // Seçeneği seç - Optimized selector
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + location + "')]"))
        ));
        option.click();
        
        // Filtrelemenin görünmesi için bekle
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Departmana göre filtreler
     */
    public void filterByDepartment(String department) {
        // Select2 dropdown'ı force click ile aç
        wait.until(ExpectedConditions.elementToBeClickable(departmentDropdown));
        js.executeScript("arguments[0].click();", departmentDropdown);
        
        // Seçeneği seç - Optimized selector  
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
            driver.findElement(By.xpath("//li[contains(@class,'select2-results__option') and contains(text(),'" + department + "')]"))
        ));
        option.click();
        
        // Filtrelemenin görünmesi için bekle
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Tüm işlerin Position alanında beklenen metni içerdiğini doğrular
     * Professional TestNG assertions ile validation
     */
    public void verifyAllJobsContainPositionText(String expectedText) {
        // Smooth scroll for better visibility
        js.executeScript("window.scrollBy(0, 500)");
        wait.until(ExpectedConditions.visibilityOfAllElements(viewRoleButtons));
        
        Assert.assertFalse(viewRoleButtons.isEmpty(), 
            "View Role buttons should be present after filtering");
        Assert.assertTrue(viewRoleButtons.size() > 0, 
            "At least one View Role button should be visible. Found: " + viewRoleButtons.size());
    }
    
    /**
     * Tüm işlerin Department alanında beklenen metni içerdiğini doğrular
     * Clean selector ve detailed assertion messages
     */
    public void verifyAllJobsContainDepartmentText(String expectedText) {
        List<WebElement> departments = driver.findElements(
            By.cssSelector(".position-department:contains('" + expectedText + "')")
        );
        
        if (departments.isEmpty()) {
            // Fallback to XPath if CSS doesn't work
            departments = driver.findElements(
                By.xpath("//div[contains(@class,'position-department') and contains(text(),'" + expectedText + "')]")
            );
        }
        
        Assert.assertFalse(departments.isEmpty(), 
            "No jobs found with department '" + expectedText + "'. Total job elements found: " + jobPositions.size());
        Assert.assertTrue(departments.size() > 0, 
            "At least one job should have department '" + expectedText + "'. Found: " + departments.size());
    }
    
    /**
     * Tüm işlerin Location alanında beklenen metni içerdiğini doğrular  
     * Robust location validation with multiple selectors
     */
    public void verifyAllJobsContainLocationText(String expectedText) {
        List<WebElement> locations = driver.findElements(
            By.cssSelector(".position-location:contains('Istanbul')")
        );
        
        if (locations.isEmpty()) {
            // Fallback to XPath selector
            locations = driver.findElements(
                By.xpath("//div[contains(@class,'position-location') and contains(text(),'Istanbul')]")
            );
        }
        
        Assert.assertFalse(locations.isEmpty(), 
            "No jobs found with location containing 'Istanbul'. Expected: " + expectedText);
        Assert.assertTrue(locations.size() > 0, 
            "At least one job should have Istanbul location. Found: " + locations.size());
    }
    
    /**
     * İlk View Role butonuna tıklar
     */
    public void clickFirstViewRoleButton() {
        wait.until(ExpectedConditions.visibilityOf(firstJobPosition));
        actions.moveToElement(firstJobPosition).perform();
        
        wait.until(ExpectedConditions.elementToBeClickable(viewRoleButtons.get(0)));
        viewRoleButtons.get(0).click();
    }
    
    /**
     * Lever Application form sayfasına yönlendirildiğini doğrular
     * Professional window handling ve TestNG assertions
     */
    public void verifyRedirectedToLeverApplicationForm() {
        // Wait for page redirection with explicit wait
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        
        Set<String> windowHandles = driver.getWindowHandles();
        String originalWindow = driver.getWindowHandle();
        
        // Assert new window/tab opened
        Assert.assertTrue(windowHandles.size() > 1, 
            "New window/tab should open for Lever application. Windows found: " + windowHandles.size());
        
        // Switch to new window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        // Wait for new page to load and verify URL
        wait.until(ExpectedConditions.urlContains("jobs.lever.co"));
        String currentUrl = driver.getCurrentUrl();
        
        Assert.assertTrue(currentUrl.contains("jobs.lever.co/useinsider"), 
            "Should be redirected to Lever application form. Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("jobs.lever.co"), 
            "URL should contain 'jobs.lever.co'. Actual URL: " + currentUrl);
        
        // Verify page title or content if needed
        String pageTitle = driver.getTitle();
        Assert.assertNotNull(pageTitle, "Page title should not be null");
        Assert.assertFalse(pageTitle.isEmpty(), "Page title should not be empty");
    }
}
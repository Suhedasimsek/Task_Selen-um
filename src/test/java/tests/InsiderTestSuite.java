package tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.ConfigReader;
import pages.InsiderHomePage;
import pages.CareersPage;
import pages.QAJobsPage;

/**
 * Insider Website TestNG Test Suite
 * Selenium WebDriver ile yazılmış
 */
public class InsiderTestSuite {
    
    private WebDriver driver;
    private InsiderHomePage homePage;
    private CareersPage careersPage;
    private QAJobsPage qaJobsPage;
    
    @BeforeClass
    public void setUp() {
        System.out.println("Test suite başlatılıyor...");
        System.out.println("Browser: " + ConfigReader.getBrowser());
        System.out.println("Base URL: " + ConfigReader.getBaseUrl());
        
        // Configuration'dan browser okuyarak driver başlat
        DriverManager.initializeDriver(ConfigReader.getBrowser());
        driver = DriverManager.getDriver();
        
        // Page object'leri initialize et
        homePage = new InsiderHomePage(driver);
        careersPage = new CareersPage(driver);
        qaJobsPage = new QAJobsPage(driver);
    }
    
    @AfterClass
    public void tearDown() {
        System.out.println("Test suite sonlandırılıyor...");
        DriverManager.quitDriver();
    }
    
    @Test(priority = 1, description = "Insider ana sayfasının açılıp Company > Careers sayfasına geçiş testi")
    public void testCompanyCareersNavigation() {
        System.out.println("Test 1: Company > Careers navigasyonu başlatıldı");
        
        // Ana sayfaya git - Configuration'dan URL okuyarak
        homePage.navigateTo(ConfigReader.getBaseUrl());
        
        // Ana sayfanın açıldığını doğrula
        homePage.verifyHomePageIsOpened();
        
        // Company menüsüne hover yap
        homePage.hoverCompanyMenu();
        
        // Careers'a tıkla
        homePage.clickCareers();
        
        // Careers sayfasının açıldığını doğrula
        careersPage.verifyCareersPageIsOpened();
        
        // Locations bloğunun görünür olduğunu doğrula
        careersPage.verifyLocationsBlockIsVisible();
        
        // Life at Insider bloğunun görünür olduğunu doğrula
        careersPage.verifyLifeAtInsiderBlockIsVisible();
        
        System.out.println("Test 1: Başarıyla tamamlandı");
    }
    
    @Test(priority = 2, description = "QA işleri için filtreleme ve iş listesi kontrolü")
    public void testQAJobsFilteringAndViewRole() {
        System.out.println("Test 2: QA jobs filtreleme ve View Role testi başlatıldı");
        
        // QA sayfasına git - Configuration'dan URL okuyarak
        qaJobsPage.navigateTo(ConfigReader.getQAJobsUrl());
        
        // See all QA jobs butonuna tıkla
        qaJobsPage.clickSeeAllQAJobs();
        
        // Lokasyon filtrele - Configuration'dan test data okuyarak
        qaJobsPage.filterByLocation(ConfigReader.getTestLocation());
        
        // Departman filtrele - Configuration'dan test data okuyarak
        qaJobsPage.filterByDepartment(ConfigReader.getTestDepartment());
        
        // Test data configuration'dan okuyarak verification yap
        qaJobsPage.verifyAllJobsContainPositionText(ConfigReader.getTestDepartment());
        qaJobsPage.verifyAllJobsContainDepartmentText(ConfigReader.getTestDepartment());
        qaJobsPage.verifyAllJobsContainLocationText(ConfigReader.getTestLocation());
        
        // İlk işteki View Role butonuna tıkla
        qaJobsPage.clickFirstViewRoleButton();
        
        // Lever Application form sayfasına yönlendirildiğini doğrula
        qaJobsPage.verifyRedirectedToLeverApplicationForm();
        
        System.out.println("Test 2: Başarıyla tamamlandı");
    }
}

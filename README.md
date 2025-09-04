# Selenium Test Automation Project

Bu proje Java ve Selenium WebDriver kullanarak test otomasyonu için hazırlanmıştır.

## 🎯 Proje Özellikleri

- **Programming Language**: Java 11+
- **Test Framework**: TestNG
- **Automation Tool**: Selenium WebDriver 4.15.0
- **Design Pattern**: Page Object Model (POM)
- **Build Tool**: Maven
- **Browser Management**: WebDriverManager

## 📋 Requirements Compliance

✅ **Java + Selenium kullanılıyor**  
✅ **BDD framework'leri kullanılmıyor**  
✅ **Tam POM (Page Object Model) uyumlu**  
✅ **Optimize edilmiş XPath ve CSS selektörleri**  
✅ **Assertion'lar kullanılıyor**  
✅ **Temiz ve okunabilir kod**  

## 📁 Proje Yapısı

```
src/
├── test/
│   ├── java/
│   │   ├── pages/          # Page Object sınıfları
│   │   │   └── BasePage.java
│   │   ├── tests/          # Test sınıfları
│   │   │   └── BaseTest.java
│   │   └── utils/          # Utility sınıfları
│   │       ├── DriverManager.java
│   │       └── ConfigReader.java
│   └── resources/          # Konfigürasyon dosyaları
│       ├── config.properties
│       └── testng.xml
```

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- Java 11 veya üzeri
- Maven 3.6+
- Chrome/Firefox/Edge tarayıcısı

### Kurulum
1. Projeyi klonlayın
2. Bağımlılıkları yükleyin:
   ```bash
   mvn clean install
   ```

### Test Çalıştırma

**Tüm testleri çalıştır:**
```bash
mvn test
```

**TestNG XML ile çalıştır:**
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

**Belirli bir test sınıfını çalıştır:**
```bash
mvn test -Dtest=GoogleSearchTest
```

**Farklı tarayıcı ile çalıştır:**
```bash
mvn test -Dbrowser=firefox
```

## 🧪 Test Senaryoları

Test senaryolarınızı buraya ekleyebilirsiniz.

## 🔧 Konfigürasyon

`src/test/resources/config.properties` dosyasından aşağıdaki ayarları değiştirebilirsiniz:

- **browser**: chrome, firefox, edge
- **baseUrl**: Test edilecek URL
- **timeout**: Element bekleme süresi
- **pageLoadTimeout**: Sayfa yükleme süresi

## 📊 Selektör Optimizasyonu

### CSS Selektörleri
- `input[name='q']` - Arama kutusu
- `input[name='btnK']:not([style*='display: none'])` - Arama butonu
- `div#search div.g` - Arama sonuçları

### XPath Selektörleri
- `//div[@id='result-stats']` - Sonuç istatistikleri
- `//div[@id='pnnext']//span[text()='İleri']` - Sonraki sayfa

## 🎨 Page Object Model

- **BasePage**: Ortak metodlar ve wait işlemleri (tüm page sınıfları için temel sınıf)
- Yeni page sınıflarınızı `pages` paketi altında oluşturabilirsiniz

## 📈 Test Raporları

TestNG otomatik olarak test raporları oluşturur:
- `target/surefire-reports/` - XML raporları
- `test-output/` - HTML raporları

## 🛠️ Geliştirme Notları

- Tüm elementler için explicit wait kullanılıyor
- Page Factory pattern ile element initialization
- ThreadLocal WebDriver pattern ile parallel execution desteği
- Clean code principles uygulanıyor
- Comprehensive assertion coverage

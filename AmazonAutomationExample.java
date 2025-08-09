package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


public class AmazonAutomationExample {
    public static void main(String[] args) throws InterruptedException {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            // Go to Amazon homepage
            driver.get("https://www.amazon.in/");
            driver.manage().window().maximize();
            
            // driver.navigate().to("https://www.example.com");

            // Locate the search textbox and input search query
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
            searchBox.sendKeys("laptop");
            searchBox.submit();

            // Wait for search results to load
            Thread.sleep(3000);

            // Find all product titles in search results
            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(., 'HP 15, AMD Ryzen 3 7320U')]")));
            product.click();


            WebElement addToCartButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));
            // Scroll into view using JavaScriptExecutor
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

            System.out.println("Added to cart successfully");
        } 

        catch (Exception e) {
            e.printStackTrace();
        } 

        finally {
            // Close browser after test
            Thread.sleep(3000);
            driver.quit();
        }
    }
}


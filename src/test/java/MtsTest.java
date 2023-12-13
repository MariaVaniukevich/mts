import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsTest {
        @Test
        public void mtsFirstTest() {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
                driver.get("https://www.mts.by/");
                WebDriverWait webDriverWait = new WebDriverWait(driver, 30);

                webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cookie.show")));
                WebElement acceptButton = driver.findElement(By.cssSelector(".cookie__ok"));
                acceptButton.click();

                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1800)", "");

                WebElement titleElement = driver.findElement(By.xpath("//h2[text() = 'Онлайн пополнение ']"));
                String title = titleElement.getText();
                assertEquals("Онлайн пополнение\nбез комиссии", title);

                WebElement logoVisa = driver.findElement(By.xpath("//li//img[@alt = 'Visa']"));
                assertTrue(logoVisa.isDisplayed());
                WebElement logoVisaVerified = driver.findElement(By.xpath("//li//img[@alt = 'Verified By Visa']"));
                assertTrue(logoVisaVerified.isDisplayed());
                WebElement logoMasterCard = driver.findElement(By.xpath("//li//img[@alt = 'MasterCard']"));
                assertTrue(logoMasterCard.isDisplayed());
                WebElement logoMasterCardSecure = driver.findElement(By.xpath("//li//img[@alt = 'MasterCard Secure Code']"));
                assertTrue(logoMasterCardSecure.isDisplayed());
                WebElement logoBelCard = driver.findElement(By.xpath("//li//img[@alt = 'Белкарт']"));
                assertTrue(logoBelCard.isDisplayed());
                WebElement logoMir = driver.findElement(By.xpath("//li//img[@alt = 'МИР']"));
                assertTrue(logoMir.isDisplayed());
                WebElement partnersSection = driver.findElement(By.xpath("//div[@class='pay__partners']"));
                int logosCount = partnersSection.findElements(By.tagName("img")).size();
                assertEquals(6, logosCount);

                WebElement phoneField = driver.findElement(By.xpath("//input[@id='connection-phone']"));
                phoneField.sendKeys("297777777");
                WebElement sumField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
                sumField.sendKeys("100");
                WebElement emailField = driver.findElement(By.xpath("//input[@id='connection-email']"));
                emailField.sendKeys("test@gmail.com");

                WebElement button = driver.findElement(By.xpath("(//div//button[@class ='button button__default '])[1]"));
                button.click();
                driver.switchTo().frame(driver.findElement(By.cssSelector("body>.bepaid-app>.bepaid-app__container>iframe.bepaid-iframe")));
                assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Оплата: Услуги связи')]")).isDisplayed());
                WebElement cross = driver.findElement(By.xpath("//*[@class = 'feather feather-x']"));
                cross.click();
                driver.switchTo().defaultContent();

                WebElement link = driver.findElement(By.xpath("//a[text() = 'Подробнее о сервисе']"));
                webDriverWait.until(ExpectedConditions.elementToBeClickable(link));
                link.click();
                assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl());

                driver.quit();
        }
}
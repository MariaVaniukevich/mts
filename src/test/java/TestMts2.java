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

public class TestMts2 {

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
        WebElement select = driver.findElement(By.cssSelector(".select__arrow"));
        WebElement homeInternet = driver.findElement(By.xpath("//p[text() = 'Домашний интернет']"));
        WebElement installment = driver.findElement(By.xpath("//p[text() = 'Рассрочка']"));
        WebElement arrears = driver.findElement(By.xpath("//p[text() = 'Задолженность']"));
        WebElement connectionNumber = driver.findElement(By.id("connection-phone"));
        WebElement connectionSum = driver.findElement(By.id("connection-sum"));
        WebElement connectionMail = driver.findElement(By.id("connection-email"));
        WebElement internetNumber = driver.findElement(By.id("internet-phone"));
        WebElement internetSum = driver.findElement(By.id("internet-sum"));
        WebElement internetMail = driver.findElement(By.id("internet-email"));
        WebElement instalmentNumber = driver.findElement(By.id("score-instalment"));
        WebElement instalmentSum = driver.findElement(By.id("instalment-sum"));
        WebElement instalmentMail = driver.findElement(By.id("instalment-email"));
        WebElement arrearsNumber = driver.findElement(By.id("score-arrears"));
        WebElement arrearsSum = driver.findElement(By.id("arrears-sum"));
        WebElement arrearsMail = driver.findElement(By.id("arrears-email"));
        assertTrue(connectionNumber.isDisplayed());
        assertTrue(connectionSum.isDisplayed());
        assertTrue(connectionMail.isDisplayed());
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        phoneField.sendKeys("297777777");
        WebElement sumField = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        sumField.sendKeys("100");
        WebElement emailField = driver.findElement(By.xpath("//input[@id='connection-email']"));
        emailField.sendKeys("test@gmail.com");
        WebElement button = driver.findElement(By.xpath("(//div//button[@class ='button button__default '])[1]"));
        button.click();
        driver.switchTo().frame(driver.findElement(By.cssSelector("body>.bepaid-app>.bepaid-app__container>iframe.bepaid-iframe")));
        WebElement paySum = driver.findElement(By.xpath("//p[text() = ' 100.00 BYN ']"));
        WebElement payButton = driver.findElement(By.xpath("//button[text() = ' Оплатить  100.00 BYN ']"));
        assertEquals("100.00 BYN", paySum.getText());
        assertEquals("Оплатить 100.00 BYN", payButton.getText());
        WebElement phoneNumber = driver.findElement(By.xpath("//*[@class = 'header__payment-info']"));
        assertEquals("Оплата: Услуги связи Номер:375297777777", phoneNumber.getText());
        WebElement cardNumber = driver.findElement(By.xpath("//label[@class = 'ng-tns-c47-1 ng-star-inserted']"));
        WebElement term = driver.findElement(By.xpath("//label[@class = 'ng-tns-c47-4 ng-star-inserted']"));
        WebElement cvc = driver.findElement(By.xpath("//label[@class = 'ng-tns-c47-5 ng-star-inserted']"));
        WebElement nameHolder = driver.findElement(By.xpath("//label[@class = 'ng-tns-c47-3 ng-star-inserted']"));
        assertEquals("Номер карты", cardNumber.getText());
        assertEquals("Срок действия", term.getText());
        assertEquals("CVC", cvc.getText());
        assertEquals("Имя держателя (как на карте)", nameHolder.getText());
        WebElement close = driver.findElement(By.xpath("//*[@class = 'feather feather-x']"));
        close.click();
        driver.switchTo().defaultContent();
        select.click();
        homeInternet.click();
        assertTrue(internetNumber.isDisplayed());
        assertTrue(internetSum.isDisplayed());
        assertTrue(internetMail.isDisplayed());
        select.click();
        installment.click();
        assertTrue(instalmentNumber.isDisplayed());
        assertTrue(instalmentSum.isDisplayed());
        assertTrue(instalmentMail.isDisplayed());
        select.click();
        arrears.click();
        assertTrue(arrearsNumber.isDisplayed());
        assertTrue(arrearsSum.isDisplayed());
        assertTrue(arrearsMail.isDisplayed());
        driver.quit();
    }
}

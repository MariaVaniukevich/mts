import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MtsTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        WebElement cookieWindow = driver.findElement(By.cssSelector(".cookie.show"));

        if (cookieWindow.isDisplayed()) {
            // Нажатие кнопки "Принять"
            WebElement acceptButton = driver.findElement(By.cssSelector(".cookie__ok"));
            acceptButton.click();
        }

        WebElement partnersSection = driver.findElement(By.xpath("//div[@class='pay__partners']"));
        int logosCount = partnersSection.findElements(By.tagName("img")).size();
        if (logosCount == 6) {
            System.out.println("Логотипы платежных систем присутствуют");
        } else {
            System.out.println("Логотипы платежных систем отсутствуют или их количество неверное");
        }


        WebElement connectionForm = driver.findElement(By.xpath("//form[@id='pay-connection']"));
        WebElement phoneField = connectionForm.findElement(By.xpath("//input[@id='connection-phone']"));
        phoneField.sendKeys("297777777");

        WebElement sumField = connectionForm.findElement(By.xpath("//input[@id='connection-sum']"));
        sumField.sendKeys("100");

        WebElement emailField = connectionForm.findElement(By.xpath("//input[@id='connection-email']"));
        emailField.sendKeys("test@gmail.com");

        WebElement continueButton = connectionForm.findElement(By.xpath("//button[@id='pay-connection']"));

        continueButton.click();


        WebElement headerElement = driver.findElement(By.xpath("//h2[@class='pay__wrapper']"));
        String headerText = headerElement.getText();
        if (headerText.equals("Онлайн пополнение " +
                "без комиссии")) {
            System.out.println("Заголовок блока соответствует ожидаемому значению.");
        } else {
            System.out.println("Заголовок блока не соответствует ожидаемому значению.");
        }

        WebElement moreDetailsLink = driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]"));
        moreDetailsLink.click();

        //Проверка названия  блока НЕ ПОЛУЧИЛОСЬ
        /* WebElement headerElement = driver.findElement(By.xpath("//h2[@class='pay__wrapper']"));
        String headerText = headerElement.getText();
        if (headerText.equals("Онлайн пополнение без комиссии")) {
            System.out.println("Заголовок блока соответствует ожидаемому значению.");
        } else {
            System.out.println("Заголовок блока не соответствует ожидаемому значению.");
        }*/

        driver.quit();
    }
}





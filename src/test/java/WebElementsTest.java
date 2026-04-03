import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WebElementsTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddRemoveElements() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        Thread.sleep(2000);

        WebElement addElementButton = driver.findElement(By.xpath("//button[text()='Add Element']"));

        addElementButton.click();
        Thread.sleep(1000);

        addElementButton.click();
        Thread.sleep(1000);

        addElementButton.click();
        Thread.sleep(1000);

        addElementButton.click();
        Thread.sleep(1000);

        WebElement lastDeleteButtonWithFindElement = driver.findElement(By.xpath("(//button[text()='Delete'])[last()]"));
        System.out.println("Last Delete button with findElement(): " + lastDeleteButtonWithFindElement.getText());
        Thread.sleep(2000);

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[class^='added']"));
        WebElement lastDeleteButtonWithFindElements = deleteButtons.get(deleteButtons.size() - 1);
        System.out.println("Last Delete button with findElements(): " + lastDeleteButtonWithFindElements.getText());
        Thread.sleep(2000);
    }

    @Test
    public void testChallengingDom() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
        Thread.sleep(2000);

        WebElement loremElement = driver.findElement(
                By.xpath("//td[text()='Apeirian9']/preceding-sibling::td[1]")
        );
        System.out.println("Lorem value: " + loremElement.getText());
        Thread.sleep(2000);

        WebElement nextElement = driver.findElement(
                By.xpath("//td[text()='Apeirian9']/following-sibling::td[1]")
        );
        System.out.println("Next element after Apeirian9: " + nextElement.getText());
        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if (driver != null) {
            driver.quit();
        }
    }
}
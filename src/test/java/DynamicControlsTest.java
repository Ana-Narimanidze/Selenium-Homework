import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicControlsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testEnableDisableInputField() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement inputField = driver.findElement(By.cssSelector("#input-example input"));
        assertFalse(inputField.isEnabled(), "Input field initially should be disabled");

        WebElement enableButton = driver.findElement(By.cssSelector("#input-example button"));
        assertEquals("Enable", enableButton.getText(), "Initial button text should be Enable");

        enableButton.click();

        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        assertEquals("It's enabled!", message.getText(), "Success message is incorrect");

        wait.until(driver -> driver.findElement(By.cssSelector("#input-example input")).isEnabled());

        inputField = driver.findElement(By.cssSelector("#input-example input"));
        assertTrue(inputField.isEnabled(), "Input field should be enabled");

        WebElement disableButton = driver.findElement(By.cssSelector("#input-example button"));
        assertEquals("Disable", disableButton.getText(), "Button text should change to Disable");

        inputField.sendKeys("SkillWill");
        assertEquals("SkillWill", inputField.getAttribute("value"), "Input value should be SkillWill");

        inputField.clear();
        assertEquals("", inputField.getAttribute("value"), "Input field should be empty after clear");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
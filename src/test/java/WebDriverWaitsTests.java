import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebDriverWaitsTests {


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();

        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().window().maximize();

    }


    @Test
    public void testWaits01() {

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement buttonRemove = driver.findElement(By.cssSelector("#checkbox-example button"));
        buttonRemove.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("checkbox"), 1));

        List<WebElement> checkboxes = driver.findElements(By.id("checkbox"));
        Assertions.assertEquals(0, checkboxes.size());

        buttonRemove.click();

        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
        // wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("checkbox"), 0));
        wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")), ExpectedConditions.numberOfElementsToBeMoreThan(By.id("checkbox"), 1)));

        WebElement checkBox = driver.findElement(By.id("checkbox"));


        Assertions.assertTrue(checkBox.isDisplayed());

    }

    @Test
    public void testWaits02() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement buttonRemove = driver.findElement(By.cssSelector("#checkbox-example button"));
        buttonRemove.click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver).pollingEvery(Duration.ofMillis(100)).withTimeout(Duration.ofSeconds(10));

        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("checkbox"), 1));

        List<WebElement> checkboxes = driver.findElements(By.id("checkbox"));
        Assertions.assertEquals(0, checkboxes.size());

        buttonRemove.click();

        List<Integer> intList = new ArrayList<>();

        // intList.add(0);

        wait.until(
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                List<WebElement> checkboxes =
                        driver.findElements(By.id("checkbox"));

                final int maxListSize = 50;

                if (intList.size() < maxListSize) intList.add(intList.size());

                System.out.println(intList);

                return (checkboxes.size() == 1) && (intList.size() >= maxListSize);
            }
        }
        );

        WebElement checkBox = driver.findElement(By.id("checkbox"));


        Assertions.assertTrue(checkBox.isDisplayed());

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}

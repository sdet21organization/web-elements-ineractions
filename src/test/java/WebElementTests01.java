import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebElementTests01 {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        driver.manage().window().maximize();

    }

    @AfterEach
    public void tearDown() {

        driver.quit();

    }

    @Test
    public void test00() throws InterruptedException {

        sleep(3000);

        driver.get("https://demoqa.com/automation-practice-form");

        sleep(3000);

        WebElement radioButton = driver.findElement(By.cssSelector("div.custom-radio label"));
        radioButton.click();

        sleep(3000);

       List<WebElement> labels = driver.findElements(By.cssSelector("div.custom-radio label"));

        labels.get(1).click();

        sleep(5000);

    }


    @Test
    public void test01() throws InterruptedException {

        sleep(3000);

        driver.get("https://demoqa.com/automation-practice-form");

        sleep(3000);

        WebElement form = driver.findElement(By.cssSelector("#userForm"));


        WebElement radioButton = form.findElement(By.cssSelector("label"));
        radioButton.click();

        sleep(3000);

        List<WebElement> labels = form.findElements(By.cssSelector("label"));

        labels.get(1).click();

        sleep(5000);

        List<WebElement> abracadabra = form.findElements(By.cssSelector("h1"));

        System.out.println("Размер списка: " + abracadabra.size());

        int i = 1;

        sleep(5000);

    }


    @Test
    public void testAccessible() throws InterruptedException {

        driver.get("https://demoqa.com/automation-practice-form");

        WebElement firstName = driver.findElement(By.cssSelector("#firstName"));

        System.out.println("Accessible name: " + firstName.getAccessibleName());

    }


    @Test
    public void testRectangle() throws InterruptedException {

        driver.get("https://demoqa.com/automation-practice-form");

        WebElement input = driver.findElement(By.cssSelector("#gender-radio-1"));

        WebElement label = driver.findElement(By.cssSelector("[for='gender-radio-1']"));

        System.out.println("Input rectangle: " + input.getRect());

        System.out.println("Label rectangle: " + label.getRect());


    }


}

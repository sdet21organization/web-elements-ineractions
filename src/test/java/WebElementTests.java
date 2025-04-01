import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebElementTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        // Получаем последнюю версию веб-драйвера для Chrome
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        //Закрываем браузер
        driver.quit();
    }

    @Test
    public void testClick() throws InterruptedException {
        driver.get("https://selectorshub.com/xpath-practice-page/");

        WebElement editIcon = driver.findElement(By.cssSelector("svg[iconid='editon']"));
        editIcon.click();

        WebElement firstNameInput = driver.findElement(By.cssSelector("input[placeholder='First Enter name']"));

        sleep(3000);

        assertTrue(firstNameInput.isEnabled(), "The element is not enabled.");
    }

    @Test
    public void testSendKeysClear() throws InterruptedException {
        driver.get("https://www.w3.org/WAI/UA/TS/html401/cp0101/0101-TEXTAREA.html");

        WebElement input = driver.findElement(By.cssSelector("#textarea1"));

        String text = "My text";

        sleep(3000);

        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);
        input.sendKeys(Keys.ENTER);
        input.sendKeys("Some other text");

        sleep(3000);
    }

    @Test
    public void testSendKeysError() {
        driver.get("https://selectorshub.com/xpath-practice-page/");

        WebElement editIcon = driver.findElement(By.cssSelector("svg[iconid='editon']"));

        WebElement firstNameInput = driver.findElement(By.cssSelector("input[placeholder='First Enter name']"));

        String text = "My name";

        if (firstNameInput.isEnabled()) {
            firstNameInput.sendKeys(text);
        } else {
            editIcon.click();
            firstNameInput.sendKeys(text);
        }
    }

    @Test
    public void testSelect() throws InterruptedException {
        driver.get("https://selectorshub.com/xpath-practice-page/");

        WebElement systemDistributionDetails = driver.findElement(By.cssSelector("#tablepress-1-name"));
        new Actions(driver).scrollToElement(systemDistributionDetails).perform();

        sleep(3000);

        WebElement selectElement = driver.findElement(By.cssSelector("#dt-length-0"));
        Select select = new Select(selectElement);

        select.selectByIndex(1);

        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            System.out.println(option.getText());
        }

        assertEquals("25", select.getFirstSelectedOption().getText());
    }

    @Test
    public void testSelectInvalid() throws InterruptedException {
        driver.get("https://demo.mobiscroll.com/javascript/select/single-select");

        WebElement denyCookie = driver.findElement(By.cssSelector("#CybotCookiebotDialogBodyButtonDecline"));
        denyCookie.click();

        WebElement selectInputElement = driver.findElement(By.cssSelector("#single-select-input"));
        WebElement selectElement = driver.findElement(By.cssSelector("#single-select-select"));

        Select select = new Select(selectElement);

        selectInputElement.click();
        sleep(2000);
        select.selectByIndex(1);

        sleep(3000);
    }

    @Test
    public void testMultiSelect() throws InterruptedException {
        driver.get("https://letcode.in/dropdowns");

        WebElement superheros = driver.findElement(By.cssSelector("#superheros"));

        Select select = new Select(superheros);

        assertTrue(select.isMultiple(), "select is not multiple");

        select.selectByValue("aq");
        select.selectByValue("bw");
        select.selectByValue("ff");

        List<String> selectedList = new ArrayList<>();

        for (WebElement element : select.getAllSelectedOptions()) {
            System.out.println(element.getText());
            selectedList.add(element.getText());
        }

        assertTrue(selectedList.contains("Aquaman"));
        assertTrue(selectedList.contains("Batwoman"));
        assertTrue(selectedList.contains("Fantastic Four"));
    }

    @Test
    public void testGetAttributeValue() {

        driver.get("https://selectorshub.com/xpath-practice-page/");

        WebElement email = driver.findElement(By.xpath("//*[@title='Email']"));

        String text = "My email";

        email.sendKeys(text);

        assertEquals(text, email.getAttribute("value"));
    }

    @Test
    public void testGetCssValue() {

        driver.get("https://selectorshub.com/xpath-practice-page/");

        WebElement button = driver.findElement(By.xpath("//button[.='Checkout here']"));

        assertEquals("rgba(76, 175, 80, 1)", button.getCssValue("background-color"));

    }

    @Test
    public void testGetTagName() {
        driver.get("https://selectorshub.com/xpath-practice-page/");

        WebElement submitButton = driver.findElement(By.xpath("//*[@value = 'Submit']"));

        assertEquals("input", submitButton.getTagName());
    }
}

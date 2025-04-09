import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class WebElementsTablesTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

        driver.manage().window().maximize();
    }


    void fillingForm01() {
        driver.get("https://demoqa.com/automation-practice-form");


        // Убираем рекламу.
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("$('footer').remove();");
        executor.executeScript("$('#fixedban').remove();");
        executor.executeScript("$('iframe').remove();");

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Kumar");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Krishna");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("abc@abc.com");

        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("1234567890");

        WebElement gender = driver.findElement(By.xpath("//label[text()='Male']"));
        gender.click();


        WebElement buttonSubmit = driver.findElement(By.id("submit"));
        executor.executeScript("arguments[0].scrollIntoView(true);", buttonSubmit);
        buttonSubmit.click();
    }

    @Test
    //@RepeatedTest(3)
    public void demoQA01() {

        fillingForm01();

        WebElement resultsTable = driver.findElement(By.cssSelector(".modal-body table"));
        assertTrue(resultsTable.isDisplayed());

        WebElement tableCellStudentName = driver.findElement(By.xpath("//table/tbody/tr/td[2]"));

        assertEquals("Kumar Krishna", tableCellStudentName.getText());

        WebElement tableCellStudentEmail = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]"));

        assertEquals("abc@abc.com", tableCellStudentEmail.getText());

        WebElement tableCellStudentMobile = driver.findElement(By.xpath("//table//td[contains(text(),'Mobile')]/../td[2]"));

        assertEquals("1234567890", tableCellStudentMobile.getText());

        checkTableCell("Gender", "Male");
        checkTableCell("Date of Birth", "09 April,2025");


    }


    @Test
    public void demoQA02() {

        fillingForm01();

        WebElement resultsTable = driver.findElement(By.cssSelector(".modal-body table"));
        assertTrue(resultsTable.isDisplayed());

        checkTableCell("Student Name", "Kumar Krishna");
        checkTableCell("Student Email", "abc@abc.com");
        checkTableCell("Gender", "Male");
        checkTableCell("Date of Birth", "09 April,2025");

    }


    void checkTableCell(String cellName, String cellValue) {

        String locator = String.format("//table//td[contains(text(),'%s')]/../td[2]", cellName);

        WebElement tableCell = driver.findElement(By.xpath(locator));

        assertEquals(cellValue, tableCell.getText());

    }

    @AfterEach
    void tearDown() throws InterruptedException {
        sleep(Duration.ofSeconds(7));
        driver.close();
    }
}

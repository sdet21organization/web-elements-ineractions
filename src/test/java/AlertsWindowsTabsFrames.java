import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Set;

public class AlertsWindowsTabsFrames {


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().window().maximize();

    }


    @Test
    public void testAlert01() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement firstButton = driver.findElement(By.tagName("button"));
        firstButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        System.out.println("Alert text is: " + text);

        alert.accept();

    }

    @Test
    public void testAlert02() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement secondButton = driver.findElements(By.tagName("button")).get(1);
        secondButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        System.out.println("Alert text is: " + text);

        alert.dismiss();

    }


    @Test
    public void testCookie() {
        driver.get("https://rozetka.com.ua/favicon.ico");

        driver.manage().addCookie(new Cookie("slang", "ru"));

        driver.get("https://rozetka.com.ua/");

        WebElement langButton = driver.findElement(By.cssSelector("[data-testid='lang_btn']"));

        String buttonText = langButton.getText();


    }


    @Test
    public void testIframe() {
        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement iframe = driver.findElement(By.cssSelector("#mce_0_ifr"));
        driver.switchTo().frame(iframe);

        WebElement editorParagraph = driver.findElement(By.cssSelector("body#tinymce p"));

        String paragraphText = editorParagraph.getText();

        System.out.println("Paragraph text: " + paragraphText);

    }



    @Test
    public void testWindows() {

        // Загрузил вкладку с алертами.

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        String myFistTab = driver.getWindowHandle(); // Запомнил её handle

        // Открываю новую вкладку и сразу перехожу на неё

        driver.switchTo().newWindow(WindowType.TAB);

        // В новой вкладке работаю с ифреймом

        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement iframe = driver.findElement(By.cssSelector("#mce_0_ifr"));
        driver.switchTo().frame(iframe);

        WebElement editorParagraph = driver.findElement(By.cssSelector("body#tinymce p"));

        String paragraphText = editorParagraph.getText();

        System.out.println("Paragraph text: " + paragraphText);

        // Переключиться обратно на первую вкладку.

        Set<String> windowHandles = driver.getWindowHandles();

        driver.switchTo().window(myFistTab);

        // Поработать с алертами.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement firstButton = driver.findElement(By.tagName("button"));
        firstButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        System.out.println("Alert text is: " + text);


    }



    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}

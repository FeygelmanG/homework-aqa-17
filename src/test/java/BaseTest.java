import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    static ChromeDriver driver;

    @BeforeAll
    public static void setBeforeAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    @BeforeEach
    public void openBeforeEach() {
        driver = new ChromeDriver();
        driver.get("https://www.tkani-feya.ru/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void closeAfterEach() {
        driver.close();
    }


}

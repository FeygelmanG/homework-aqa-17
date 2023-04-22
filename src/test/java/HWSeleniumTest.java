import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Проверка поиска по сайту www.tkani-feya.ru")
public class HWSeleniumTest extends BaseTest {
    @Test
    @DisplayName("Позитивный кейс проверки поиска")
    @Description("Позитивный кейс проверки поиска по полному названию ткани Шелк блузочный")
    public void searchTextile() {
        String itemName = "Шелк блузочный";

        step("Ввод данных в строку поиска", () -> {
            driver.findElement(By.name("find")).sendKeys(itemName + Keys.ENTER); //name="find"
        });

        step("Основная проверка страницы", () -> {
            assertEquals("https://www.tkani-feya.ru/fabrics/?find=%D0%A8%D0%B5%D0%BB%D0%BA+%D0%B1%D0%BB%D1%83%D0%B7%D0%BE%D1%87%D0%BD%D1%8B%D0%B9",
                    driver.getCurrentUrl());
            assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
        });

        step("Проверка, что каждый из элементов включает в названии слово " + itemName, () -> {
            for (WebElement el : driver.findElements(By.cssSelector(".text-block .name"))) {
                assertTrue(el.getText().contains(itemName));
            }
        });

    }

    @Test
    @DisplayName("Негативный кейс проверки поиска")
    @Description("Негативный кейс проверки поиска по пробелу")
    public void searchTextileWithSpase() {
        String itemName = " ";

        step("Ввод данных в строку поиска",
                () -> driver.findElement(By.name("find")).sendKeys(itemName + Keys.ENTER));

        step("Основная проверка страницы", () -> assertTrue(driver.findElement(By.tagName("h1")).isDisplayed()));

    }

    @Test
    @DisplayName("Негативный кейс проверки поиска")
    @Description("Негативный кейс проверки поиска по слову слово")
    public void notFoundTextile() {
        String itemName = "слово";

        step("Ввод данных в строку поиска",
                () -> driver.findElement(By.name("find")).sendKeys(itemName + Keys.ENTER));

        step("Основная проверка страницы", () -> assertTrue(driver.findElement(By.tagName("h1")).isDisplayed()));

        step("Проверка заглушки", () -> {
            WebElement element = driver.findElement(By.cssSelector(".alert-warning"));
            assertTrue(element.isDisplayed());
            assertEquals("По вашему запросу ничего не найдено", element.getText());
        });


    }
}

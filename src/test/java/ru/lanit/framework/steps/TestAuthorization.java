package ru.lanit.framework.steps;

import net.bytebuddy.matcher.ElementMatcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.lanit.framework.webdriver.WebDriverManager;

import static net.bytebuddy.matcher.ElementMatchers.is;

public class TestAuthorization {

    WebDriver driver = WebDriverManager.getDriver();

    @Test(priority=1)
    public void testEmptyAuth() throws InterruptedException {
        //Переход на тестовую страницу
        driver.get("https://dev.n7lanit.ru/");

        //Проверка по заголовку что мы на нужной странице
        driver.findElement(By.tagName("h1")).isDisplayed();
        assertThat(driver.findElement(By.tagName("h1")).getText(), is("Lanit education"));

        //Открываем форму авторизации
        driver.findElement(By.cssSelector(".btn-sign-in")).isDisplayed();
        driver.findElement(By.cssSelector(".btn-sign-in")).click();

        //Авторизация с пустыми полями
        driver.findElement(By.cssSelector(".modal-content")).isDisplayed();
        driver.findElement(By.cssSelector("#id_username")).sendKeys("");
        driver.findElement(By.cssSelector("#id_password")).sendKeys("");
        driver.findElement(By.cssSelector("div.modal-footer > button")).isDisplayed();
        driver.findElement(By.cssSelector("div.modal-footer > button")).click();

        //Проверка вывода уведомления
        driver.findElement(By.cssSelector("div.alerts-snackbar > p")).isDisplayed();
        assertThat(driver.findElement(By.cssSelector("div.alerts-snackbar > p")).getText(), is("Заполните оба поля."));
    }

    @Test(priority=2)
    public void testFalseAuth() {
        //Авторизация под не существующим пользователем
        driver.findElement(By.cssSelector(".modal-content")).isDisplayed();
        driver.findElement(By.cssSelector("#id_username")).sendKeys("RuslanaNet");
        driver.findElement(By.cssSelector("#id_password")).sendKeys("PassNet");
        driver.findElement(By.cssSelector("div.modal-footer > button")).isDisplayed();
        driver.findElement(By.cssSelector("div.modal-footer > button")).click();

        //Проверка вывода уведомления
        driver.findElement(By.cssSelector("div.alerts-snackbar > p")).isDisplayed();
        assertThat(driver.findElement(By.cssSelector("div.alerts-snackbar > p")).getText(), is("Заполните оба поля."));
    }

    @Test(priority=3)
    public void testTrueAuth() {
        //Закрытие формы авторизации (.clear()-Не очищает поля, решил переоткрыть форму)
        driver.findElement(By.cssSelector("button.close")).click();
        driver.findElement(By.cssSelector(".btn-sign-in")).click();

        //Авторизация под существующим пользователем
        driver.findElement(By.cssSelector(".modal-content")).isDisplayed();
        driver.findElement(By.cssSelector("#id_username")).sendKeys("Ruslan");
        driver.findElement(By.cssSelector("#id_password")).sendKeys("RStest1");
        driver.findElement(By.cssSelector("div.modal-footer > button")).isDisplayed();
        driver.findElement(By.cssSelector("div.modal-footer > button")).click();

        //Проверка авторизации
        assertThat(driver.findElement(By.cssSelector("li.dropdown-header > strong")).getText(), is("Ruslan"));
    }

    private void assertThat(String h1, ElementMatcher.Junction<Object> hello_userName) {
    }

}

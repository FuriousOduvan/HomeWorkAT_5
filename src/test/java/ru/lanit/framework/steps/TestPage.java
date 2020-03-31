package ru.lanit.framework.steps;
import net.bytebuddy.matcher.ElementMatcher;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import ru.lanit.framework.webdriver.WebDriverManager;

import static net.bytebuddy.matcher.ElementMatchers.is;


public class TestPage {
    WebDriver driver = WebDriverManager.getDriver();

    @Test(priority=1)
    public void testMainPage() {

        //Переход на тестовую страницу
        driver.get("https://dev.n7lanit.ru/");

        //Проверка по заголовку что мы на нужной странице
        driver.findElement(By.tagName("h1")).isDisplayed();
        assertThat(driver.findElement(By.tagName("h1")).getText(), is("Lanit education"));
    }

    @Test(priority=2)
    public void testCategories() {
        //Переход на страницу "Категории" и проверка
        driver.findElement(By.linkText("Категории")).click();
        driver.findElement(By.xpath("//*[@id='categories-mount']")).isDisplayed();
        assertThat(driver.findElement(By.tagName("h1")).getText(), is("Категории"));
    }

    @Test(priority=3)
    public void testUsers() {
        //Переход на страницу "Пользователи" и проверка
        driver.findElement(By.linkText("Пользователи")).click();
        driver.findElement(By.xpath("//*[@id='page-mount']")).isDisplayed();
        assertThat(driver.findElement(By.tagName("h1")).getText(), is("Пользователи"));
    }

    @Test(priority=4)
    public void testSearch(){
        //Открытие поиска и проверка
        driver.findElement(By.xpath("//*[@id='user-menu-mount']/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id='user-menu-mount']//input")).isDisplayed();
        driver.findElement(By.xpath("//*[@id='user-menu-mount']//input")).sendKeys("Ruslan");
        driver.findElement(By.xpath("//*[@id='user-menu-mount']//li[3]//div[2]/h5")).isDisplayed();
        assertThat(driver.findElement(By.tagName("h5")).getText(), is("Ruslan"));

        driver.quit();
    }

    private void assertThat(String h1, ElementMatcher.Junction<Object> hello_userName) {
    }

}

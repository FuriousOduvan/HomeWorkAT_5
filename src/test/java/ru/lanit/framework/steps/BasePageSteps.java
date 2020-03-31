/**
 * Класс для описания основных методов работы с элементами
 * Автор Васильев И.Н.
 * 02.12.2018
 */
package ru.lanit.framework.steps;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.lanit.framework.webdriver.WebDriverManager;

public  abstract class BasePageSteps {
    protected final Wait<WebDriver> wait = new WebDriverWait(WebDriverManager.getDriver(), 100, 1000);
}

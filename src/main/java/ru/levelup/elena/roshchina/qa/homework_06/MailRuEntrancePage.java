package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuEntrancePage extends AbstractMailRuLocators{

    private WebDriver driver;

    public MailRuEntrancePage(WebDriver driver) {
        this.driver = driver;
    }
    // открыть главную страницу
    public void open(){
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    // вход
    public void login(String username, String password){
        WebElement username_input =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(loginInputFieldLocator));
        username_input.sendKeys(username);

        WebElement pswd_input = driver.findElement(openPswdInputFieldLocator);
        pswd_input.click();

        WebElement password_input = new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(passwordInputFieldLocator));
        password_input.sendKeys(password);

        WebElement submit_button = driver.findElement(submitButtonLocator);
        submit_button.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(composeButtonLocator)); // ожидание загрузки пункта меню
    }
}

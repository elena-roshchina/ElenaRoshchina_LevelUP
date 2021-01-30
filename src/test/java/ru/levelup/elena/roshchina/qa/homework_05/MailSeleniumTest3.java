package ru.levelup.elena.roshchina.qa.homework_05;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.levelup.elena.roshchina.qa.utils.GenPassword.getPassword;

/*
Exercise 3
1.	Войти в почту
2.	Assert, что вход выполнен успешно
3.	Создать новое письмо (заполнить адресата (самого себя), тему письма и тело)
4.	Отправить письмо
5.	Verify, что письмо появилось в папке Входящие
6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
7.	Удалить письмо
8.	Verify что письмо появилось в папке Корзина
9.	Выйти из учётной записи

*/


public class MailSeleniumTest3 extends AbstractBaseSeleniumTest {

    @Test
    public void mailSeleniumTest(){
        TestUser user = new TestUser();

        String subj = "Exercise 3";
        String body = "Exercise 3 body text";
        String email = user.getEmail();

        boolean draftSaved = false;
        boolean inputFolderItemFound = false;
        boolean trashFolderItemFound = false;

        driver.manage().window().maximize();
        driver.navigate().to(user.getUrl());
        //1.	Войти в почту
        WebElement username_input =  new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.name("login")));
        username_input.sendKeys(user.getBox());

        WebElement pswd_input = driver.findElement(By.xpath("//button[contains(text(),'пароль')]"));
        pswd_input.click();

        WebElement password_input = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.name("password")));
        password_input.sendKeys(getPassword(user.getKey()));

        WebElement enter_button = driver.findElement(By.xpath("//button[contains(text(),'Войти')]"));
        enter_button.click();

        WebElement compose_button = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.className("compose-button__txt")));

        //2.	Assert, что вход выполнен успешно
        assertTrue(driver.getTitle().contains(user.getAccountTitleFragment()), "Вход в аккаунт " + driver.getTitle());

        //3.	Создать новое письмо (заполнить адресата (самого себя), тему письма и тело)
        compose_button.click();

        WebElement editLetterContainer = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.className("head_container--3W05z")));

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        for (int i = 0; i< inputs.size(); i++) {
            if (inputs.get(i).getAttribute("class").contains("container--H9L5q") && !inputs.get(i).getAttribute("name").contains("Subject")) {
                inputs.get(i).sendKeys(email);
            } else if (inputs.get(i).getAttribute("class").contains("container--H9L5q") && inputs.get(i).getAttribute("name").contains("Subject")) {
                inputs.get(i).sendKeys(subj);
            }
        }
        WebElement textbox = driver.findElement(By.xpath("//div[@role='textbox']//div"));
        textbox.sendKeys(body);

        //4.	Отправить письмо
        WebElement sendLetter = driver.findElement(By.xpath("//span[@title='Отправить']"));
        sendLetter.click();

        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.layer-window__block span[title='Закрыть'] > span"))).click();
        } catch (StaleElementReferenceException e) {
            new WebDriverWait(driver, 10)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.layer-window__block span[title='Закрыть'] > span"))).click();
        }
        WebElement inputFolder = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Входящие')]//div[@class='nav__folder-name__txt']")));
        inputFolder.click();
        // Проверка видимости содержимого папки
        WebElement item = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//a[contains(@class, 'letter-list-item')]//div[@class='llc__item llc__item_title']")));

        List<WebElement> inputFolderItems = driver.findElements(By.xpath(
                "//a[contains(@class, 'letter-list-item')]//span[@class='ll-sj__normal']"));

        //5.	Verify, что письмо появилось в папке Входящие
        for (int i = 0; i < inputFolderItems.size(); i++){
            if(inputFolderItems.get(i).getText().contains(subj)){
                inputFolderItemFound = true;
                inputFolderItems.get(i).click();
                break;
            }
        }
        assertTrue( inputFolderItemFound, "Search letter in the Input folder");

        //6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        WebElement recepientAddress = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.className("letter-contact")));
        assertEquals(recepientAddress.getAttribute("title"), email, "Check address " + recepientAddress.getAttribute("title"));

        WebElement receivedSubj = driver.findElement(By.tagName("h2"));
        assertEquals(receivedSubj.getText(), subj, "Subject check " + receivedSubj.getText());

        WebElement receivedBodyDiv = driver.findElement(By.xpath("//div[contains(@id, '_BODY')]/div[contains(@class, 'cl_')]/div"));
        assertEquals(receivedBodyDiv.getText(), body, "Check body " + receivedBodyDiv.getText());

        //7.	Удалить письмо
        WebElement deleteEmailButton = driver.findElement(By.xpath("//span[@title='Удалить']/span[@class='button2__wrapper']"));
        deleteEmailButton.click();

        //8.	Verify что письмо появилось в папке Корзина
        WebElement trashFolder = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Корзина')]//div[@class='nav__folder-name__txt']")));
        trashFolder.click();

        WebElement trashFolderItem = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'letter-list-item')]//span[@class='ll-sj__normal']")));

        List<WebElement> trashFolderItems = driver.findElements(By.xpath(
                "//a[contains(@class, 'letter-list-item')]//span[@class='ll-sj__normal']"));
        for (int i = 0; i < trashFolderItems.size(); i++){
            System.out.println(trashFolderItems.get(i).getText());
            if(trashFolderItems.get(i).getText().contains(subj)){
                trashFolderItemFound = true;
                trashFolderItems.get(i).click();
                break;
            }
        }
        assertTrue( trashFolderItemFound, "Search letter in the Trash folder");

        //9.	Выйти из учётной записи
        WebElement exitLink =new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.id("PH_logoutLink")));
        exitLink.click();
    }
}

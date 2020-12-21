package ru.levelup.elena.roshchina.qa.homework_05;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
//1.	Войти в почту
//2.	Assert, что вход выполнен успешно
//3.	Создать новое письмо (заполнить адресата, тему письма и тело)
//4.	Сохранить его как черновик
//5.	Verify, что письмо сохранено в черновиках
//6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
//7.	Отправить письмо
//8.	Verify, что письмо исчезло из черновиков
//9.	Verify, что письмо появилось в папке отправленные
//10.	 Выйти из учётной записи
*/


public class MailSeleniumTest1 extends AbstractBaseSeleniumTest {

    @Test
    @Parameters({"url", "login", "pswd", "accountTitleFragment"})
    public void mailSeleniumTest(String url, String login, String pswd, String accountTitleFragment){
        String subj = "my test subject";
        String body = "Lorem ipsum";
        String email = login + "@mail.ru";

        boolean draftSaved = false;
        boolean sentletterFound = false;

        driver.manage().window().maximize();
        driver.navigate().to(url);
        //1.	Войти в почту
        WebElement username_input =  new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.name("login")));
        username_input.sendKeys(login);

        WebElement pswd_input = driver.findElement(By.xpath("//button[contains(text(),'пароль')]"));
        pswd_input.click();

        WebElement password_input = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.name("password")));
        password_input.sendKeys(pswd);

        WebElement enter_button = driver.findElement(By.xpath("//button[contains(text(),'Войти')]"));
        enter_button.click();

        WebElement compose_button = new WebDriverWait(driver, 300)
                .until(ExpectedConditions.elementToBeClickable(By.className("compose-button__txt")));

        //2.	Assert, что вход выполнен успешно
        assertTrue(driver.getTitle().contains(accountTitleFragment), "Вход в аккаунт " + driver.getTitle());

        //3.	Создать новое письмо (заполнить адресата, тему письма и тело)
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

        //4.	Сохранить его как черновик
        WebElement saveDraft = driver.findElement(By.xpath("//span[@title='Сохранить']"));
        saveDraft.click();

        WebElement closeEditLetter = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Закрыть']")));
        closeEditLetter.click();

        WebElement draftLetterFolder =  new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Черновики")));
        draftLetterFolder.click();

        WebElement draftItem =  new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'letter-list-item')]")));

        String savedDraftTime= null; // для доп. идентификации черновика

        List<WebElement> draftLetters = driver.findElements(By.xpath("//a[contains(@class,'letter-list-item')]"));
        for (int i = 0; i < draftLetters.size(); i++){
            WebElement draftItemContentContainer =  draftLetters.get(i)
                    .findElement(By.xpath("./div[@class='llc__container']/div[contains(@class,'pony-mode')]"));

            WebElement subjectItem =  draftItemContentContainer.findElement(By.xpath("./div[contains(@class,'item_title')]/span[contains(@class,'subject')]/span"));
            if (subjectItem.getText().equals(subj)){
                savedDraftTime = draftItemContentContainer.findElement(By.xpath("./div[contains(@class,'item_title')]")).getAttribute("title");
                draftSaved = true;
                subjectItem.click();
                break;
            }
        }

        //5.	Verify, что письмо сохранено в черновиках
        assertTrue(draftSaved, "Checking Draft saved");

        //6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        WebElement savedBody = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='textbox']//div")));
        assertTrue(savedBody.getText().contains(body), "Body check " + savedBody.getText());

        WebElement savedSubj = driver.findElement(By.xpath("//input[@name='Subject']"));
        assertEquals(savedSubj.getAttribute("value"), subj, "Check subject " + savedSubj.getAttribute("value"));

        WebElement savedAddress = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='text--1tHKB']")));
        assertEquals(savedAddress.getText(), email, "Check address " + savedAddress.getText());

        //7.	Отправить письмо
        WebElement sendDraft = driver.findElement(By.xpath("//span[@title='Отправить']"));
        sendDraft.click();

        // После отправки висит слой, закрывающий содержмое, необходимо некоторое ожидание, пока не появится меню и все остальное
        draftLetterFolder =  new WebDriverWait(driver, 300)
                .until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Черновики")));

        //8.	Verify, что письмо исчезло из черновиков
        List<WebElement> draftLettersAfterSending = driver.findElements(By.xpath("./div[@title='"+ savedDraftTime +"']"));
        assertEquals(draftLettersAfterSending.size(), 0, "Checking that no letters with time=" + savedDraftTime);

        //9.	Verify, что письмо появилось в папке отправленные
        // Переход в папке Отправленные затруднен тем, что клик по нему принимает слой div class='layer-window__container'
        // необходимо перезагрузить страницу
        driver.navigate().refresh();

        WebElement sentLettersFolder = new WebDriverWait(driver, 300)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Отправленные')]//div[@class='nav__folder-name__txt']")));
        sentLettersFolder.click();

        List<WebElement> sentLetters = driver.findElements(By.xpath("//span[@class='llc__subject']//span"));
        System.out.println(sentLetters.size());
        for (int i = 0; i < sentLetters.size(); i++){
            System.out.println(sentLetters.get(i).getText());
            if(sentLetters.get(i).getText().contains(subj)){
                sentletterFound = true;
                break;
            }
        }
        assertTrue(sentletterFound, "Check output folder");

        //10.	 Выйти из учётной записи
        WebElement exitLink =new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.id("PH_logoutLink")));
        exitLink.click();

    }
}

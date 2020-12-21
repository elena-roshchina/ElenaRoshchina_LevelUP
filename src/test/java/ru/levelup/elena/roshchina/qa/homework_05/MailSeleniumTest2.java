package ru.levelup.elena.roshchina.qa.homework_05;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.levelup.elena.roshchina.qa.utils.Sleep;

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


public class MailSeleniumTest2 extends AbstractBaseSeleniumTest {

    @Test
    @Parameters({"url", "login", "pswd", "accountTitleFragment"})
    public void mailSeleniumTest2(String url, String login, String pswd, String accountTitleFragment){

        String subj = "Тест";
        String body = "The letter for folder Test";
        String email = login + "@mail.ru";

        boolean testFolderItemFound = false;
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

        //3.	Создать новое письмо (заполнить адресата (самого себя), тему письма (должно содержать слово Тест) и тело)
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
        //org.openqa.selenium.UnhandledAlertException: unexpected alert open: {Alert text : }
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (NoAlertPresentException e){
            System.out.println("NoAlertPresentException");
        }

        WebElement output = new WebDriverWait(driver, 30000)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Отправленные')]//div[@class='nav__folder-name__txt']")));
        output.click();

        /*
        List<WebElement> navElements = driver.findElements(By.xpath("//a[contains(@class, 'nav__item')]"));
        System.out.println(navElements.size());
        WebElement output = new WebDriverWait(driver, 30000)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Отправленные')]//div[@class='nav__folder-name__txt']")));

        output.click();
        WebElement sentItem = new WebDriverWait(driver, 5000)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='llc__subject']//span")));
        System.out.println(sentItem.getTagName());

        //5.	Verify, что письмо появилось в папке отправленные

        WebElement sentItem = new WebDriverWait(driver, 300)
                 .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='llc__subject']//span")));
        System.out.println(sentItem.getTagName());

        //Sleep.sleep(300q0);
        //List<WebElement> sentLetters = driver.findElements(By.xpath("//span[@class='llc__subject']//span"));
        //System.out.println(sentLetters.size()); */
        /*
        for (int i = 0; i < sentLetters.size(); i++){
            System.out.println(sentLetters.get(i).getText());
            if(sentLetters.get(i).getText().contains(subj)){
                sentletterFound = true;
                break;
            }
        }
        assertTrue(sentletterFound, "Check output folder");

        //6.	Verify, что письмо появилось в папке «Тест»
        WebElement testFolder = new WebDriverWait(driver, 300)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//a[contains(@title, 'Тест')]//div[@class='nav__folder-name__txt']")));
        testFolder.click();
        // Проверка видимости содержимого папки
        WebElement item = new WebDriverWait(driver, 300)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//a[contains(@class, 'letter-list-item')]//div[@class='llc__item llc__item_title']")));

        List<WebElement> testFolderItems = driver.findElements(By.xpath(
                "//a[contains(@class, 'letter-list-item')]//span[@class='ll-sj__normal']"));
        System.out.println(testFolderItems.size());
        for (int i = 0; i < testFolderItems.size(); i++){
            System.out.println(testFolderItems.get(i).getText());
            if(testFolderItems.get(i).getText().contains(subj)){
                testFolderItemFound = true;
                testFolderItems.get(i).click();
                break;
            }
        }
        assertTrue( testFolderItemFound, "Search letter in the Тест folder");

        //7.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)


        WebElement receivedSubj = new WebDriverWait(driver, 300)
                .until(ExpectedConditions.elementToBeClickable(By.className("thread__subject thread__subject_pony-mode")));
        assertEquals(receivedSubj.getText(), subj, "Subject check " + receivedSubj.getText());

        WebElement recepientAddress = driver.findElement(By.className("letter-contact"));
        assertEquals(recepientAddress.getAttribute("title"), email, "Check address " + recepientAddress.getAttribute("title"));

        List<WebElement> receivedBodyDivs = driver.findElements(By.xpath("//div[contains(@id, 'BODY')]/div[contains(@class, 'cl_)]/div"));

        //assertEquals(savedSubj.getAttribute("value"), subj, "Check subject " + savedSubj.getAttribute("value"));


        //8.	Выйти из учётной записи
        WebElement exitLink =new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.id("PH_logoutLink")));
        exitLink.click();

    */
    }
}

package ru.levelup.elena.roshchina.qa.homework_05;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.levelup.elena.roshchina.qa.utils.GenPassword.getPassword;

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
    public void mailSeleniumTest2(){
        TestUser user = new TestUser();
        String subj = "Тест";
        String body = "The letter for folder Test";
        String email = user.getEmail();

        boolean testFolderItemFound = false;
        boolean sentletterFound = false;

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
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.layer-window__block span[title='Закрыть'] > span"))).click();
        } catch (StaleElementReferenceException e) {
            new WebDriverWait(driver, 10)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.layer-window__block span[title='Закрыть'] > span"))).click();
        }
        WebElement output = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Отправленные')]//div[@class='nav__folder-name__txt']")));
        output.click();

        //5.	Verify, что письмо появилось в папке отправленные
        WebElement sentItem = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='llc__subject']//span")));
        List<WebElement> sentLetters = driver.findElements(By.xpath("//span[@class='llc__subject']//span"));
        for (int i = 0; i < sentLetters.size(); i++){
            System.out.println(sentLetters.get(i).getText());
            if(sentLetters.get(i).getText().contains(subj)){
                sentletterFound = true;
                break;
            }
        }
        assertTrue(sentletterFound, "Check output folder");

        //6.	Verify, что письмо появилось в папке «Тест»
        WebElement testFolder = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//a[contains(@title, 'Тест')]//div[@class='nav__folder-name__txt']")));
        testFolder.click();
        // Проверка видимости содержимого папки
        WebElement item = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(
                        "//a[contains(@class, 'letter-list-item')]//div[@class='llc__item llc__item_title']")));

        List<WebElement> testFolderItems = driver.findElements(By.xpath(
                "//a[contains(@class, 'letter-list-item')]//span[@class='ll-sj__normal']"));
        for (int i = 0; i < testFolderItems.size(); i++){
            if(testFolderItems.get(i).getText().contains(subj)){
                testFolderItemFound = true;
                testFolderItems.get(i).click();
                break;
            }
        }
        assertTrue( testFolderItemFound, "Search letter in the Тест folder");

        //7.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        WebElement recepientAddress = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.className("letter-contact")));
        assertEquals(recepientAddress.getAttribute("title"), email, "Check address " + recepientAddress.getAttribute("title"));

        WebElement receivedSubj = driver.findElement(By.tagName("h2"));
        assertEquals(receivedSubj.getText(), subj, "Subject check " + receivedSubj.getText());

        WebElement receivedBodyDiv = driver.findElement(By.xpath("//div[contains(@id, '_BODY')]/div[contains(@class, 'cl_')]/div"));
        assertEquals(receivedBodyDiv.getText(), body, "Check body " + receivedBodyDiv.getText());

        //8.	Выйти из учётной записи
        WebElement exitLink =new WebDriverWait(driver, 30)
                .until(ExpectedConditions.elementToBeClickable(By.id("PH_logoutLink")));
        exitLink.click();
    }
}

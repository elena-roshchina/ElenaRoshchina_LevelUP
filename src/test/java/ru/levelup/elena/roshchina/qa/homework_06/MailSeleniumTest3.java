package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;
import static ru.levelup.elena.roshchina.qa.utils.UsefulThing.getString;

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
    @Parameters({"subj", "body"})
    public void mailSeleniumTest(String subj, String body){
        //1.	Войти в почту
        MailRuEntrancePage entermailRu = new MailRuEntrancePage(driver);
        entermailRu.open();
        entermailRu.login(user.getBox(), getString(user.getKey()));
        MailRuPage indexPage = new MailRuPage(driver);

        //2.	Assert, что вход выполнен успешно
        assertTrue(driver.getTitle().contains(user.getAccountTitleFragment()), "Вход в аккаунт " + driver.getTitle());

        //3.	Создать новое письмо (заполнить адресата (самого себя), тему письма и тело)
        //4.	Отправить письмо
        indexPage.composeLetter(user.getEmail(), subj, body, true);
        indexPage.goToInputFoder();

        //5.	Verify, что письмо появилось в папке Входящие
        HashMap<String, WebElement> receivedLetter = indexPage.findLetter(user.getEmail(), subj, body);
        assertNotNull(receivedLetter);

        //6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        assertTrue(receivedLetter
                .get("bodyFragment")
                .getText()
                .contains(body.length() > 99 ? body.substring(0,98) : body), "Body check " + receivedLetter.get("bodyFragment").getText());

        assertTrue(receivedLetter
                .get("subject")
                .getText()
                .contains(subj), "Check subject " + receivedLetter.get("subject").getText());

        assertEquals(receivedLetter
                .get("correspondent")
                .getText(), user.getUserName(), "Check user " + receivedLetter.get("correspondent").getText());

        //7.	Удалить письмо
        indexPage.deleteReceivedLetter(receivedLetter.get("subject"));

        //8.	Verify что письмо появилось в папке Корзина
        indexPage.goToTrashFoder();
        HashMap<String, WebElement> deletedItem = indexPage.findLetter(user.getEmail(), subj, body);
        assertNotNull(deletedItem);

        //9.	Выйти из учётной записи
        indexPage.logout();
    }
}

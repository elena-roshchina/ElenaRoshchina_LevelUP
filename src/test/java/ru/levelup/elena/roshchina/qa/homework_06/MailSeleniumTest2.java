package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;
import static ru.levelup.elena.roshchina.qa.utils.GenPassword.getPassword;

/*
1.	Войти в почту
2.	Assert, что вход выполнен успешно
3.	Создать новое письмо (заполнить адресата, тему письма и тело)
4.	Сохранить его как черновик
5.	Verify, что письмо сохранено в черновиках
6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
7.	Отправить письмо
8.	Verify, что письмо исчезло из черновиков
9.	Verify, что письмо появилось в папке отправленные
10.	 Выйти из учётной записи
*/


public class MailSeleniumTest2 extends AbstractBaseSeleniumTest {

    @Test
    @Parameters({"subj", "body"})
    public void mailSeleniumTest2(String subj, String body){
        //1.	Войти в почту
        MailRuEntrancePage entermailRu = new MailRuEntrancePage(driver);
        entermailRu.open();
        entermailRu.login(user.getBox(), getPassword(user.getKey()));
        MailRuPage indexPage = new MailRuPage(driver);

        //2.	Assert, что вход выполнен успешно
        assertTrue(driver.getTitle().contains(user.getAccountTitleFragment()), "Вход в аккаунт " + driver.getTitle());

        //3.	Создать новое письмо (заполнить адресата (самого себя), тему письма (должно содержать слово Тест) и тело)
        indexPage.composeLetter(user.getEmail(), subj, body, true);

        //5.	Verify, что письмо появилось в папке отправленные
        indexPage.goToOutputFoder();
        HashMap<String, WebElement> sentLetter = indexPage.findLetter(user.getEmail(), subj, body);
        assertNotNull(sentLetter);

        //6.	Verify, что письмо появилось в папке «Тест»
        indexPage.goToTestFoder();
        HashMap<String, WebElement> testFolderItem = indexPage.findLetter(user.getEmail(), subj, body);
        assertNotNull(testFolderItem);

        //7.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        assertTrue(testFolderItem
                .get("bodyFragment")
                .getText()
                .contains(body.length() > 99 ? body.substring(0,98) : body), "Body check " + testFolderItem.get("bodyFragment").getText());

        assertTrue(testFolderItem
                .get("subject")
                .getText()
                .contains(subj), "Check subject " + testFolderItem.get("subject").getText());

        assertEquals(testFolderItem
                .get("correspondent")
                .getText(), user.getUserName(), "Check user " + testFolderItem.get("correspondent").getText());

        //8.	Выйти из учётной записи
        indexPage.logout();
    }
}

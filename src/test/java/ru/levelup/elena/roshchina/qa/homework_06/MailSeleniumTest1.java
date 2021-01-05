package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;
import static ru.levelup.elena.roshchina.qa.utils.UsefulThing.getString;

/* Exercise 1
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


public class MailSeleniumTest1 extends AbstractBaseSeleniumTest {

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

        //3.	Создать новое письмо (заполнить адресата, тему письма и тело)
        //4.	Сохранить его как черновик
        indexPage.composeLetter(user.getEmail(), subj, body, false);

        //5.	Verify, что письмо сохранено в черновиках
        indexPage.goToDraftFoder();
        HashMap<String, WebElement> savedDraft = indexPage.findLetter(user.getEmail(), subj, body);  // ключи: subject, correspondent, bodyFragment, time
        assertNotNull(savedDraft);

        String savedDraftTime= savedDraft.get("time").getText(); // для доп. идентификации черновика

        //6.	Verify контент, адресата и тему письма (должно совпадать с пунктом 3)
        assertTrue(savedDraft
                .get("bodyFragment")
                .getText()
                .contains(body.length() > 99 ? body.substring(0,98) : body), "Body check " + savedDraft.get("bodyFragment").getText());

        assertTrue(savedDraft
                .get("subject")
                .getText()
                .contains(subj), "Check subject " + savedDraft.get("subject").getText());

        assertEquals(savedDraft
                .get("correspondent")
                .getText(), user.getEmail(), "Check user " + savedDraft.get("correspondent").getText());

        //7.	Отправить письмо
        indexPage.sendDraftLetter(savedDraft.get("subject"));

        //8.	Verify, что письмо исчезло из черновиков
        assertEquals(indexPage.findLetterByTime(savedDraftTime).size(), 0, "Checking that no letters with time=" + savedDraftTime);

        //9.	Verify, что письмо появилось в папке отправленные
        indexPage.goToOutputFoder();
        HashMap<String, WebElement> sentletters = indexPage.findLetter(user.getEmail(), subj, body);  // ключи: subject, correspondent, bodyFragment, time
        assertNotNull(sentletters);

        //10.	 Выйти из учётной записи
        indexPage.logout();
    }
}

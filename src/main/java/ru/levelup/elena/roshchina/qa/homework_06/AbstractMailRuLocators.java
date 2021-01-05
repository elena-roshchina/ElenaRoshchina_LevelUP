package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.By;

public abstract class AbstractMailRuLocators {

    protected final int maxBodyFragmentlenght = 99;
    protected final int timeOutSec = 30;
    protected final String url = "https://mail.ru/";

    // локаторы элементов формы логина
    protected final By loginInputFieldLocator  = By.name("login");
    protected final By openPswdInputFieldLocator = By.xpath("//button[contains(text(),'пароль')]");
    protected final By passwordInputFieldLocator = By.name("password");
    protected final By submitButtonLocator = By.xpath("//button[contains(text(),'Войти')]");
    // локатор линка выхода
    protected final By logoutLinkLocator = By.id("PH_logoutLink");
    // локаторы и фрагменты локаторов областей в странице редактирования/создания письма
    protected final By editLetterContainerLocator = By.className("head_container--3W05z");
    protected final By letterInputFieldsLocator = By.tagName("input");
    protected final String addressInputFieldAttributeName = "class";
    protected final String addressInputFieldAttributeFragment = "container--H9L5q";
    protected final String subjectInputFieldAttributeName = "name";
    protected final String subjectInputFieldAttributeFragment = "Subject";
    protected final By letterTextAreaLocator = By.xpath("//div[@role='textbox']//div");
    // локаторы кнопок действий с письмом в странице редактирования/создания письма
    protected final By saveDraftButtonLocator = By.xpath("//span[@title='Сохранить']");
    protected final By sendLetterButtonLocator = By.xpath("//span[@title='Отправить']");
    protected final By deleteReceivedLetterButtonLocator = By.xpath("//span[@title='Удалить']/span[@class='button2__wrapper']");
    // локатор элемента закрытия окан редактора письма
    protected final By closeLetterEditorLocator = By.xpath("//button[@title='Закрыть']");
    // локатор элемента закрытия окна, отрывающегося после отправки письма
    protected final By closeWinAfterComposeletterButtonLocator = By.cssSelector("div.layer-window__block span[title='Закрыть'] > span");
    // локаторы списков емейлов
    protected final By letterListItemLocator = By.xpath("//a[contains(@class,'letter-list-item')]");
    protected final By letterListItemInnerContainerLocator = By.xpath("./div[@class='llc__container']/div[contains(@class,'pony-mode')]");
    protected final By letterListItemCorrespondentLocator = By.xpath("./div[contains(@class,'correspondent')]/span");
    protected final By letterListItemSubjectLocator = By.xpath("./div[contains(@class,'item_title')]/span[contains(@class,'subject')]/span");
    protected final By letterListItemBodyFragmentLocator = By.xpath("./div[contains(@class,'item_title')]/span[contains(@class,'snippet')]/span");
    protected final By timeLetterListItemLocator = By.xpath("./div[contains(@class,'item_date')]");
    // локаторы пунктов меню слева
    protected final By composeButtonLocator = By.className("compose-button__txt");
    protected final By inputFolderLinkLocator = By.xpath("//a[contains(@title, 'Входящие')]//div[@class='nav__folder-name__txt']");
    protected final By draftFolderLinkLocator = By.partialLinkText("Черновики");
    protected final By outputFolderLinkLocator = By.xpath("//a[contains(@title, 'Отправленные')]//div[@class='nav__folder-name__txt']");
    protected final By testFolderLinkLocator = By.xpath("//a[contains(@title, 'Тест')]//div[@class='nav__folder-name__txt']");
    protected final By trashFolderLinkLocator = By.xpath("//a[contains(@title, 'Корзина')]//div[@class='nav__folder-name__txt']");

    // локатор для поиска письма по времени
    protected By getLetterbyTimeLocator(String time){
        return By.xpath("./div[@title='" + time + "']");
    }
}

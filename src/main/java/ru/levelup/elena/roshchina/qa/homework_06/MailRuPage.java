package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;


public class MailRuPage extends AbstractMailRuLocators{
    private WebDriver driver;
    private final int maxBodyFragmentlenght = 99;
    private final int timeOutSec = 30;

    @FindBy(className = "compose-button__txt")
    private WebElement compose_button;
    @FindBy(tagName = "input")
    private List<WebElement> inputs;                                             // Поля ввода создания емейла
    @FindBy(xpath = "//div[@role='textbox']//div")
    private WebElement textbox;
    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sendLetter;
    @FindBy(xpath = "//span[@title='Сохранить']")
    private WebElement saveDraft;
    @FindBy(xpath = "//button[@title='Закрыть']")
    private WebElement closeEditLetter;
    @FindBy(xpath = "//a[contains(@class,'letter-list-item')]")
    List<WebElement> itemLetters;
    @FindBy(partialLinkText = "Черновики")
    private WebElement draftLetterFolder;
    @FindBy(xpath = "//a[contains(@title, 'Отправленные')]//div[@class='nav__folder-name__txt']")
    private WebElement outputLetterFolder;
    @FindBy(xpath = "//a[contains(@title, 'Тест')]//div[@class='nav__folder-name__txt']")
    private WebElement testLetterFolder;
    @FindBy(id = "PH_logoutLink")
    private WebElement exitLink;
    @FindBy(xpath = "//a[contains(@title, 'Входящие')]//div[@class='nav__folder-name__txt']")
    private WebElement inputLetterFolder;
    @FindBy(xpath = "//a[contains(@title, 'Корзина')]//div[@class='nav__folder-name__txt']")
    private WebElement trashLetterFolder;
    @FindBy(xpath = "//span[@title='Удалить']/span[@class='button2__wrapper']")
    private WebElement deleteEmailButton;

    public MailRuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // создание письма с отправкой (send=true) или сохранением (send=false)
    public void composeLetter(String address, String subject, String text,  boolean send){
        compose_button.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(editLetterContainerLocator));
        inputs = driver.findElements(letterInputFieldsLocator);
        for (int i = 0; i< inputs.size(); i++) {
            if (inputs.get(i).getAttribute(addressInputFieldAttributeName).contains(addressInputFieldAttributeFragment)) {
                if (!inputs.get(i).getAttribute(subjectInputFieldAttributeName).contains(subjectInputFieldAttributeFragment)){
                    inputs.get(i).sendKeys(address);
                } else if (inputs.get(i).getAttribute(subjectInputFieldAttributeName).contains(subjectInputFieldAttributeFragment)) {
                    inputs.get(i).sendKeys(subject);
                }
            }
        }
        textbox.sendKeys(text);
        if (send) {
            sendLetter.click();
            try {
                new WebDriverWait(driver, timeOutSec)
                        .until(ExpectedConditions.elementToBeClickable(closeWinAfterComposeletterButtonLocator)).click();
            } catch (StaleElementReferenceException e) {
                new WebDriverWait(driver, timeOutSec)
                        .ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.elementToBeClickable(closeWinAfterComposeletterButtonLocator)).click();
            }
        } else {
            saveDraft.click();
            closeEditLetter.click();
        }
    }

    // Отвправка сохраненного письма
    public void sendDraftLetter(WebElement letterLink){
        letterLink.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(sendLetterButtonLocator));
        sendLetter.click();

        try {
            new WebDriverWait(driver, timeOutSec)
                    .until(ExpectedConditions.elementToBeClickable(closeWinAfterComposeletterButtonLocator)).click();
        } catch (StaleElementReferenceException e) {
            new WebDriverWait(driver, timeOutSec)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(closeWinAfterComposeletterButtonLocator)).click();
        }
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(draftFolderLinkLocator));
    }

    // удаление принятого письма
    public void deleteReceivedLetter(WebElement letterLink){
        letterLink.click();
        deleteEmailButton.click();
    }

    // Поиск письма на текущей странице по отправителю, теме и фрагменту текста письма
    public HashMap<String, WebElement> findLetter(String email, String subj, String bodyText){

        if (bodyText.length() > maxBodyFragmentlenght){
            bodyText = bodyText.substring(0, maxBodyFragmentlenght - 1);
        }

        WebElement letterItemContentContainer = null;
        WebElement letterCorrespondent = null;
        WebElement subjectItem = null;
        WebElement sentTimeOfItem = null;
        WebElement bodyTextFragment = null;

        String corrText = "";
        String itemSubjectText = "";
        String sentTime = "";
        String text = "";

        HashMap<String, WebElement> letter = null;

        for (int i = 0; i < itemLetters.size(); i++){
            letterItemContentContainer =  itemLetters.get(i).findElement(letterListItemInnerContainerLocator);

            letterCorrespondent = letterItemContentContainer.findElement(letterListItemCorrespondentLocator);
            corrText = letterCorrespondent.getAttribute("title");

            subjectItem =  letterItemContentContainer.findElement(letterListItemSubjectLocator);
            itemSubjectText = subjectItem.getText();

            sentTimeOfItem = letterItemContentContainer.findElement(timeLetterListItemLocator);

            bodyTextFragment = letterItemContentContainer.findElement(letterListItemBodyFragmentLocator);
            text = bodyTextFragment.getText();

            if (corrText.contains(email) && itemSubjectText.contains(subj) && text.contains(bodyText)){
                letter = new HashMap<>();
                letter.put("correspondent", letterCorrespondent);
                letter.put("subject", subjectItem);
                letter.put("time", sentTimeOfItem);
                letter.put("bodyFragment", bodyTextFragment);
                break;
            }
        }
        return letter;
    }

    // Поиск письма на текущей странице по времени
    public List<WebElement> findLetterByTime(String time){
        return driver.findElements(this.getLetterbyTimeLocator(time));
    }

    // Навигация по левому меню
    public void goToDraftFoder() {
        draftLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }
    public void goToOutputFoder() {
        outputLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }

    public void goToInputFoder() {
        inputLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }

    public void goToTestFoder() {
        testLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }
    public void goToTrashFoder() {
        trashLetterFolder.click();
    }
    // выход
    public void logout() {
        try {
            exitLink.click();
        } catch (org.openqa.selenium.TimeoutException e){
            exitLink.click();
        }
    }
}

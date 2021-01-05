package ru.levelup.elena.roshchina.qa.homework_06;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;


public class MailRuPage extends AbstractMailRuLocators{
    private WebDriver driver;
    private final int maxBodyFragmentlenght = 99;
    private final int timeOutSec = 30;

    private WebElement compose_button;
    private WebElement draftLetterFolder;
    private WebElement outputLetterFolder;
    private WebElement testLetterFolder;
    private WebElement exitLink;
    private WebElement inputLetterFolder;
    private WebElement trashLetterFolder;
    private WebElement deleteEmailButton;

    public MailRuPage(WebDriver driver) {
        this.driver = driver;
    }

    // создание письма с отправкой (send=true) или сохранением (send=false)
    public void composeLetter(String address, String subject, String text,  boolean send){
        compose_button = new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(composeButtonLocator));
        compose_button.click();

        WebElement editLetterContainer = new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(editLetterContainerLocator));

        List<WebElement> inputs = driver.findElements(letterInputFieldsLocator);
        for (int i = 0; i< inputs.size(); i++) {
            if (inputs.get(i).getAttribute(addressInputFieldAttributeName).contains(addressInputFieldAttributeFragment)) {
                if (!inputs.get(i).getAttribute(subjectInputFieldAttributeName).contains(subjectInputFieldAttributeFragment)){
                    inputs.get(i).sendKeys(address);
                } else if (inputs.get(i).getAttribute(subjectInputFieldAttributeName).contains(subjectInputFieldAttributeFragment)) {
                    inputs.get(i).sendKeys(subject);
                }
            }
        }
        WebElement textbox = driver.findElement(letterTextAreaLocator);
        textbox.sendKeys(text);
        if (send) {
            WebElement sendLetter = driver.findElement(sendLetterButtonLocator);
            sendLetter.click();
            try {
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(closeWinAfterComposeletterButtonLocator)).click();
            } catch (StaleElementReferenceException e) {
                new WebDriverWait(driver, 10)
                        .ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.elementToBeClickable(closeWinAfterComposeletterButtonLocator)).click();
            }
        } else {
            WebElement saveDraft = driver.findElement(saveDraftButtonLocator);
            saveDraft.click();
            WebElement closeEditLetter = new WebDriverWait(driver, timeOutSec)
                    .until(ExpectedConditions.elementToBeClickable(closeLetterEditorLocator));
            closeEditLetter.click();
        }
    }
    // Отвправка сохраненного письма
    public void sendDraftLetter(WebElement letterLink){
        letterLink.click();
        WebElement sendDraft = new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(sendLetterButtonLocator));
        sendDraft.click();
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
        deleteEmailButton =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(deleteReceivedLetterButtonLocator));
        deleteEmailButton.click();
    }
    // Поиск письма на текущей странице по отправителю, теме и фрагменту текста письма
    public HashMap<String, WebElement> findLetter(String email, String subj, String bodyText){
        HashMap<String, WebElement> letter = new HashMap<>();
        List<WebElement> draftLetters = driver.findElements(letterListItemLocator);
        for (int i = 0; i < draftLetters.size(); i++){
            WebElement draftItemContentContainer =  draftLetters.get(i)
                    .findElement(letterListItemInnerContainerLocator);

            WebElement letterCorrespondent = draftItemContentContainer.findElement(letterListItemCorrespondentLocator);
            String corrText = letterCorrespondent.getAttribute("title");
            letter.put("correspondent", letterCorrespondent);

            WebElement subjectItem =  draftItemContentContainer.findElement(letterListItemSubjectLocator);
            String itemSubjectText = subjectItem.getText();
            letter.put("subject", subjectItem);

            WebElement sentTimeOfItem = draftItemContentContainer.findElement(timeLetterListItemLocator);
            String sentTime = sentTimeOfItem.getText();
            letter.put("time", sentTimeOfItem);
            WebElement bodyTextFragment = draftItemContentContainer.findElement(letterListItemBodyFragmentLocator);
            String text = bodyTextFragment.getText();
            letter.put("bodyFragment", bodyTextFragment);

            if (bodyText.length() > maxBodyFragmentlenght){
                bodyText = bodyText.substring(0, maxBodyFragmentlenght - 1);
            }

            if (corrText.contains(email) && itemSubjectText.contains(subj) && text.contains(bodyText)){
                return letter;
            }
        }
        return null;
    }
    // Поиск письма на текущей странице по времени
    public List<WebElement> findLetterByTime(String time){
        return driver.findElements(this.getLetterbyTimeLocator(time));
    }

    // Навигация по левому меню
    public void goToDraftFoder() {
        draftLetterFolder =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(draftFolderLinkLocator));
        draftLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }
    public void goToOutputFoder() {
        outputLetterFolder =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(outputFolderLinkLocator));
        outputLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }

    public void goToInputFoder() {
        inputLetterFolder =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(inputFolderLinkLocator));
        inputLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }

    public void goToTestFoder() {
        testLetterFolder =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(testFolderLinkLocator));
        testLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }
    public void goToTrashFoder() {
        trashLetterFolder =  new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(trashFolderLinkLocator));
        trashLetterFolder.click();
        new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(letterListItemLocator));
    }
    // выход
    public void logout(){
        exitLink = new WebDriverWait(driver, timeOutSec)
                .until(ExpectedConditions.elementToBeClickable(logoutLinkLocator));
        try {
            exitLink.click();
        } catch (org.openqa.selenium.TimeoutException e){
            exitLink = new WebDriverWait(driver, timeOutSec)
                    .until(ExpectedConditions.elementToBeClickable(logoutLinkLocator));
            exitLink.click();
        }
    }
}

package ru.levelup.elena.roshchina.qa.final_work;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractBaseFleetManagement {

    protected final String FLEET_MENU = "Fleet";
    protected WebDriverWait wait;

    public final String userDropDownMenuXpath = "//li[@id='user-menu']/a[@class='dropdown-toggle']";
    @FindBy(xpath = userDropDownMenuXpath)          // Top Right Corner menu
    WebElement userDropDownMenu;

    protected final String logoutLinkXpath = "//a[@href='/user/logout']";
    @FindBy(xpath = logoutLinkXpath)
    WebElement logoutLink;

    protected final String mainMenuUlXpath = "//div[@id='main-menu']/ul[@class='nav-multilevel main-menu']";

    protected final String mainMenuLiXpath = "//li[contains(@class, 'dropdown-level-1')]//span[contains(@class, 'title-level-1')]";
    @FindBy(xpath = mainMenuLiXpath)
    List<WebElement> mainMenuItems;

    protected WebDriver driver;
    protected JavascriptExecutor js;

    public AbstractBaseFleetManagement(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void makePicture(String fn){
        waitForClicableElement(userDropDownMenu);
        TakesScreenshot sc = (TakesScreenshot)driver;
        File screensFile = sc.getScreenshotAs(OutputType.FILE);
        String fpath = "pics" + "/" + fn + ".png";
        try {
            FileUtils.copyFile(screensFile, new File(fpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Выход")
    public void logout(){
        waitForClicableElement(userDropDownMenu);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", userDropDownMenu);
        js.executeScript("arguments[0].click();", logoutLink);
    }

    public void waitForElement(WebElement we){
        try {
            this.wait.until(ExpectedConditions.visibilityOf(we));
        } catch (org.openqa.selenium.TimeoutException e){
            try {
                this.wait.until(ExpectedConditions.visibilityOf(we));
            } catch (org.openqa.selenium.TimeoutException ee){
                try {
                    this.wait.until(ExpectedConditions.visibilityOf(we));
                } catch (org.openqa.selenium.TimeoutException eee){
                    this.wait.until(ExpectedConditions.visibilityOf(we));
                }
            }
        }
    }

    public void waitForElements(List<WebElement> we){
        try {
            this.wait.until(ExpectedConditions.visibilityOfAllElements(we));
        } catch (org.openqa.selenium.TimeoutException e){
            try {
                this.wait.until(ExpectedConditions.visibilityOfAllElements(we));
            } catch (org.openqa.selenium.TimeoutException ee){
                try {
                    this.wait.until(ExpectedConditions.visibilityOfAllElements(we));
                } catch (org.openqa.selenium.TimeoutException eee){
                    this.wait.until(ExpectedConditions.visibilityOfAllElements(we));
                }
            }
        }
    }

    public void waitForClicableElement(WebElement we){
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(we));
        } catch (org.openqa.selenium.TimeoutException e){
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(we));
            } catch (org.openqa.selenium.TimeoutException ee){
                try {
                    this.wait.until(ExpectedConditions.elementToBeClickable(we));
                } catch (org.openqa.selenium.TimeoutException eee){
                    try {
                        this.wait.until(ExpectedConditions.elementToBeClickable(we));
                    } catch (org.openqa.selenium.TimeoutException eeee) {
                        this.wait.until(ExpectedConditions.elementToBeClickable(we));
                    }
                }
            }
        }
    }
}

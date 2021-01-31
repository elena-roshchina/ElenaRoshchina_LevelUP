package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbstractBaseFleetManagement {

    public final String LOGIN_URL = "https://qa2.vytrack.com/user/login";
    protected final String LOGIN = "storemanager207";
    protected final String PASSWORD = "UserUser123";
    public final String userDropDownMenuXpath = "//li[@id='user-menu']/a[@class='dropdown-toggle']";
    protected final String FLEET_MENU = "Fleet";

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

    public AbstractBaseFleetManagement(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void logout(){
        new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(userDropDownMenuXpath))); // ожидание загрузки пункта меню
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", userDropDownMenu);
        js.executeScript("arguments[0].click();", logoutLink);
    }
}

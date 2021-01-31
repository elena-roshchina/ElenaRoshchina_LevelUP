package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractBaseFleetManagement {

    @FindBy(name = "_username")
    private WebElement username_input;
    @FindBy(name = "_password")
    WebElement password_input;
    @FindBy(name ="_submit")
    WebElement submit_button;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //<li class="dropdown user-menu-dropdown" id="user-menu">
    //        <a href="javascript: void(0);" class="dropdown-toggle" data-toggle="dropdown">
    //                            Darrick Bergstrom
    //                                <i class="fa-caret-down"></i> </a>
    //                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
    //                            <li class="last">
    //            <a href="/user/logout" class="no-hash">Logout</a>
    /*
    @FindBy(xpath = userDropDownMenuXpath)
    WebElement userDropDownMenu;
    protected final String logoutLinkXpath = "//a[@href='/user/logout']";
    @FindBy(xpath = logoutLinkXpath)
    WebElement logoutLink;*/



    // открыть главную страницу
    public void open(){
        driver.manage().window().maximize();
        driver.navigate().to(LOGIN_URL);
    }

    // вход
    public DashboardsPage login(){
        username_input.sendKeys(LOGIN);
        password_input.sendKeys(PASSWORD);

        submit_button.click();
        new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(userDropDownMenuXpath))); // ожидание загрузки пункта меню

        return new DashboardsPage(driver);
    }




}

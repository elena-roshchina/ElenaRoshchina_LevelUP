package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    public String loginUrl;
    private String login;
    private String password;

    protected WebDriver driver;

    @FindBy(name = "_username")
    private WebElement username_input;
    @FindBy(name = "_password")
    WebElement password_input;
    @FindBy(name ="_submit")
    WebElement submit_button;

    public LoginPage(WebDriver driver, String loginUrl, String login, String password) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.login = login;
        this.loginUrl = loginUrl;
        this.password = password;
    }

    // открыть главную страницу
    public void open(){
        driver.manage().window().maximize();
        driver.navigate().to(loginUrl);
    }

    // вход
    public DashboardsPage login(){
        username_input.sendKeys(login);
        password_input.sendKeys(password);
        submit_button.click();
        return new DashboardsPage(driver);
    }
}

package ru.levelup.elena.roshchina.qa.homework_06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class AbstractBaseSeleniumTest {

    protected WebDriver driver;
    protected TestUser user;

    @BeforeSuite
    public void setupSuite(){ WebDriverManager.chromedriver().setup(); }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        user = new TestUser();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

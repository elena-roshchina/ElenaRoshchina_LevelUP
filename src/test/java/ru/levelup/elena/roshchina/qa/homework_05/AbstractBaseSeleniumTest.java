package ru.levelup.elena.roshchina.qa.homework_05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.levelup.elena.roshchina.qa.utils.Sleep;


public class AbstractBaseSeleniumTest {

    protected WebDriver driver;
    @BeforeSuite
    public void setupSuite(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

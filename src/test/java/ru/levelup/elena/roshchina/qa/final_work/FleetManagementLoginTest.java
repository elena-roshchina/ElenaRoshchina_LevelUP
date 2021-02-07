package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class FleetManagementLoginTest extends AbstractBaseFleetManagementTest  {



    @Test(testName = "Проверка логина")
    public void loginTest(){
        LoginPage entrancePage = new LoginPage(driver, loginUrl, login, password);
        entrancePage.open();                             //1.	Открыть сайт
        DashboardsPage mainPage = entrancePage.login();  //2.	Авторизоваться под заданным пользователем
        System.out.println(mainPage.getPageTitle());
        mainPage.logout();
    }
}

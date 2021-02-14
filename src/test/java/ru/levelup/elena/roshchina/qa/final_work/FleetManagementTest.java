package ru.levelup.elena.roshchina.qa.final_work;

import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/*
1.	Открыть сайт
2.	Авторизоваться под заданным пользователем
3.	Проверить, что авторизация прошла успешно
4.	Открыть Fleet -> Vehicles
5.	Нажать на кнопку Create Car
6.	На вкладке Generalзаполнить поля
•	License Plate
•	Tags (выбрать 3 tag)
•	Driver
•	Location
•	Model Year
•	Seats Number
•	Transmission
•	Fuel Type
•	VehicleModel (выбрать любую модель из списка)
7.	Нажать на кнопку Save And Close
8.	Проверить, что все поля заполнены в соответствии с введёнными данными
9.	Выйти из системы

 */


public class FleetManagementTest extends AbstractBaseFleetManagementTest {

    @Test(testName = "Проверка создания записи машины")
    public void createCarTest() {
        LoginPage entrancePage = new LoginPage(driver, loginUrl, login, password);
        entrancePage.open();                             //1.	Открыть сайт
        DashboardsPage mainPage = entrancePage.login();  //2.	Авторизоваться под заданным пользователем
        //3.	Проверить, что авторизация прошла успешно
        assertTrue(mainPage.getPageTitle().contains(DashboardsPage.EXPECTED_ENTRANCE_TITLE), mainPage.getPageTitle());

        //4.	Открыть Fleet -> Vehicles
        FleetPage vehiclesPage = mainPage.gotoFleet("Vehicles");
        //System.out.println(vehiclesPage.getPageTitle());

        //5.	Нажать на кнопку Create Car
        CreateCarPage createCarPage = vehiclesPage.createCar();
        //System.out.println(createCarPage.getPageTitle());

        //6.	На вкладке Generalзаполнить поля
        createCarPage.feelCreateCarForm(myCar);

        EntityCarPage carPage = createCarPage.saveAndClose();

        //System.out.println(carPage.getPageTitle());
        carPage.makePicture("car_created3");
        //8.	Проверить, что все поля заполнены в соответствии с введёнными данными

        HashMap<String, String> enteredData = carPage.getEnteredData(myCar.keySet().toArray(new String[0]));
        for (String key :
                myCar.keySet()) {
            if(!myCar.get(key).equals("any")){
                if (key.equals("Tags")){
                    String[] tagList = myCar.get(key).split(",");
                    for (String word:
                         tagList) {
                        assertTrue(enteredData.get(key).contains(word), "tag failure " + enteredData.get(key) + " not contains "  + word);
                    }
                } else {
                    assertEquals(enteredData.get(key), myCar.get(key), enteredData.get(key) + " != " +  myCar.get(key));
                }
            }
            //System.out.println(myCar.get(key));
            //System.out.println(enteredData.get(key));
        }
        carPage.logout();
        //vehiclesPage = createCarPage.cancel();
        //vehiclesPage.logout();
    }
}

package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardsPage extends AbstractBaseFleetManagement {

    public final String EXPECTED_ENTRANCE_TITLE = "Dashboard";

    public DashboardsPage(WebDriver driver) {
        super(driver);
    }


    public void goToMainMenuItem(String menuItem, String subItemName){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement foundSubItem;
        for (WebElement item : mainMenuItems) {
            if (item.getText().contains(menuItem)) {
                WebElement par = item.findElement(By.xpath("./../.."));
                js.executeScript("arguments[0].click();", item);
                foundSubItem = par.findElement(By.xpath("//span[normalize-space(text())='" + subItemName + "']"));
                js.executeScript("arguments[0].click();", foundSubItem);
            }
        }
    }

    public FleetPage gotoFleet(String subItemName){
        goToMainMenuItem(FLEET_MENU, subItemName);
        return new FleetPage(driver);
    }
}

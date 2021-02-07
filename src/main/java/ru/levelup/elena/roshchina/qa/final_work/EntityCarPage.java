package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class EntityCarPage extends AbstractBaseFleetManagement {

    protected String toTitle(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public EntityCarPage(WebDriver driver) {
        super(driver);
    }

    private String findFieldText(String fieldName){
        WebElement element = new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='" + fieldName + "']/parent::div")));
        WebElement elementChild = element.findElement(By.xpath("./div[@class='controls']/div[@class='control-label']"));
        return elementChild.getText();
    }

    public HashMap<String, String>getEnteredData(String[] fieldNames){
        waitForClicableElement(userDropDownMenu);

        HashMap<String, String> enteredData = new HashMap<>();
        for (String key:
             fieldNames) {
            enteredData.put(key, findFieldText(key));
        }
        return enteredData;
    }
}

package ru.levelup.elena.roshchina.qa.final_work;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class FleetPage extends AbstractBaseFleetManagement {

    public final String EXPECTED_ENTRANCE_TITLE = "All - Car - Entities - System - Car - Entities - System";

    protected final String createCarButtonXpath = "//div[@class='pull-right title-buttons-container']/a";
    @FindBy(xpath = createCarButtonXpath)
    private WebElement createCarButton;

    public FleetPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть веб-форму для создания записи машины")
    public CreateCarPage createCar(){
        waitForClicableElement(createCarButton);
        js.executeScript("arguments[0].click();", createCarButton);
        return new CreateCarPage(driver);
    }
}



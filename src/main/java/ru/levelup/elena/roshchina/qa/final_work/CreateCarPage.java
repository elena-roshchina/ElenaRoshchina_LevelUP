package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateCarPage extends AbstractBaseFleetManagement {

    public final String EXPECTED_ENTRANCE_TITLE = "Create Car - Entities - System - Car - Entities - System";

    protected final String saveAndCloseButtonXpath = "//div[@class='btn-group pull-right']";
    @FindBy(xpath = saveAndCloseButtonXpath)
    private WebElement saveAndCloseButton;

    protected final String cancelButtonXpath = "//div[@class='pull-left btn-group icons-holder']";
    @FindBy(xpath = cancelButtonXpath)
    private WebElement cancelButton;

    protected final String inputLicenseXpath = "//input[@name='custom_entity_type[LicensePlate]']";
    @FindBy(xpath = inputLicenseXpath)
    private WebElement inputLicense;

    protected final String boxTagXpath = "//input[@type='checkbox']";
    @FindBy(xpath = boxTagXpath)
    List<WebElement> tagsBoxes;

    protected final String inputDriverXpath = "//input[@name='custom_entity_type[Driver]']";
    @FindBy(xpath = inputDriverXpath)
    private WebElement inputDriver;

    protected final String inputLocationXpath = "//input[@name='custom_entity_type[Location]']";
    @FindBy(xpath = inputLocationXpath)
    private WebElement inputLocation;

    protected final String inputModelYearXpath = "//input[@name='custom_entity_type[ModelYear]']";
    @FindBy(xpath = inputModelYearXpath)
    private WebElement inputModelYear;

    protected final String inputSeatsXpath = "//input[@name='custom_entity_type[SeatsNumber]']";
    @FindBy(xpath = inputSeatsXpath)
    private WebElement inputSeats;

    protected final String selectTransmissionXpath = "//div[contains(@id, 'Transmission')]";
    @FindBy(xpath = selectTransmissionXpath)
    private WebElement selectTransmission;

    protected final String selectFuelXpath = "//div[contains(@id, 'FuelType')]";
    @FindBy(xpath = selectFuelXpath)
    private WebElement selectFuel;

    protected final String addModelXpath = "//div[contains(@id,'custom_entity_type_Vehicle_Model-uid')]";
    @FindBy(xpath = addModelXpath)
    private List<WebElement> addModelElements;

    public void addModel(){
        System.out.println("elements");
        System.out.println(addModelElements.size());
        for (int i=0; i<addModelElements.size(); i++){
            if (addModelElements.get(i).getAttribute("id").contains("container")){
                System.out.println(addModelElements.get(i).getAttribute("id"));
                // <button type="button" class="btn btn-medium add-btn" data-purpose="open-dialog-widget"
                // name="temp-validation-name-6965" aria-invalid="false"><i class="fa-plus"></i>Add</button>
                WebElement addButton = addModelElements.get(i).findElement(By.xpath(".//i[@class='fa-plus']"));
                System.out.println(addButton.getAttribute("class"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", addButton);
                Sleep.sleep(500);
                //List<WebElement> modelRow = driver.findElements(By.xpath("//td[@data-column-label='Model Name']"));
                WebElement dialogLayer = driver
                        .findElement(By.xpath("//div[@role='dialog']/div[@class='ui-dialog-content ui-widget-content']/div[@class='widget-content grid-widget-content']"));
                System.out.println(dialogLayer.getAttribute("data-wid"));
                WebElement outerTable = dialogLayer.findElement(By.tagName("table"));
                System.out.println(outerTable.getAttribute("class"));

                //List<WebElement> cells = outerTable.findElements(By.tagName("td"));
                //System.out.println(cells.size());

                List<WebElement> checkBoxes = dialogLayer.findElements(By.xpath("./input[@type='checkbox']"));
                //System.out.println("models found");
                System.out.println(checkBoxes.size());

                WebElement modelCancelButton = driver.findElement(By.xpath("//div[@class='form-actions widget-actions']//button[@type='reset']"));
                System.out.println(modelCancelButton.getAttribute("class"));
                Sleep.sleep(500);
                js.executeScript("arguments[0].click();", modelCancelButton);

            }

        }
    }

    private void chooseTransmission(){
        System.out.println(selectTransmission.getTagName());
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", selectTransmission);
        //WebElement option = driver.findElement(By.xpath("//div[contains(text(), 'Automatic')]"));
    }

    private void chooseFuelType(){
        System.out.println(selectFuel.getTagName());
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();", selectFuel);
        //WebElement option = driver.findElement(By.xpath("//div[contains(text(), 'Diesel')]"));
    }

    private void setTags(){
        for(int i=0; i < tagsBoxes.size(); i++){
            System.out.println(tagsBoxes.get(i).getAttribute("value"));
        }
    }


    public CreateCarPage(WebDriver driver) {
        super(driver);
    }

    public void saveAndClose(){
        System.out.println(saveAndCloseButton.getText());
        System.out.println("Create Car - save");
    }

    public void cancel(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cancelButton);
        System.out.println("Create Car - cancel");
    }
}

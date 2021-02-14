package ru.levelup.elena.roshchina.qa.final_work;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.List;

public class CreateCarPage extends AbstractBaseFleetManagement {

    public final String EXPECTED_ENTRANCE_TITLE = "Create Car - Entities - System - Car - Entities - System";

    protected final String saveAndCloseButtonXpath = "//button[@type='submit']";
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

    protected final String selectTransmissionXpath = "//div[contains(@id, 's2id_custom_entity_type_Transmission-uid')]";
    @FindBy(xpath = selectTransmissionXpath)
    private WebElement selectTransmission;

    //protected final String selectFuelXpath = "//div[contains(@id, 'FuelType')]";
    protected final String selectFuelXpath = "//select[contains(@id, 'FuelType-uid')]";
    @FindBy(xpath = selectFuelXpath)
    private WebElement selectFuel;

    protected final String vehicleModelLinkText = "VehiclesModel";
    @FindBy(linkText = vehicleModelLinkText)
    WebElement vehicleModelLink;

    protected final String addModelXpath = "//div[contains(@id,'custom_entity_type_Vehicle_Model-uid')]";
    @FindBy(xpath = addModelXpath)
    List<WebElement> addModelElements;

    protected final String modelListTableXpath = "//div[@class='grid-container']/table/tbody/tr[3]/td[1]/input";
    @FindBy(xpath = modelListTableXpath)
    WebElement modelListTable;

    public void feelCreateCarForm(HashMap<String, String> car){
        waitForElement(inputSeats);

        inputLicense.sendKeys(car.get("License Plate"));

        String[] tags = car.get("Tags").split(",");
        for (WebElement box:
             tagsBoxes) {
            for (String tag:
                 tags) {
                if (tag.toLowerCase().equals(box.getAttribute("value").toLowerCase())) {
                    js.executeScript("arguments[0].click();", box);
                    break;
                }
            }
        }

        inputDriver.sendKeys(car.get("Driver"));
        inputLocation.sendKeys(car.get("Location"));
        inputModelYear.sendKeys(car.get("Model Year"));
        inputSeats.sendKeys(car.get("Seats Number"));
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        js.executeScript("arguments[0].click();",  vehicleModelLink);
        addModel();
        waitForClicableElement(selectTransmission);
        chooseTransmission();
        waitForClicableElement(selectFuel);
        chooseFuelType();
    }

    private void addModel(){
        for (int i=0; i<addModelElements.size(); i++){
            if (addModelElements.get(i).getAttribute("id").contains("container")){
                WebElement addButton = addModelElements.get(i).findElement(By.xpath("./div[@class='actions clearfix']//div[@class='pull-right']/button[@data-purpose='open-dialog-widget']"));
                js.executeScript("arguments[0].click();", addButton);
                waitForElement(modelListTable);
                WebElement modelCheckbox =  driver.findElement(By.xpath("//div[@class='grid-container']/table/tbody/tr[3]/td[1]/input"));
                js.executeScript("arguments[0].click();", modelCheckbox);
                WebElement modelCancelButton = driver.findElement(By.xpath("//div[@class='form-actions widget-actions']//button[@type='reset']"));
                WebElement modelSelectButton = driver.findElement(By.xpath("//div[@class='form-actions widget-actions']//button[@data-action-name='select']"));
                waitForElement(modelSelectButton);
                js.executeScript("arguments[0].click();", modelSelectButton);
                WebElement modelLink = addModelElements.get(i).findElement(By.xpath("//input[@type='radio']"));
                waitForElement(modelLink);
                js.executeScript("arguments[0].click();", modelLink);
            }
        }
    }

    private void chooseTransmission(){
        WebElement elem = selectTransmission.findElement(By.xpath("//span[@class='select2-chosen']"));
        waitForClicableElement(elem);
        if (elem.isDisplayed()){
            js.executeScript("arguments[0].click();", elem);
        } else {
            System.out.println("Select transmission NOT displayed");
            return;
        }
        //WebElement option = driver.findElement(By.xpath("//div[contains(text(), 'Automatic')]"));
        //WebElement option = selectTransmission.findElement(By.xpath("//option[@value='automatic']"));

        //WebElement arrow = selectTransmission.findElement(By.xpath("//span[@class='select2-arrow']"));
        //js.executeScript("arguments[0].click();", arrow);

        List<WebElement> options = driver.findElements(By.xpath("//ul[@class='select2-result']/li"));
        if (options.size() == 0){
            System.out.println("Select OPTIONs transmission NOT found");
            return;
        }
        if(options.get(0).isDisplayed()){
            js.executeScript("arguments[0].click();", options.get(0));
        } else{
            System.out.println("Select OPTIONs transmission NOT displayed");
            return;
        }
        return;
    }

    private void chooseFuelType(){
        //System.out.println("selectFuel.getTagName()=>");
        //System.out.println(selectFuel.getTagName());
        js.executeScript("arguments[0].click();", selectFuel);

        WebElement option = selectFuel.findElement(By.xpath("//option[@value='gasoline']"));
        js.executeScript("arguments[0].click();", selectFuel);
        //WebElement option = driver.findElement(By.xpath("//div[contains(text(), 'Diesel')]"));
    }

    public CreateCarPage(WebDriver driver) {
        super(driver);
    }

    public EntityCarPage saveAndClose(){
        //System.out.println(saveAndCloseButton.getAttribute("class"));
        //System.out.println("Create Car - save");
        js.executeScript("arguments[0].click();", saveAndCloseButton);
        return new EntityCarPage(driver);
    }

    public FleetPage cancel(){
        js.executeScript("arguments[0].click();", cancelButton);
        System.out.println("Create Car - cancel");
        return new FleetPage(driver);
    }
}


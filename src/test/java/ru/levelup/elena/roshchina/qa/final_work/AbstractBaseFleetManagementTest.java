package ru.levelup.elena.roshchina.qa.final_work;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.net.URL;
import java.util.HashMap;


public abstract class AbstractBaseFleetManagementTest {

    protected WebDriver driver;
    public HashMap<String, String> myCar = new HashMap<String, String>();

    private final String carDataFileName = "car_data.csv";
    private final String userLoginDataFileName = "login_data.csv";
    private URL dataFilePathName = this.getClass().getResource("/ru.levelup.elena.roshchina.qa/data_files/final_work/");

    public String loginUrl;     // = "https://qa2.vytrack.com/user/login";
    public String login;        // = "storemanager207";
    public String password;     // = "UserUser123";

    @BeforeSuite
    public void setupSuite(){
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void setUp() throws IOException {
        driver = new ChromeDriver();

        String[] data;
        String line;
        File file;
        FileReader fr;
        BufferedReader reader;

        file = new File(dataFilePathName.getPath()+userLoginDataFileName);
        fr = new FileReader(file);
        reader = new BufferedReader(fr);

        data = reader.readLine().replace("\n", "").split(";");

        loginUrl = data[0];
        login = data[1];
        password = data[2];

        file = new File(dataFilePathName.getPath()+carDataFileName);
        fr = new FileReader(file);
        reader = new BufferedReader(fr);

        line = reader.readLine();
        while (line != null) {
            data = line.replace("\n", "").split(";");
            myCar.put(data[0], data[1]);
            line = reader.readLine();
        }

        /*
        myCar.put("License Plate", "01AA");
        myCar.put("Tags", "Junior,Purchased,Sedan");
        myCar.put("Driver", "speedracer13");
        myCar.put("Location", "Trinidad-and-Tobago");
        myCar.put("Model Year", "2019");
        myCar.put("Seats Number", "2");
        myCar.put("Transmission", "any");
        myCar.put("Fuel Type", "any");
        myCar.put("Vehicle Model", "any");*/

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}

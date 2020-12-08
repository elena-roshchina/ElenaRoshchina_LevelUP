package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.*;
import ru.levelup.qa.at.calculator.Calculator;

public class AbstractBaseTest {

    Calculator calculator;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println( "====> beforeSuite Method");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("--> Before Test Method");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println(this.getClass().getName() + " before class Method");
    }

    @BeforeMethod
    public void setUp(){
        calculator = new Calculator();
        System.out.println(this.getClass().getName() +  " SetUp Method");
    }

    @AfterMethod
    public void tearDown(){
        System.out.println(this.getClass().getName() + " TearDown Method");
        calculator = null;
    }

    @AfterClass
    public void afterClass(){
        System.out.println(this.getClass().getName() + " after class Method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("--> After Test Method");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println( "====> afterSuite Method");
    }

}

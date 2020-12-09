package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CalculatorTrigTest extends AbstractBaseTest{
    /*
    public double tg(double a)
    public double ctg(double a)
    public double cos(double a)
    public double sin(double a)
    */
    private double[] angles = {0.0, 30.0, 45.0, 90.0};
    private Object[][] data = new Object[angles.length][2];

    @DataProvider
    public Object[][] tanDataSet(){
        for(int i=0; i<angles.length; i++){
            data[i][0] = Math.toRadians(angles[i]);
            data[i][1] = Math.tan(Math.toRadians(angles[i]));
        }
        return data;
    }

    @DataProvider
    public Object[][] cotanDataSet(){
        for(int i=0; i<angles.length; i++){
            data[i][0] = Math.toRadians(angles[i]);
            if (Math.sin(Math.toRadians(angles[i])) == 0) data[i][1] = Double.NaN;
            else data[i][1] = 1 / Math.tan(Math.toRadians(angles[i]));
        }
        return data;
    }

    @DataProvider
    public Object[][] cosDataSet(){
        for(int i=0; i<angles.length; i++){
            data[i][0] = Math.toRadians(angles[i]);
            data[i][1] = Math.cos(Math.toRadians(angles[i]));
        }
        return data;
    }

    @DataProvider
    public Object[][] sinDataSet(){
        for(int i=0; i<angles.length; i++){
            data[i][0] = Math.toRadians(angles[i]);
            data[i][1] = Math.sin(Math.toRadians(angles[i]));
        }
        return data;
    }

    @Test(dataProvider = "tanDataSet")
    public void doubleTanTest(double a, double expected){
        System.out.println("\tDouble Tangent TEST Method");
        double actual = calculator.tg(a);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "cotanDataSet")
    public void doubleCotanTest(double a, double expected){
        System.out.println("\tDouble Cotangent TEST Method");
        double actual = calculator.ctg(a);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "cosDataSet")
    public void doubleCosTest(double a, double expected){
        System.out.println("\tDouble Cosines TEST Method");
        double actual = calculator.cos(a);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "sinDataSet")
    public void doubleSinTest(double a, double expected){
        System.out.println("\tDouble Sines TEST Method");
        double actual = calculator.sin(a);
        assertEquals(actual, expected);
    }
}

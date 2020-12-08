package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CalculatorTrigTest extends AbstractBaseTest{
    /*
    public double tg(double a)
    public double ctg(double a)
    public double cos(double a)
    public double sin(double a)
    */

    @Test
    @Parameters({"a", "expected"})
    public void doubleTanTest(double a, double expected){
        System.out.println("Double Tangent TEST Method");
        double actual = calculator.tg(a);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "expected"})
    public void doubleCotanTest(double a, double expected){
        System.out.println("Double Cotangent TEST Method");
        double actual = calculator.ctg(a);
        assertEquals(actual, expected);
    }
    @Test
    @Parameters({"a", "expected"})
    public void doubleCosTest(double a, double expected){
        System.out.println("Double Cosines TEST Method");
        double actual = calculator.cos(a);
        assertEquals(actual, expected);
    }
    @Test
    @Parameters({"a", "expected"})
    public void doubleSinTest(double a, double expected){
        System.out.println("Double Sines TEST Method");
        double actual = calculator.cos(a);
        assertEquals(actual, expected);
    }
}

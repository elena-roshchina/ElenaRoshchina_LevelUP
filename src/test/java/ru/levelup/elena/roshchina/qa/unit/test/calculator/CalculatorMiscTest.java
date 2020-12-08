package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CalculatorMiscTest extends AbstractBaseTest{
    /*
    public double pow(double a, double b)
    public double sqrt(double a)
    public boolean isPositive(long val)
    public boolean isNegative(long val)
     */

    @Test
    @Parameters({"a", "b", "expected"})
    public void doublePowTest(double a, double b, double expected){
        System.out.println("Double Power TEST Method");
        double actual = calculator.pow(a,b);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "expected"})
    public void doubleSqrtTest(double a, double expected){
        System.out.println("Double SQRT TEST Method");
        double actual = calculator.sqrt(a);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "expected"})
    public void boolIsPositiveTest(long a, boolean expected){
        System.out.println("Boolean IsPositive TEST Method");
        boolean actual = calculator.isPositive(a);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "expected"})
    public void boolIsNegativeTest(long a, boolean expected){
        System.out.println("Boolean IsNegative TEST Method");
        boolean actual = calculator.isNegative(a);
        assertEquals(actual, expected);
    }
}


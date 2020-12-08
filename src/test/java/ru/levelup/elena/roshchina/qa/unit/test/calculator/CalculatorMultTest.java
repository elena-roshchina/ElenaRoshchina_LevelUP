package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CalculatorMultTest extends AbstractBaseTest{

    @Test
    @Parameters({"a", "b", "expected"})
    public void longMultTest(long a, long b, long expected){
        System.out.println("Long Multiplication TEST Method");
        long actual = calculator.mult(a,b);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "b", "expected"})
    public void doubleMultTest(double a, double b, double expected){
        System.out.println("Double Multiplication TEST Method");
        double actual = calculator.mult(a,b);
        assertEquals(actual, expected);
    }
}


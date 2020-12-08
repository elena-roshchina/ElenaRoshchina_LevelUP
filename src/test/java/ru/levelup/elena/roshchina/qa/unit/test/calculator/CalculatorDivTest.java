package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CalculatorDivTest extends AbstractBaseTest{

    @Test
    @Parameters({"a", "b", "expected"})
    public void longDivTest(long a, long b, long expected){
        System.out.println("Long Division TEST Method");
        long actual = calculator.div(a,b);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "b", "expected"})
    public void doubleDivTest(double a, double b, double expected){
        System.out.println("Double Division TEST Method");
        double actual = calculator.div(a,b);
        assertEquals(actual, expected);
    }
}

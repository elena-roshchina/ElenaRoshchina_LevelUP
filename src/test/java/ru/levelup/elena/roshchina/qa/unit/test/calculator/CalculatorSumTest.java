package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


public class CalculatorSumTest extends AbstractBaseTest{

    @Test
    @Parameters({"a", "b", "expected"})
    public void longSumTest(long a, long b, long expected){
        System.out.println("\tLong Sum TEST Method");
        long actual = calculator.sum(a,b);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "b", "expected"})
    public void doubleSumTest(double a, double b, double expected){
        System.out.println("\tDouble Sum TEST Method");
        double actual = calculator.sum(a,b);
        assertEquals(actual, expected);
    }
}

package ru.levelup.elena.roshchina.qa.unit.test.calculator;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.levelup.elena.roshchina.qa.unit.test.calculator.AbstractBaseTest;

import static org.testng.Assert.assertEquals;


public class CalculatorSubTest extends AbstractBaseTest {

    @Test
    @Parameters({"a", "b", "expected"})
    public void longSubTest(long a, long b, long expected){
        System.out.println("Long Substraction TEST Method");
        long actual = calculator.sub(a,b);
        assertEquals(actual, expected);
    }

    @Test
    @Parameters({"a", "b", "expected"})
    public void doubleSubTest(double a, double b, double expected){
        System.out.println("Double Substraction TEST Method");
        double actual = calculator.sub(a,b);
        assertEquals(actual, expected);
    }
}
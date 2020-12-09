package ru.levelup.elena.roshchina.qa.unit.test.calculator;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.testng.Assert.assertEquals;


public class CalculatorMultTest extends AbstractBaseTest{

    @DataProvider
    public Iterator<Object[]> dataDoubleSetMult(){
        return Arrays.asList(
                new Object[] {2.0, 2.9, 5.8},
                new Object[] {2.1, 2.0, 4.2},
                new Object[] {4.0, 3.5, 14.0})
                .iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataLongSetMult(){
        return Arrays.asList(
                new Object[] {2L, 2L, 4L},
                new Object[] {-3L, 2L, -6L},
                new Object[] {4L, 3L, 12L})
                .iterator();
    }

    /*
    public long mult(long a, long b) {
        return a * b;
    }

    public double mult(double a, double b) {
        return Math.floor(a * b);
    }
     */

    @Test(dataProvider = "dataLongSetMult")
    public void longMultTest(long a, long b, long expected){
        System.out.println("\tLong Multiplication TEST Method");
        long actual = calculator.mult(a,b);
        System.out.println(actual);
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "dataDoubleSetMult")
    public void doubleMultTest(double a, double b, double expected){
        System.out.println("\tDouble Multiplication TEST Method");
        double actual = calculator.mult(a,b);
        System.out.println(actual);
        assertEquals(actual, expected);
    }
}


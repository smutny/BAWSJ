package pl.edu.amu.bawsj.junit;

import org.junit.*;

public class FizzBuzzTest
{

    @Test
    public void forNumberDivisibleByBoth5And3ShouldReturnFizzBuzzNotJustFizz() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        Assert.assertEquals("FizzBuzz", fizzBuzz.go(30));
    }

    @Test
    public void shouldJustReturnNumberIfNumberIsNeitherDivisibleBy3Or5() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        Assert.assertEquals("4", fizzBuzz.go(4));
    }

    @Test
    public void shouldWorkForNegativeNumbers() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        Assert.assertEquals("-1", fizzBuzz.go(-1));
        Assert.assertEquals("Buzz", fizzBuzz.go(-25));
        Assert.assertEquals("Fizz", fizzBuzz.go(-21));
    }
}
package pl.edu.amu.bawsj.junit;

import org.junit.*;
public class AddTest
{
    Add add;

    @Before
    public void initalizeAdd() {
        add = new Add();
    }

    @Test
    public void shouldAddTwoNumbers() {
        Assert.assertEquals(5, add.go(2,3));
        Assert.assertEquals(7, add.go(3,4));
        Assert.assertNotEquals(10, add.go(2,3));
    }

    @Test (expected = ArithmeticException.class)
    public void shouldAddingToIntMaxThrowException() {
        add.go(Integer.MAX_VALUE, 2);
    }

    @Test(expected = ArithmeticException.class)
    public void shouldAddingToMinIntThrowException() {
        add.go(Integer.MIN_VALUE, -20);
    }
}
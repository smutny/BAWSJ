package pl.edu.amu.bawsj.mockito.c;

import org.junit.*;
import org.mockito.Mockito;

public class StringCreatorTest
{

    @Test
    public void shouldCreateStringCorrectly()
    {
        SoutPrinter soutPrinter = Mockito.mock(SoutPrinter.class);
        StringCreator stringCreator = new StringCreator(soutPrinter);
        Assert.assertEquals("Hi there", stringCreator.create());
    }
}
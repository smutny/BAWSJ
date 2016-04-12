package pl.edu.amu.bawsj.refactoring.c;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

public class ImieninyHandlerTest {

    @Test
    public void testIfNameIsCheckedCorrectly() throws IOException {
        BufferedReader readerMocked = Mockito.mock(BufferedReader.class);
        Mockito.when(readerMocked.readLine())
                .thenReturn("firstName")
                .thenReturn("secondName")
                .thenReturn(null);
        ImieninyHandler imieninyHandler = new ImieninyHandler(readerMocked);
        Assert.assertEquals(true, imieninyHandler.isNameInReader("firstName"));
        Assert.assertEquals(false, imieninyHandler.isNameInReader("thirdName"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfCorrectExceptionIsThrownWhenNullReaderIsGiven() throws IOException{
        BufferedReader bufferedReader = null;
        ImieninyHandler imieninyHandler = new ImieninyHandler(bufferedReader);
    }
}
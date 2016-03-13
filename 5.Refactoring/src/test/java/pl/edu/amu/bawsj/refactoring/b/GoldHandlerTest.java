package pl.edu.amu.bawsj.refactoring.b;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by rafal on 3/13/16.
 */
public class GoldHandlerTest {

    @Test
    public void testIfBiggestAverageIsCountedCorrectly() throws IOException, ParseException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine())
                .thenReturn("30-Jan-2016,99999,99997,99998,,1147.43,1115.83,1146.42,\n")
                .thenReturn("04-Feb-2015,40961.74,38285.75,38590.14,,1274.05,1190.82,1200.29,\n")
                .thenReturn(null);
        GoldHandler goldHandler = new GoldHandler(reader);

        Assert.assertEquals(99998.0, goldHandler.biggestAverage(), 0.003);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfCorrectExceptionIsThrownWhenNullReaderIsPassed() throws IOException, ParseException {
        BufferedReader reader = null;
        GoldHandler goldHandler = new GoldHandler(reader);
    }
}

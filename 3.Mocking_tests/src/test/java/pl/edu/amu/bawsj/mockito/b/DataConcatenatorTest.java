package pl.edu.amu.bawsj.mockito.b;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DataConcatenatorTest {

    @Test
    public void shouldConcatenateSimpleStringsWithMockedDataChanger() {
        Data data = Mockito.mock(Data.class);
        Mockito.when(data.getA()).thenReturn("c");
        Mockito.when(data.getB()).thenReturn("d");
        DataChanger dataChanger = Mockito.mock(DataChanger.class);

        DataConcatenator dataConcatenator = new DataConcatenator(dataChanger);
        Assert.assertEquals("c:d", dataConcatenator.concatenate(data));
    }
}
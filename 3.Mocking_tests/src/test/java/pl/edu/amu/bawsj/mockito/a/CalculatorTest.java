package pl.edu.amu.bawsj.mockito.a;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by rafal on 3/6/16.
 */
public class CalculatorTest {

    @Test
    public void shouldCalculateCorrectlyForMockedDataProvider() {
        DataProvider dataProvider = Mockito.mock(DataProvider.class);
        Mockito.when(dataProvider.get()).thenReturn(3.0);
        Calculator calculator = new Calculator(dataProvider);
        Assert.assertEquals(9, calculator.calculate(), 0.003);
    }
}

package pl.edu.amu.bawsj.mockito.d;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ResultProcessorTest
{

    @Test
    public void shouldProcessorProvideCorrectAnswerWithMockedProvider() {
        ResultProvider resultProvider = Mockito.mock(ResultProvider.class);
        Mockito.when(resultProvider.provide())
                .thenReturn(22.123);
        ResultProcessor resultProcessor = new ResultProcessor(resultProvider);
        Assert.assertEquals("22.123 22.123", resultProcessor.provide());
    }
}
package pl.edu.amu.bawsj.mockito.d;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ResultProcessorTest
{

    @Test
    public void shouldNameThisFunctionInACorrectWay()
    {

        ResultProvider mock = Mockito.mock( ResultProvider.class );

        Mockito.when( mock.provide() ).thenReturn( 3.0 );
        ResultProvider variable = new ResultProvider()
        {
            @Override
            public double provide()
            {
                return 3.0;
            }
        };
        ResultProcessor processor = new ResultProcessor( variable );

        Assert.assertEquals("3.0 3.0", processor.provide() );

    }
}
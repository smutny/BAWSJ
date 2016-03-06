package pl.edu.amu.bawsj.junit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class MovingAverageTest
{

    // klasa ma wyliczac srednia z ostatnich N podanych liczb
    // jezeli uzytkownik nie poda zadnej liczby a zechce "srednia" to wyrzucany jest wyjatek: illegalstateexception

    @Test
    @Ignore
    public void shouldSupportBigSetOfData()
    {
        MovingAverage movingAverage = new MovingAverage( 3 );
        for( long i = 0; i < 10000000l; i++ )
        {
            movingAverage.push( 3 );
        }
        Assert.assertEquals( 3, movingAverage.getAvg(), 0.003 );
    }
}

package pl.edu.amu.bawsj.junit;

import org.junit.Assert;
import org.junit.Test;

public class MovingAverageTest
{

    // klasa ma wyliczac srednia z ostatnich N podanych liczb
    // jezeli uzytkownik nie poda zadnej liczby a zechce "srednia" to wyrzucany jest wyjatek: illegalstateexception

    @Test
    public void shouldSupportBigSetOfData()
    {
        MovingAverage movingAverage = new MovingAverage( 3 );
        for( long i = 0; i < 10000000l; i++ )
        {
            movingAverage.push( 3 );
        }
        Assert.assertEquals( 3, movingAverage.getAvg(), 0.003 );
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrowExceptionIfNoNumbersWerePushed() {
        MovingAverage movingAverage = new MovingAverage(3);
        movingAverage.getAvg();
    }

    @Test (expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenArgumentIsLessThan0() {
        MovingAverage movingAverage = new MovingAverage(-1);
    }

    @Test
    public void shouldOnlyReturnAverageFromLastNNumbersNotFromAll() {
        MovingAverage movingAverage = new MovingAverage(3);
        movingAverage.push(10000);
        movingAverage.push(1);
        movingAverage.push(1);
        movingAverage.push(1);
        Assert.assertEquals(1, movingAverage.getAvg(), 0.003);
    }
}

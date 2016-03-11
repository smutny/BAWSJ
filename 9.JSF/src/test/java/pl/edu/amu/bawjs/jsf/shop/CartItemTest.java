package pl.edu.amu.bawjs.jsf.shop;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class CartItemTest
{

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowExceptionWhenQuantityIsZero()
    {
        new CartItem( Mockito.mock( Product.class ), 0 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void shouldThrowExceptionWhenQuantityIsMinusOne()
    {
        new CartItem( Mockito.mock( Product.class ), -1 );
    }

    @Test
    public void shouldReturnCorrectValueIfQuantityIsOne()
    {
        Product mock = Mockito.mock( Product.class );
        Mockito.when( mock.getPrice() ).thenReturn( new BigDecimal( 22.1 ) );

        CartItem item = new CartItem( mock, 1 );

        Assert.assertEquals( new BigDecimal( 22.1 ), item.getSubTotal() );
    }

    @Test
    public void shouldReturnCorrectValueIfQuantityIsMoreThanOne()
    {
        Product mock = Mockito.mock( Product.class );
        Mockito.when( mock.getPrice() ).thenReturn( new BigDecimal( 22.1 ) );

        CartItem item = new CartItem( mock, 2 );

        Assert.assertEquals( new BigDecimal( 22.1 ).multiply( BigDecimal.valueOf( 2 ) ), item.getSubTotal() );
    }
}
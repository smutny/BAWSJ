package pl.edu.amu.bawjs.jsf.shop;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class CartTest
{
    @Test
    public void shouldReturnZeroIfThereIsNoItemInCart()
    {
        Cart cart = new Cart();

        Assert.assertEquals( new BigDecimal( 0.0 ), cart.getTotal() );
    }

    @Test
    public void shouldReturnCorrectPriceForSingleProduct()
    {
        Cart cart = new Cart();

        CartItem cartItemMock = Mockito.mock( CartItem.class );
        Mockito.when( cartItemMock.getSubTotal() ).thenReturn( new BigDecimal( 30.0 ) );
        cart.add( cartItemMock );

        Assert.assertEquals( new BigDecimal( 30.0 ), cart.getTotal() );
    }

    @Test
    public void shouldReturnCorrectPriceForManyProducts()
    {
        Cart cart = new Cart();

        CartItem cartItemMock = Mockito.mock( CartItem.class );
        Mockito.when( cartItemMock.getSubTotal() ).thenReturn( new BigDecimal( 30.0 ) );
        cart.add( cartItemMock );
        cart.add( cartItemMock );

        Assert.assertEquals( new BigDecimal( 60.0 ), cart.getTotal() );
    }
}
package pl.edu.amu.bawjs.jsf.shop;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mbocian on 2016-03-11.
 */
public class CartItem implements Serializable
{

    public CartItem( Product product, int quantity )
    {
        if( quantity <= 0 )
        {
            throw new IllegalArgumentException( "Quantity is less than 1" );
        }
        this.product = product;
        this.quantity = quantity;
    }

    private Product product;
    private int quantity;

    public BigDecimal getSubTotal()
    {
        return product.getPrice().multiply( new BigDecimal( quantity ) );
    }
}

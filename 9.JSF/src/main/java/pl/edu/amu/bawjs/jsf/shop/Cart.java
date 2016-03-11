package pl.edu.amu.bawjs.jsf.shop;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mbocian on 2016-03-11.
 */
@ManagedBean
@SessionScoped
public class Cart
{
    private List<CartItem> items;

    public Cart(){
        items = new ArrayList<>(  );
    }

    public void add( CartItem cartItem )
    {
        items.add( cartItem );
    }

    public BigDecimal getTotal()
    {
        BigDecimal sum = new BigDecimal( 0 );
        for( CartItem item : items )
        {
            sum = sum.add( item.getSubTotal() );
        }
        return sum;
    }
}

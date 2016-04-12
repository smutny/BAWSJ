package pl.edu.amu.bawjs.jsf.shop;

import pl.edu.amu.bawjs.jsf.user.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

    @ManagedProperty(value="#{user}")
    private User user;

    public Cart(){
        items = new ArrayList<>(  );
    }

    public User getUser()
    {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }


    public void addProduct() {
        String action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productName");
        System.out.println(action);
    }
}

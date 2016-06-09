package pl.edu.amu.bawjs.jsf.shop;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by rafal on 6/9/16.
 */
@ManagedBean
@SessionScoped
public class AmountBean implements Serializable {
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

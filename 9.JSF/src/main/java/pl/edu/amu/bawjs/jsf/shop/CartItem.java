package pl.edu.amu.bawjs.jsf.shop;

import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by mbocian on 2016-03-11.
 */
public class CartItem implements Serializable {

    public CartItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity is less than 1");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) { this.quantity -= quantity; }

    public int getQuantity() {
        return quantity;
    }

    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }

    public String getItemPrice() {
        return DecimalFormat.getCurrencyInstance().format(getSubTotal().doubleValue());
    }

    public BigDecimal getSubTotal() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

}

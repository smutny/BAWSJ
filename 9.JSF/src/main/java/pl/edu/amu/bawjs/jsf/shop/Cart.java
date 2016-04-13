package pl.edu.amu.bawjs.jsf.shop;

import pl.edu.amu.bawjs.jsf.controller.ProductController;
import pl.edu.amu.bawjs.jsf.user.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mbocian on 2016-03-11.
 */
@ManagedBean
@SessionScoped
public class Cart implements Serializable {
    private List<CartItem> items;

    @ManagedProperty(value = "#{user}")
    private User user;

    @ManagedProperty(value = "#{productController}")
    private ProductController productController;

    /**
     * Only for JSF
     */
    public ProductController getProductController() {
        return productController;
    }

    /**
     * Only for JSF
     */
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Cart() {
        items = new ArrayList<>();
    }

    /**
     * Only for JSF
     */
    public User getUser() {
        return user;
    }

    public void add(CartItem cartItem) {
        Optional<CartItem> optionalFound = items.stream()
                .filter(ci -> ci.getProduct().equals(cartItem.getProduct()))
                .findFirst();
        if (optionalFound.isPresent()) {
            optionalFound.ifPresent(ci -> ci.addQuantity(cartItem.getQuantity()));
        } else {
            items.add(cartItem);
        }
    }

    public void add(Product product) {
        add(new CartItem(product, 1));
    }

    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal(0);
        for (CartItem item : items) {
            sum = sum.add(item.getSubTotal());
        }
        return sum;
    }

    /**
     * Only for JSF
     */
    public void setUser(User user) {
        this.user = user;
    }


    public void addProduct() {
        String action = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productName");
        Product product = productController.getProducts().stream()
                .filter(p -> p.getName().equals(action))
                .findAny().orElseThrow(() -> new IllegalStateException("Product does not exist."));
        add(product);
    }
}

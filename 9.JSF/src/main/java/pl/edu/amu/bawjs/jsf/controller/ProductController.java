package pl.edu.amu.bawjs.jsf.controller;

import pl.edu.amu.bawjs.jsf.shop.Product;
import pl.edu.amu.bawjs.jsf.user.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bocian on 15.03.2016.
 */
@ManagedBean
@SessionScoped
public class ProductController {


    @ManagedProperty(value="#{user}")
    private User user;


    public ProductController(){

    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Prod1", "Opis produktu nr1", BigDecimal.valueOf(22.22)));
        products.add(new Product("Prod2", "Opis produktu nr2", BigDecimal.valueOf(22.22)));
        products.add(new Product("Prod3", "Opis produktu nr3", BigDecimal.valueOf(22.22)));
        products.add(new Product("Prod4", "Opis produktu nr4", BigDecimal.valueOf(22.22)));

        return products;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

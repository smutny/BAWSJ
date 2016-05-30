package pl.edu.amu.bawjs.jsf.controller;

import pl.edu.amu.bawjs.jsf.services.ProductsService;
import pl.edu.amu.bawjs.jsf.shop.Product;
import pl.edu.amu.bawjs.jsf.user.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bocian on 15.03.2016.
 */
@ManagedBean
@SessionScoped
public class ProductController implements Serializable {
    @ManagedProperty(value="#{user}")
    private User user;
    @Inject
    ProductsService productsService;

    public ProductController(){

    }

    public List<Product> getProducts() {
        return productsService.getAll();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

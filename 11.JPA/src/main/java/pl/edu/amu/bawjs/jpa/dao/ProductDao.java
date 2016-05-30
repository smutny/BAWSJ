package pl.edu.amu.bawjs.jpa.dao;

import pl.edu.amu.bawjs.jpa.model.Product;

import javax.ejb.Stateless;

/**
 * Created by rafal on 5/30/16.
 */
@Stateless
public class ProductDao extends GenericDao<Product> {
    public ProductDao() {
        super(Product.class);
    }
}

package pl.edu.amu.bawjs.jpa.services;

import pl.edu.amu.bawjs.jpa.dao.ProductDao;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rafal on 5/30/16.
 */
@Stateless
public class ProductsService {
    private ProductDao productDao;

    @Inject
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    //    @Inject
//    public void setProductDao(ProductDao productDao) {
//        this.productDao = productDao;
//    }

    public List<Product> getAll() {
        List<Product> products = productDao.findAll();
        return products;
    }


    public Product getById(long id) throws WrongIdException {
        Product product = productDao.findById(id);

        if (product == null) {
            throw new WrongIdException();
        }
        return product;
    }

    public void addProduct(Product product) {
        productDao.create(product);
    }
}

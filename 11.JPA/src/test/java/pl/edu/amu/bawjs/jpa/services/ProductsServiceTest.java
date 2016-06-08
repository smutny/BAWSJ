package pl.edu.amu.bawjs.jpa.services;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.amu.bawjs.jpa.dao.ProductDao;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;


/**
 * Created by rafal on 5/30/16.
 */
public class ProductsServiceTest {
    ProductsService productsService;

    @Before
    public void setUp() {
        ProductDao productDao = Mockito.mock(ProductDao.class);
        productsService = new ProductsService();
        productsService.setProductDao(productDao);
    }

    @Test(expected = WrongIdException.class)
    public void getProductByIdShouldThrowExceptionIfIdIsInvalid() throws WrongIdException {
        productsService.getById(-1);
    }
}
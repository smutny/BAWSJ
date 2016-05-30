package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.dao.ProductDao;
import pl.edu.amu.bawjs.jpa.model.Product;
import pl.edu.amu.bawjs.jpa.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rafal on 5/30/16.
 */
@Path("/products")
public class ProductsController {
    @Inject
    private ProductDao productDao;

    @GET
    @Produces({"application/json"})
    @Transactional
    public Response getProducts() {
        List<Product> products = productDao.findAll();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Transactional
    public Response getProductById(@PathParam("id") long id) {
        Product product = productDao.findById(id);

        if (product == null) {
            return Response.status(404).build();
        }

        return Response.ok(product).build();
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response addProduct(Product product) {
        productDao.create(product);
        return Response.status(201).build();
    }
}

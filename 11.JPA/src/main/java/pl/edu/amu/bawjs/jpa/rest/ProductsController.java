package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Product;
import pl.edu.amu.bawjs.jpa.services.ProductsService;

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
    ProductsService productsService;


    @GET
    @Produces({"application/json"})
    @Transactional
    public Response getProducts() {
        List<Product> products = productsService.getAll();
        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    @Transactional
    public Response getProductById(@PathParam("id") long id) {
        try {
            Product product = productsService.getById(id);
            return Response.ok(product).build();
        } catch (WrongIdException e) {
            return Response.status(404).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Transactional
    public Response addProduct(Product product) {
        productsService.addProduct(product);
        return Response.status(201).build();
    }
}

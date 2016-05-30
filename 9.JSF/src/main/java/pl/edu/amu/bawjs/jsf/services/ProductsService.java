package pl.edu.amu.bawjs.jsf.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pl.edu.amu.bawjs.jsf.shop.Product;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;

/**
 * Created by rafal on 5/30/16.
 */
@Stateless
public class ProductsService {
    private final String SHOP_API_URL = "http://localhost:8080/11.JPA/rest/";
    Gson gson = new Gson();

    public ProductsService() {
    }

    public List<Product> getAll() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(SHOP_API_URL + "products");
        String json = target.request().get(String.class);
        return gson.fromJson(json, new TypeToken<List<Product>>(){}.getType());
    }
}

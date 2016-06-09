package pl.edu.amu.bawjs.jsf.controller;

import com.google.gson.reflect.TypeToken;
import pl.edu.amu.bawjs.jsf.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jsf.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jsf.shop.Cart;
import pl.edu.amu.bawjs.jsf.shop.Checkin;
import pl.edu.amu.bawjs.jsf.shop.Product;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rafal on 6/4/16.
 */
@ManagedBean
@SessionScoped
public class CheckInController {
    private final String SHOP_API_URL = "http://localhost:8080/11.JPA/rest/";
    @ManagedProperty(value = "#{cart}")
    Cart cart;

    public String checkIn() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String cardNumber = externalContext.getRequestParameterMap().get("checkInForm:cardNumber");
        String pin = externalContext.getRequestParameterMap().get("checkInForm:pin");
        String totalString = externalContext.getRequestParameterMap().get("amount");
        System.out.println(cardNumber + ":" + pin);

        Client client = ClientBuilder.newClient();

        Checkin checkin = new Checkin(cardNumber, pin, Double.parseDouble(totalString));

        try {
            makeCheckinRequest(client, checkin);
            System.out.println("Bought!");
            cart.removeAllProducts();
            return "shop.xhtml";
        } catch (NotEnoughFundsException e) {
            String message = "Niewystarczająca ilość środków";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            System.out.println("Not enough funds!");
        } catch (UnauthorizedException e) {
            String message = "Zły numer konta lub pin";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            System.out.println("Unauthorized!");
        }

        return "";
    }

    private void makeCheckinRequest(Client client, Checkin checkin) throws NotEnoughFundsException, UnauthorizedException {
        WebTarget target = client.target(SHOP_API_URL + "checkin");
        Response response = target.request().post(Entity.json(checkin));

        if (response.getStatus() == 401)
            throw new UnauthorizedException();

        if (response.getStatus() == 400)
            throw new NotEnoughFundsException();
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

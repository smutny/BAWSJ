package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Card;
import pl.edu.amu.bawjs.jpa.services.CardsService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by rafal on 6/7/16.
 */
@Path("/cards")
public class CardsController {
    @Inject
    private CardsService cardsService;

    @GET
    @Produces({"application/json"})
    @Transactional
    public Response test() {
        return Response.ok(new Card("222", "1234")).build();
    }

    @POST
    @Path("/account/{accountId}")
    @Consumes("application/json")
    @Transactional
    public Response addCardToAccount(Card card, @PathParam("accountId") long accountId) {
        try {
            cardsService.addCardToAccount(card, accountId);
            return Response.status(201).build();
        } catch (WrongIdException e) {
            return Response.status(404).build();
        }
    }
}

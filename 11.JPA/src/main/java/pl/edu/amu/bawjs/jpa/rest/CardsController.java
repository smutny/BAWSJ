package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jpa.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.services.CardsService;
import pl.edu.amu.bawsj.atmjpa.model.Balance;
import pl.edu.amu.bawsj.atmjpa.model.Card;
import pl.edu.amu.bawsj.atmjpa.model.NewPinCard;
import pl.edu.amu.bawsj.atmjpa.model.Withdraw;

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

    @POST
    @Path("/change-pin")
    @Consumes("application/json")
    @Transactional
    public Response changePin(NewPinCard newPinCard) {
        try {
            cardsService.changePin(newPinCard);
            return Response.status(200).build();
        } catch (UnauthorizedException ex) {
            return Response.status(401).build();
        }
    }

    @POST
    @Path("/withdraw")
    @Consumes("application/json")
    public Response withdraw(Withdraw withdraw) {
        try {
            cardsService.withdraw(withdraw);
            return Response.status(200).build();
        } catch (UnauthorizedException ex) {
            return Response.status(401).build();
        } catch (NotEnoughFundsException ex) {
            return Response.status(400).build();
        }
    }

    @POST
    @Path("/balance")
    @Consumes("application/json")
    @Produces({"application/json"})
    public Response checkBalance(Card card) {
        try {
            Balance balance = cardsService.checkBalance(card);
            return Response.ok(balance).build();
        } catch (UnauthorizedException ex) {
            return Response.status(401).build();
        }
    }
}

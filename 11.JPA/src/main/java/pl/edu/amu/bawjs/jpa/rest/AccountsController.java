package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Amount;
import pl.edu.amu.bawjs.jpa.services.AccountsService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by rafal on 6/7/16.
 */
@Path("/accounts")
public class AccountsController {
    @Inject
    AccountsService accountsService;

    @POST
    @Path("/{accountId}/charge")
    @Consumes("application/json")
    public Response chargeAccount(Amount amount, @PathParam("accountId") long accountId) {
        try {
            accountsService.chargeAccount(accountId, amount.getAmount());
            return Response.ok().build();
        } catch (WrongIdException e) {
            return Response.status(404).build();
        }
    }
}

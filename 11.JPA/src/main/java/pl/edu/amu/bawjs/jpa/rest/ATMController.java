package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughMoneyInATMException;
import pl.edu.amu.bawjs.jpa.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongAmountToWithdrawException;
import pl.edu.amu.bawjs.jpa.model.ATMNoteState;
import pl.edu.amu.bawjs.jpa.model.Withdraw;
import pl.edu.amu.bawjs.jpa.services.atm.ATMService;
import pl.edu.amu.bawsj.atmjpa.model.Note;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/atm")
public class ATMController {
    @Inject
    ATMService atmService;

    @POST
    @Path("/withdraw")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response withdraw(Withdraw withdraw) {
        try {
            List<Note> notesToWithdraw = atmService.withdraw(withdraw);
            return Response.ok(notesToWithdraw).build();
        } catch (NotEnoughMoneyInATMException e) {
            return Response.status(500).build();
        } catch (NotEnoughFundsException e) {
            return Response.status(400).build();
        } catch (WrongAmountToWithdrawException e) {
            return Response.status(406).build();
        } catch (UnauthorizedException e) {
            return Response.status(401).build();
        }
    }

    @GET
    @Path("/notes")
    @Produces("application/json")
    @Transactional
    public Response getNotes() {
        List<ATMNoteState> atmNoteStateList = atmService.getNotes();
        return Response.ok(atmNoteStateList).build();
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    @Transactional
    public Response addNotes(ATMNoteState atmNoteStateToAdd) {
        atmService.addNotes(atmNoteStateToAdd);
        return Response.status(201).build();
    }
}

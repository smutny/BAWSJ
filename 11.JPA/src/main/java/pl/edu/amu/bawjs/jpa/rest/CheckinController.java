package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jpa.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Card;
import pl.edu.amu.bawjs.jpa.model.CheckinMapper;
import pl.edu.amu.bawjs.jpa.services.CheckinService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by rafal on 6/7/16.
 */
@Path("/checkin")
public class CheckinController {
    @Inject
    CheckinService checkinService;

    @POST
    @Consumes("application/json")
    public Response checkIn(CheckinMapper checkin) {
        try {
            checkinService.checkIn(checkin);
            return Response.ok().build();
        } catch (UnauthorizedException e) {
            return Response.status(401).build();
        } catch (WrongIdException e) {
            return Response.status(401).build();
        } catch (NotEnoughFundsException e) {
            return Response.status(400).build();
        }
    }
}

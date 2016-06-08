package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.model.User;
import pl.edu.amu.bawjs.jpa.services.UsersService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UsersController {
    @Inject
    UsersService usersService;

    @GET
    @Path("/users")
    @Produces({"application/json"})
    @Transactional
    public Response getUsers() {
        // lazy loading!
        List<User> users = usersService.getUsers();
        return Response.ok(users).build();
    }

    @POST
    @Path("/users")
    @Consumes("application/json")
    @Produces({"application/json"})
    @Transactional
    public Response newUser(User user) {
        usersService.addUser(user);
        return Response.status(201).build();
    }
}
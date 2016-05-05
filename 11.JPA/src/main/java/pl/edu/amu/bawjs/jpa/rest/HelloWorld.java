package pl.edu.amu.bawjs.jpa.rest;

import pl.edu.amu.bawjs.jpa.dao.UserDao;
import pl.edu.amu.bawjs.jpa.model.User;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Random;

@Path("/")
public class HelloWorld {
    @Inject
    UserDao dao;

    @GET
    @Path("/users")
    @Produces({"application/json"})
    @Transactional
    public Response getUsers() {
        List<User> all = dao.findAll();
        // lazy loading!
        all.forEach(i -> i.getAccounts().size());
        return Response.ok(all).build();
    }


    @GET
    @Path("/users/create")
    @Produces({"application/json"})
    @Transactional
    public Response newUser() {
        User user = new User();
        user.setEmail("email@email.com");
        user.setName(String.valueOf(new Random().nextInt()));

        dao.create(user);
        return Response.ok().build();
    }


}
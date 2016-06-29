package pl.edu.amu.bawjs.jpa.services;

import pl.edu.amu.bawjs.jpa.dao.UserDao;
import pl.edu.amu.bawsj.atmjpa.model.User;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by rafal on 6/7/16.
 */
public class UsersService {
    @Inject
    private UserDao dao;

    @Inject
    private AccountsService accountsService;


    public List<User> getUsers() {
        List<User> users = dao.findAll();
        //Lazyyyyyyyyyyyyyyy
        users.forEach(i ->
                i.getAccounts().forEach(j ->
                        j.getCards().size()));
        return users;
    }

    public void addUser(User user) {
        dao.create(user);
        accountsService.addAccountToUser(user);
    }
}

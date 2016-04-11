package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public interface UsersDatabase {
    List<User> getAllUsers();
    User getUserByLogin(String login);
    void addUser(String login, String pin) throws IOException;

    double getBalance(String userLogin);

    double updatePin();
}

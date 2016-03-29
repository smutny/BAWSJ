package pl.edu.amu.bawsj;

import java.io.IOException;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public interface UsersDatabase {
    List<User> getAllUsers();
    User getUserByLogin(String login);

    void addUser(User user) throws IOException;
}

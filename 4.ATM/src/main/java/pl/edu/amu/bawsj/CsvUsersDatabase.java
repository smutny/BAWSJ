package pl.edu.amu.bawsj;

import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class CsvUsersDatabase implements UsersDatabase {
    FileHandler fileHandler;
    List<User> users;

    public CsvUsersDatabase(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}

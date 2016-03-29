package pl.edu.amu.bawsj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class CsvUsersDatabase implements UsersDatabase {
    private FileHandler fileHandler;
    private List<User> users;
    private AccountsDatabase accountsDatabase;

    public CsvUsersDatabase(FileHandler fileHandler, AccountsDatabase accountsDatabase) {
        if (fileHandler == null)
            throw new IllegalArgumentException();

        this.fileHandler = fileHandler;
        this.accountsDatabase = accountsDatabase;
        users = new ArrayList<User>();
        initalizeUsers();
    }

    private void initalizeUsers() {
        List<String[]> usersList = fileHandler.getData();

        for (String[] userStringArray : usersList) {
            parseUserAndAddToList(userStringArray);
        }
    }

    private void parseUserAndAddToList(String[] userStringArray) {
        users.add(new User(userStringArray[0], userStringArray[1], userStringArray[2], userStringArray[3], userStringArray[4]));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        for (User user : users) {
            if (userHasLogin(user, login))
                return user;
        }
        return null;
    }

    private boolean userHasLogin(User user, String login) {
        return user.getLogin().equals(login);
    }

    @Override
    public void addUser(User user) throws IOException {
        if (getUserByLogin(user.getLogin()) != null)
            throw new RuntimeException();
        users.add(user);
        fileHandler.addNewRow(user.getId()+","+user.getFirstName()+","+ user.getLastName()+","+user.getAccountNumber()+","+user.getLogin());
    }


}

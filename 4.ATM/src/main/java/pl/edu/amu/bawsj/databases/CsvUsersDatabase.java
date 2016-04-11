package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.Account;
import pl.edu.amu.bawsj.domain.User;
import pl.edu.amu.bawsj.exceptions.WrongIdException;
import pl.edu.amu.bawsj.utils.FileHandler;

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

    public CsvUsersDatabase(FileHandler fileHandler, AccountsDatabase accountsDatabase) throws IOException {
        if (fileHandler == null)
            throw new IllegalArgumentException();

        this.fileHandler = fileHandler;
        this.accountsDatabase = accountsDatabase;
        users = new ArrayList<User>();
        initalizeUsers();
    }

    private void initalizeUsers() throws IOException {
        List<String[]> usersList = fileHandler.getData();

        for (String[] userStringArray : usersList) {
            parseUserAndAddToList(userStringArray);
        }
    }

    private void parseUserAndAddToList(String[] userStringArray) {
        User user = new User(userStringArray[0], userStringArray[1], userStringArray[2]);
        users.add(user);
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
    public void addUser(String login, String pin) throws IOException {
        if (getUserByLogin(login) != null)
            throw new RuntimeException();
        Account account = accountsDatabase.addAccount(pin);
        User user = new User(findNextId(), account.getAccountNumber(), login);
        users.add(user);
        fileHandler.addNewRow(user.getId()+","+user.getAccountNumber()+","+user.getLogin());
    }

    private String findNextId() {
        return "1";
    }

    @Override
    public double getBalance(String userLogin) {
        User user = getUserByLogin(userLogin);
        if (user == null)
            throw new WrongIdException();
        Account account = accountsDatabase.getAccountByNumber(user.getAccountNumber());

        return account.getBalance();
    }

    @Override
    public double updatePin() {
        return 0;
    }
}

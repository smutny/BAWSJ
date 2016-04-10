package pl.edu.amu.bawsj;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class CsvUsersDatabaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfHandlerIsNull() throws IOException {
        AccountsDatabase accountsDatabase = Mockito.mock(AccountsDatabase.class);
        CsvUsersDatabase csvUsersDatabase = new CsvUsersDatabase(null, accountsDatabase);
    }

    @Test
    public void getAllUsersShouldReturnUsersCorrectly() throws IOException {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        List<User> users = csvUsersDatabase.getAllUsers();
        User user = users.get(0);
        Assert.assertEquals("0", user.getId());
        Assert.assertEquals("444", user.getAccountNumber());
        Assert.assertEquals("login1", user.getLogin());
        user = users.get(1);
        Assert.assertEquals("555", user.getAccountNumber());
        Assert.assertEquals("1", user.getId());
        Assert.assertEquals("login2", user.getLogin());
    }

    private CsvUsersDatabase prepareCsvUsersDatabase() throws IOException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        List<String[]> data = prepareData();
        Mockito.when(fileHandlerMocked.getData()).thenReturn(data);
        AccountsDatabase accountsDatabase = Mockito.mock(AccountsDatabase.class);
        Mockito.when(accountsDatabase.addAccount("1234")).thenReturn(new Account("12345678", "1234", 0.0));
        return new CsvUsersDatabase(fileHandlerMocked, accountsDatabase);
    }

    private List<String[]> prepareData() {
        List<String[]> data = new ArrayList<String[]>();
        String[] user1 = new String[3];
        String[] user2 = new String[3];
        user1[0] = "0";
        user1[1] = "444";
        user1[2] = "login1";
        user2[0] = "1";
        user2[1] = "555";
        user2[2] = "login2";
        data.add(user1);
        data.add(user2);
        return data;
    }

    @Test
    public void shouldReturnNullWhenUserWithUsernameDoesntExist() throws IOException {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        User user = csvUsersDatabase.getUserByLogin("nonexistingusername");

        Assert.assertEquals(user, null);
    }

    @Test
    public void shouldReturnCorrectUserForExistingUsername() throws IOException {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        User user = csvUsersDatabase.getUserByLogin("login1");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getLogin(), "login1");
        Assert.assertEquals(user.getAccountNumber(), "444");
        Assert.assertEquals(user.getId(), "0");

    }

    @Test
    public void shouldAddNewUser() throws IOException {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        csvUsersDatabase.addUser("login55", "1234");
        User addedUser = csvUsersDatabase.getUserByLogin("login55");
        Assert.assertNotNull(addedUser);
        Assert.assertEquals(addedUser.getLogin(), "login55");
    }

}

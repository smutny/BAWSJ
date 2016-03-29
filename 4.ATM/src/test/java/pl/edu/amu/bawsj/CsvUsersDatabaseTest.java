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
    public void shouldThrowExceptionIfHandlerIsNull() {
        AccountsDatabase accountsDatabase = Mockito.mock(AccountsDatabase.class);
        CsvUsersDatabase csvUsersDatabase = new CsvUsersDatabase(null, accountsDatabase);
    }

    @Test
    public void getAllUsersShouldReturnUsersCorrectly() {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        List<User> users = csvUsersDatabase.getAllUsers();
        User user = users.get(0);
        Assert.assertEquals("0", user.getId());
        Assert.assertEquals("jan", user.getFirstName());
        Assert.assertEquals("kowalski", user.getLastName());
        Assert.assertEquals("444", user.getAccountNumber());
        Assert.assertEquals("login1", user.getLogin());
        user = users.get(1);
        Assert.assertEquals("Mirek", user.getFirstName());
        Assert.assertEquals("Nowak", user.getLastName());
        Assert.assertEquals("555", user.getAccountNumber());
        Assert.assertEquals("1", user.getId());
        Assert.assertEquals("login2", user.getLogin());
    }

    private CsvUsersDatabase prepareCsvUsersDatabase() {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        List<String[]> data = prepareData();
        Mockito.when(fileHandlerMocked.getData()).thenReturn(data);
        AccountsDatabase accountsDatabase = Mockito.mock(AccountsDatabase.class);
        return new CsvUsersDatabase(fileHandlerMocked, accountsDatabase);
    }

    private List<String[]> prepareData() {
        List<String[]> data = new ArrayList<String[]>();
        String[] user1 = new String[5];
        String[] user2 = new String[5];
        user1[0] = "0";
        user1[1] = "jan";
        user1[2] = "kowalski";
        user1[3] = "444";
        user1[4] = "login1";
        user2[0] = "1";
        user2[1] = "Mirek";
        user2[2] = "Nowak";
        user2[3] = "555";
        user2[4] = "login2";
        data.add(user1);
        data.add(user2);
        return data;
    }

    @Test
    public void shouldReturnNullWhenUserWithUsernameDoesntExist() {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        User user = csvUsersDatabase.getUserByLogin("nonexistingusername");

        Assert.assertEquals(user, null);
    }

    @Test
    public void shouldReturnCorrectUserForExistingUsername() {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        User user = csvUsersDatabase.getUserByLogin("login1");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getLogin(), "login1");
        Assert.assertEquals(user.getFirstName(), "jan");
        Assert.assertEquals(user.getLastName(), "kowalski");
        Assert.assertEquals(user.getAccountNumber(), "444");
        Assert.assertEquals(user.getId(), "0");

    }

    @Test
    public void shouldAddNewUser() throws IOException {
        CsvUsersDatabase csvUsersDatabase = prepareCsvUsersDatabase();
        User user = new User ("3", "firstName", "lastName", "4124124", "login55");
        csvUsersDatabase.addUser(user);
        User addedUser = csvUsersDatabase.getUserByLogin("login55");
        Assert.assertEquals(addedUser.getId(), "3");
        Assert.assertEquals(addedUser.getFirstName(), "firstName");
        Assert.assertEquals(addedUser.getLastName(), "lastName");
        Assert.assertEquals(addedUser.getAccountNumber(), "4124124");
        Assert.assertEquals(addedUser.getLogin(), "login55");
    }

}

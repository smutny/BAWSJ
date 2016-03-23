package pl.edu.amu.bawsj;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class CsvUsersDatabaseTest {
    @Test
    public void getAllUsersShouldReturnUsersCorrectly() {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        CsvUsersDatabase csvUsersDatabase = new CsvUsersDatabase(fileHandlerMocked);
        List<User> users = csvUsersDatabase.getAllUsers();
        User user = users.get(0);
        Assert.assertEquals("test", user.getFirstName());
    }
}

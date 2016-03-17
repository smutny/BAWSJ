package pl.edu.amu.bawsj;

import org.junit.Test;

/**
 * Created by rafal on 3/17/16.
 */
public class CsvUsersDatabaseTests {
    @Test
    public void getAllUsersShouldReturnUsersCorrectly() {
        CsvUsersDatabase csvUsersDatabase = new CsvUsersDatabase();
        csvUsersDatabase.getAllUsers();
    }
}

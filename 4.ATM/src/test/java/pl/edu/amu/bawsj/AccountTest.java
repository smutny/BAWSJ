package pl.edu.amu.bawsj;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.amu.bawsj.databases.CsvAccountsDatabase;
import pl.edu.amu.bawsj.domain.Account;
import pl.edu.amu.bawsj.utils.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class AccountTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfHandlerIsNull() throws IOException {
        CsvAccountsDatabase csvAccountsDatabase = new CsvAccountsDatabase(null);
    }

    @Test
    public void getAllAccountsShouldReturnAccountsCorrectly() throws IOException {
        CsvAccountsDatabase csvAccountsDatabase = prepareCsvAccountsDatabase();
        List<Account> accounts = csvAccountsDatabase.getAllAccounts();
        Account account = accounts.get(0);
        Assert.assertEquals("123123123", account.getAccountNumber());
        Assert.assertEquals("1234", account.getPin());
        Assert.assertEquals(55, account.getBalance(), 0.002);
        account = accounts.get(1);
        Assert.assertEquals("2123123", account.getAccountNumber());
        Assert.assertEquals("5678", account.getPin());
        Assert.assertEquals(333, account.getBalance(), 0.002);
    }

    private CsvAccountsDatabase prepareCsvAccountsDatabase() throws IOException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        List<String[]> data = prepareData();
        Mockito.when(fileHandlerMocked.getData()).thenReturn(data);
        return new CsvAccountsDatabase(fileHandlerMocked);
    }

    private List<String[]> prepareData() {
        List<String[]> data = new ArrayList<String[]>();
        String[] account1 = new String[3];
        String[] account2 = new String[3];
        account1[0] = "123123123";
        account1[1] = "1234";
        account1[2] = "55";
        account2[0] = "2123123";
        account2[1] = "5678";
        account2[2] = "333";
        data.add(account1);
        data.add(account2);
        return data;
    }

    @Test
    public void shouldReturnNullWhenAccountWithGivenNumberDoesntExist() throws IOException {
        CsvAccountsDatabase csvAccountsDatabase = prepareCsvAccountsDatabase();
        Account account = csvAccountsDatabase.getAccountByNumber("non-existing-number");

        Assert.assertEquals(account, null);
    }

    @Test
    public void shouldReturnCorrectAccountForExistingAccountNumber() throws IOException {
        CsvAccountsDatabase csvAccountsDatabase = prepareCsvAccountsDatabase();
        Account account = csvAccountsDatabase.getAccountByNumber("2123123");
        Assert.assertNotNull(account);
        Assert.assertEquals("5678", account.getPin());
        Assert.assertEquals(333, account.getBalance(), 0.002);
    }

    @Test
    public void shouldAddNewAccountCorrectly() throws IOException {
        CsvAccountsDatabase csvAccountsDatabase = prepareCsvAccountsDatabase();
        Account account = csvAccountsDatabase.addAccount("1234");
        Assert.assertNotNull(account);
    }

}
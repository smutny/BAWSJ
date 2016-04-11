package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.Account;

import java.io.IOException;
import java.util.List;

/**
 * Created by rafal on 3/29/16.
 */
public interface AccountsDatabase {
    List<Account> getAllAccounts();

    Account addAccount(String pin) throws IOException;

    Account getAccountByNumber(String number);
}

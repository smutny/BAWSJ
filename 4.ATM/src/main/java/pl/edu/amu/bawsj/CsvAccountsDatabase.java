package pl.edu.amu.bawsj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rafal on 3/29/16.
 */
public class CsvAccountsDatabase implements AccountsDatabase {
    FileHandler fileHandler;
    List<Account> accounts;
    public CsvAccountsDatabase(FileHandler fileHandler) throws IOException {
        if (fileHandler == null)
            throw new IllegalArgumentException();

        this.fileHandler = fileHandler;
        accounts = new ArrayList<Account>();
        initalizeAccounts();
    }

    private void initalizeAccounts() throws IOException {
        List<String[]> accountsList = fileHandler.getData();

        for (String[] accountStringArray : accountsList) {
            parseAccountAndAddToList(accountStringArray);
        }
    }

    private void parseAccountAndAddToList(String[] accountStringArray) {
        accounts.add(new Account(accountStringArray[0], accountStringArray[1], Double.parseDouble(accountStringArray[2])));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account addAccount(String pin) throws IOException {
        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, pin, 0.0);
        fileHandler.addNewRow(account.getAccountNumber()+","+account.getPin()+","+account.getBalance());
        return account;
    }

    private String generateAccountNumber() {
        String accountNumber;
        do {
            char[] chars = "1234567890".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            accountNumber = sb.toString();
        } while (getAccountByNumber(accountNumber) != null);

        return accountNumber;
    }

    @Override
    public Account getAccountByNumber(String number) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == number)
                return account;
        }
        return null;
    }
}

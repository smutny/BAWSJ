package pl.edu.amu.bawsj;

/**
 * Created by rafal on 3/17/16.
 */
public class Account {
    private String accountNumber;
    private String pin;
    private Double balance;

    public Account(String accountNumber, String pin, Double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public Double getBalance() {
        return balance;
    }
}

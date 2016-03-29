package pl.edu.amu.bawsj;

/**
 * Created by rafal on 3/17/16.
 */
public class User {
    private String id;
    private String accountNumber;
    private String login;

    public User(String id, String accountNumber, String login) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

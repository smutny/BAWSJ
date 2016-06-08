package pl.edu.amu.bawjs.jpa.services;

import pl.edu.amu.bawjs.jpa.dao.AccountDao;
import pl.edu.amu.bawjs.jpa.dao.UserDao;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Account;
import pl.edu.amu.bawjs.jpa.model.Amount;
import pl.edu.amu.bawjs.jpa.model.Card;
import pl.edu.amu.bawjs.jpa.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by rafal on 6/7/16.
 */
@Stateless
public class AccountsService {
    @Inject
    AccountDao accountDao;
    @Inject
    UserDao userDao;

    public void addAccountToUser(User user) {
        List<Account> userAccounts = user.getAccounts();
        userAccounts.add(createNewAccount(user));
        user.setAccounts(userAccounts);
        userDao.update(user);
    }

    private Account createNewAccount(User user) {
        Account account = new Account(user, 0.0);
        accountDao.create(account);
        return account;
    }

    public Account getAccountById(long id) {
        return accountDao.findById(id);
    }

    public void addCardToAccount(Account account, Card card) {
        List<Card> accountCards = account.getCards();
        accountCards.add(card);
        account.setCards(accountCards);
        update(account);
    }

    public void chargeAccount(long accountId, double amount) throws WrongIdException {
        Account account = getAccountById(accountId);
        if (account == null)
            throw new WrongIdException();

        account.setBalance(account.getBalance() + amount);
        update(account);
    }

    public void update(Account account) {
        accountDao.update(account);
    }
}

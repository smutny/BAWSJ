package pl.edu.amu.bawjs.jpa.dao;

import pl.edu.amu.bawjs.jpa.model.Account;

import javax.ejb.Stateless;

/**
 * Created by rafal on 6/7/16.
 */
@Stateless
public class AccountDao extends GenericDao<Account> {
    public AccountDao() {
        super(Account.class);
    }
}

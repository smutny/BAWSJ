package pl.edu.amu.bawjs.jpa.services;

import pl.edu.amu.bawjs.jpa.dao.CardDao;
import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jpa.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawsj.atmjpa.model.*;

import javax.inject.Inject;
import java.security.SecureRandom;

/**
 * Created by rafal on 6/7/16.
 */
public class CardsService {
    @Inject
    private CardDao cardDao;
    @Inject
    private AccountsService accountsService;

    static final String AB = "0123456789";
    static SecureRandom rnd = new SecureRandom();

    public void addCardToAccount(Card card, long accountId) throws WrongIdException {

        Account account = accountsService.getAccountById(accountId);
        if (account == null)
            throw new WrongIdException();

        Card cardToAdd = new Card(generateNumber(), card.getPin());
        cardToAdd.setAccount(account);
        cardDao.create(cardToAdd);

        accountsService.addCardToAccount(account, cardToAdd);
    }

    private String generateNumber() {
        return randomString(16);
    }

    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public Card findCardByNumber(String cardNumber) {
        return cardDao.
                findAll().
                stream().
                filter(card -> card.getNumber().equals(cardNumber)).
                findFirst().
                orElse(null);
    }

    public void changePin(NewPinCard newPinCard) throws UnauthorizedException {
        Card card = findCardByNumber(newPinCard.getNumber());

        if (card == null)
            throw new UnauthorizedException();

        if (!card.getPin().equals(newPinCard.getPin()))
            throw new UnauthorizedException();

        card.setPin(newPinCard.getNewPin());
        cardDao.update(card);
    }

    public void withdraw(Withdraw withdraw) throws UnauthorizedException, NotEnoughFundsException {
        Card card = findCardByNumber(withdraw.getNumber());

        if (card == null)
            throw new UnauthorizedException();

        if (!card.getPin().equals(withdraw.getPin()))
            throw new UnauthorizedException();

        Account account = card.getAccount();

        if (account.getBalance() < withdraw.getAmount())
            throw new NotEnoughFundsException();

        account.setBalance(account.getBalance() - withdraw.getAmount());
        accountsService.update(account);
    }

    public Balance checkBalance(Card cardToCheck) throws UnauthorizedException {
        Card card = findCardByNumber(cardToCheck.getNumber());

        if (card == null)
            throw new UnauthorizedException();

        if (!card.getPin().equals(cardToCheck.getPin()))
            throw new UnauthorizedException();

        Balance balance = new Balance();
        balance.setAmount(card.getAccount().getBalance());
        return balance;
    }
}

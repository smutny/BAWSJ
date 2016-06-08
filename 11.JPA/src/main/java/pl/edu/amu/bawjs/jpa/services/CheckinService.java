package pl.edu.amu.bawjs.jpa.services;

import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jpa.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongIdException;
import pl.edu.amu.bawjs.jpa.model.Account;
import pl.edu.amu.bawjs.jpa.model.Card;
import pl.edu.amu.bawjs.jpa.model.CheckinMapper;

import javax.inject.Inject;

/**
 * Created by rafal on 6/7/16.
 */
public class CheckinService {
    @Inject
    CardsService cardsService;
    @Inject
    AccountsService accountsService;

    public void checkIn(CheckinMapper checkin) throws UnauthorizedException, WrongIdException, NotEnoughFundsException {
        Card card = cardsService.findCardByNumber(checkin.getCardNumber());

        if (card == null)
            throw new WrongIdException();

        if (card.getPin() != checkin.getPin())
            throw new UnauthorizedException();

        Account account = card.getAccount();

        if (account.getBalance() < checkin.getAmount())
            throw new NotEnoughFundsException();

        account.setBalance(account.getBalance() - checkin.getAmount());
        accountsService.update(account);
    }
}

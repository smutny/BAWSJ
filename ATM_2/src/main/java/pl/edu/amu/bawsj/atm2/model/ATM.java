package pl.edu.amu.bawsj.atm2.model;

import pl.edu.amu.bawsj.atm2.model.exception.*;
import pl.edu.amu.bawsj.atm2.model.manager.AccountManager;
import pl.edu.amu.bawsj.atm2.model.manager.CardManager;
import pl.edu.amu.bawsj.atmjpa.model.Card2;
import pl.edu.amu.bawsj.atmjpa.model.Note;
import pl.edu.amu.bawsj.atmjpa.model.PIN;

import java.math.BigDecimal;
import java.util.List;

public enum ATM {
    INSTANCE;

    private AccountManager accountManager = new AccountManager();
    private CardManager cardManager = new CardManager();
    private Card2 insertedCard;

    public void insertCard(Card2 card) throws UnauthorizedException, UnknownResponseException {
        accountManager.checkMoneyInAccount(card);
        insertedCard = card;
    }

    public void removeCard() {
        insertedCard = null;
    }

    public PIN changePIN(PIN newPIN) throws NoCardException, UnauthorizedException, UnknownResponseException {
        if (insertedCard == null) {
            throw new NoCardException();
        }
        return cardManager.changeCardPIN(insertedCard, newPIN);

    }

    public BigDecimal checkMoneyInAccount() throws NoCardException, UnauthorizedException, UnknownResponseException {
        if (insertedCard == null) {
            throw new NoCardException();
        }
        return accountManager.checkMoneyInAccount(insertedCard);
    }

    public List<Note> withdrawMoneyFromAccount(int money) throws NoCardException, UnauthorizedException, UnknownResponseException,
            NotEnoughMoneyInATMException, NotEnoughMoneyInAccountException, WrongAmountToWithdrawException {
        if (insertedCard == null) {
            throw new NoCardException();
        }
        return accountManager.withdrawMoneyFromAccount(insertedCard, money);
    }

    public BigDecimal payMoneyIntoAccount(List<Note> money) throws NoCardException, UnknownResponseException, UnauthorizedException {
        if (insertedCard == null) {
            throw new NoCardException();
        }
        return accountManager.payMoneyIntoAccount(insertedCard, money);
    }
}

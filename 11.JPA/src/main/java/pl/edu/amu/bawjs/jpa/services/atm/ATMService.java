package pl.edu.amu.bawjs.jpa.services.atm;

import pl.edu.amu.bawjs.jpa.dao.ATMNoteStateDao;
import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughFundsException;
import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughMoneyInATMException;
import pl.edu.amu.bawjs.jpa.exceptions.UnauthorizedException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongAmountToWithdrawException;
import pl.edu.amu.bawjs.jpa.model.ATMNoteState;
import pl.edu.amu.bawjs.jpa.model.Account;
import pl.edu.amu.bawjs.jpa.model.Card;
import pl.edu.amu.bawjs.jpa.model.Withdraw;
import pl.edu.amu.bawjs.jpa.services.AccountsService;
import pl.edu.amu.bawjs.jpa.services.CardsService;
import pl.edu.amu.bawjs.jpa.services.atm.strategy.DefaultWithdrawingStrategy;
import pl.edu.amu.bawjs.jpa.services.atm.strategy.WithdrawingStrategy;
import pl.edu.amu.bawsj.atmjpa.model.Note;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Stateless
public class ATMService {
    @Inject
    private ATMNoteStateDao atmNoteStateDao;
    @Inject
    private CardsService cardsService;
    @Inject
    private AccountsService accountsService;

    public List<Note> withdraw(Withdraw withdraw) throws NotEnoughFundsException, UnauthorizedException, NotEnoughMoneyInATMException, WrongAmountToWithdrawException {
        Card card = cardsService.findCardByNumber(withdraw.getNumber());
        checkCard(card, withdraw.getPin());

        Account account = card.getAccount();
        checkFundsOnAccount(account, withdraw.getAmount());

        List<ATMNoteState> atmNoteStateList = atmNoteStateDao.findAll();
        WithdrawingStrategy withdrawingStrategy = new DefaultWithdrawingStrategy(atmNoteStateList);
        List<Note> noteList = withdrawingStrategy.withdrawMoney(withdraw.getAmount());
        Map<Note, Integer> notesAfterWithdrawing = withdrawingStrategy.getNotesAfterWithdrawing();

        for (Note note : notesAfterWithdrawing.keySet()) {
            atmNoteStateDao.update(new ATMNoteState(note, notesAfterWithdrawing.get(note)));
        }
        account.setBalance(account.getBalance() - withdraw.getAmount());
        accountsService.update(account);

        return noteList;
    }

    public List<ATMNoteState> getNotes() {
        return atmNoteStateDao.findAll();
    }

    public void addNotes(ATMNoteState atmNoteStateToAdd) {
        List<ATMNoteState> atmNoteStateList = getNotes();

        Optional<ATMNoteState> atmNoteStateOptional = atmNoteStateList.stream()
                .filter(a -> a.getNoteId() == atmNoteStateToAdd.getNoteId()).findFirst();
        if (!atmNoteStateOptional.isPresent()) {
            atmNoteStateDao.create(atmNoteStateToAdd);
        } else {
            ATMNoteState atmNoteState = atmNoteStateOptional.get();
            atmNoteState.setQuantity(atmNoteState.getQuantity() + atmNoteStateToAdd.getQuantity());
            atmNoteStateDao.update(atmNoteState);
        }
    }

    private void checkCard(Card card, String pin) throws UnauthorizedException {
        if (card == null) {
            throw new UnauthorizedException();
        }
        if (!card.getPin().equals(pin)) {
            throw new UnauthorizedException();
        }
    }

    private void checkFundsOnAccount(Account account, int amount) throws NotEnoughFundsException {
        if (account.getBalance() < amount) {
            throw new NotEnoughFundsException();
        }
    }
}

package pl.edu.amu.bawsj;

import pl.edu.amu.bawsj.databases.CardDatabase;
import pl.edu.amu.bawsj.databases.NotesDatabase;
import pl.edu.amu.bawsj.domain.Card;
import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.exceptions.*;
import pl.edu.amu.bawsj.utils.DefaultWithdrawingStrategy;
import pl.edu.amu.bawsj.utils.WithdrawingStrategy;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public class Atm {
    private Card currentCard;
    private CardDatabase cardDatabase;
    private NotesDatabase notesDatabase;
    private WithdrawingStrategy withdrawingStrategy;

    public Atm(CardDatabase cardDatabase, NotesDatabase notesDatabase) {
        this.cardDatabase = cardDatabase;
        this.notesDatabase = notesDatabase;
        withdrawingStrategy = new DefaultWithdrawingStrategy(notesDatabase);
    }

    public void setWithdrawingStrategy(WithdrawingStrategy withdrawingStrategy) {
        this.withdrawingStrategy = withdrawingStrategy;
    }

    public void login(String cardNumber, int pin) throws IOException, ParseException, WrongPinException, AnotherCardInsertedException {
        if (currentCard != null)
            throw new AnotherCardInsertedException();

        currentCard = authorizeCardAndReturnCardObject(cardNumber, pin);
    }

    private Card authorizeCardAndReturnCardObject(String cardNumber, int pin) throws IOException, ParseException, WrongPinException {
        Card card = cardDatabase.findCardByNumber(cardNumber);
        if (card.getPin() == pin)
            return card;

        throw new WrongPinException();
    }

    public List<Note> withdrawMoney(int amount) throws NotEnoughMoneyException, IOException, ParseException, NotEnoughNotesException {
        if (currentCard.getCashAmount() < amount)
            throw new NotEnoughMoneyException();

        List<Note> notes = withdrawingStrategy.withdrawMoney(amount);
        cardDatabase.substractMoneyFromCard(currentCard, amount);
        return notes;
    }

    public void depositMoney(List<Note> notes) throws IOException, ParseException {
        double summedAmount = countAmount(notes);
        cardDatabase.addMoneyToCard(currentCard, summedAmount);
        addNotesToDatabase(notes);
    }

    private void addNotesToDatabase(List<Note> notes) throws ParseException, IOException {
        for (Note note : notes)
            notesDatabase.addNotes(note.getValue(), 1);
    }

    private double countAmount(List<Note> notes) {
        double sum = 0;
        for (Note note : notes)
            sum += note.getValue();
        return sum;
    }

    public void logout() {
        currentCard = null;
    }

    public boolean isLoggedIn() {
        return currentCard != null;
    }

    public double getBalance() throws NotLoggedInException {
        if (!isLoggedIn())
            throw new NotLoggedInException();

        return currentCard.getCashAmount();
    }
}

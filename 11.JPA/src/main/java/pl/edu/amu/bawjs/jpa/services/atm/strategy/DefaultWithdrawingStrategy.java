package pl.edu.amu.bawjs.jpa.services.atm.strategy;

import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughMoneyInATMException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongAmountToWithdrawException;
import pl.edu.amu.bawjs.jpa.model.ATMNoteState;
import pl.edu.amu.bawsj.atmjpa.model.Note;

import java.util.*;

public class DefaultWithdrawingStrategy implements WithdrawingStrategy {
    private Map<Note, Integer> notesInDB = new TreeMap<>(Comparator.reverseOrder());

    public DefaultWithdrawingStrategy(Map<Note, Integer> notesInDB) {
        this.notesInDB.putAll(notesInDB);
    }

    public DefaultWithdrawingStrategy(List<ATMNoteState> notesInDB) {
        for (ATMNoteState atmNoteState : notesInDB) {
            this.notesInDB.put(atmNoteState.getNoteId(), atmNoteState.getQuantity());
        }
    }

    @Override
    public List<Note> withdrawMoney(int amount) throws WrongAmountToWithdrawException, NotEnoughMoneyInATMException {
        if (amount <= 0 || amount % 10 != 0) {
            throw new WrongAmountToWithdrawException();
        }
        List<Note> notes = new ArrayList<>();
        while (amount > 0) {
            Note note = getTheBiggestAvailableNote(amount);
            notes.add(note);
            notesInDB.put(note, notesInDB.get(note) - 1);
            amount -= note.getValue();
        }
        return notes;
    }

    @Override
    public Map<Note, Integer> getNotesAfterWithdrawing() {
        return notesInDB;
    }

    private Note getTheBiggestAvailableNote(int moneyToWithdraw) throws NotEnoughMoneyInATMException {
        for (Map.Entry<Note, Integer> entry : notesInDB.entrySet()) {
            if (entry.getValue() > 0 && entry.getKey().getValue() <= moneyToWithdraw) {
                return entry.getKey();
            }
        }
        throw new NotEnoughMoneyInATMException();
    }
}

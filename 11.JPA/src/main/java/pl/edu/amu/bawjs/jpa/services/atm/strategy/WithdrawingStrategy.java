package pl.edu.amu.bawjs.jpa.services.atm.strategy;

import pl.edu.amu.bawjs.jpa.exceptions.NotEnoughMoneyInATMException;
import pl.edu.amu.bawjs.jpa.exceptions.WrongAmountToWithdrawException;
import pl.edu.amu.bawsj.atmjpa.model.Note;

import java.util.List;
import java.util.Map;

public interface WithdrawingStrategy {
    List<Note> withdrawMoney(int amount) throws WrongAmountToWithdrawException, NotEnoughMoneyInATMException;

    Map<Note, Integer> getNotesAfterWithdrawing();
}

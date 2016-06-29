package pl.edu.amu.bawsj.atm2.presenter;

import pl.edu.amu.bawsj.atm2.model.ATM;
import pl.edu.amu.bawsj.atm2.model.exception.*;
import pl.edu.amu.bawsj.atm2.view.ATMMainView;
import pl.edu.amu.bawsj.atmjpa.model.Card2;
import pl.edu.amu.bawsj.atmjpa.model.Note;
import pl.edu.amu.bawsj.atmjpa.model.PIN;
import pl.edu.amu.bawsj.atmjpa.model.exception.IncorrectCardNumberException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMMainPresenter {
    private ATMMainView mainView;
    private ATM atm = ATM.INSTANCE;

    public void withdrawMoney(int money) {
        try {
            List<Note> withdrawnNotes = atm.withdrawMoneyFromAccount(money);
            mainView.updateAccountBalanceInfo(atm.checkMoneyInAccount());
            mainView.showDialog("Wypłacono banknoty: " + parseNoteListToString(withdrawnNotes));
        } catch (NotEnoughMoneyInAccountException e) {
            mainView.showDialog("Za mało środków na koncie");
        } catch (NotEnoughMoneyInATMException e) {
            mainView.showDialog("Brak banknotów do wypłaty");
        } catch (NoCardException e) {
            mainView.showDialog("Nie włożono karty");
        } catch (WrongAmountToWithdrawException e) {
            mainView.showDialog("Kwota do wypłacenia jest nieprawidłowa");
        } catch (UnauthorizedException e) {
            mainView.showDialog("Autoryzacja karty nie powiodła się");
        } catch (UnknownResponseException e) {
            mainView.showDialog("Nieznana odpowiedź od ATM. Operacja nie powiadła się");
        }
    }

    public void changePIN(PIN newPIN) {
        try {
            atm.changePIN(newPIN);
        } catch (NoCardException e) {
            mainView.showDialog("Nie włożono poprawnej karty");
        } catch (UnauthorizedException e) {
            mainView.showDialog("Autoryzacja karty nie powiodła się");
        } catch (UnknownResponseException e) {
            mainView.showDialog("Nieznana odpowiedź od ATM. Operacja nie powiadła się");
        }
    }

    public void removeCard() {
        atm.removeCard();
        mainView.showDialog("Poprawnie wyjęto kartę");
        mainView.removeAccountBalanceInfo();
    }

    public void insertCard(String cardNumber, PIN pin) {
        try {
            atm.insertCard(new Card2(cardNumber, pin));
            mainView.updateAccountBalanceInfo(atm.checkMoneyInAccount());
        } catch (IncorrectCardNumberException e) {
            mainView.showDialog("Podana karta jest nieprawidłowa");
        } catch (NoCardException e) {
            mainView.showDialog("Nie włożono poprawnej karty");
        } catch (UnauthorizedException e) {
            mainView.showDialog("Autoryzacja karty nie powiodła się");
        } catch (UnknownResponseException e) {
            mainView.showDialog("Nieznana odpowiedź od ATM. Operacja nie powiadła się");
        }
    }

    public void depositMoney(String[] strings) {
        try {
            List<Note> notes = makeNoteList(strings);
            mainView.updateAccountBalanceInfo(atm.payMoneyIntoAccount(notes));
        } catch (NumberFormatException e) {
            mainView.showDialog("Podano nieprawidłową liczbę banknotów");
        } catch (NoCardException e) {
            mainView.showDialog("Nie włożono poprawnej karty");
        } catch (UnauthorizedException e) {
            mainView.showDialog("Autoryzacja karty nie powiodła się");
        } catch (UnknownResponseException e) {
            mainView.showDialog("Nieznana odpowiedź od ATM. Operacja nie powiadła się");
        }
    }

    private String parseNoteListToString(List<Note> withdrawedNotes) {
        String line = "";
        for (Note note : withdrawedNotes) {
            line += "\n" + note.getValue() + " zł";
        }
        return line;
    }

    private List<Note> makeNoteList(String[] strings) {
        int[] howManyNotes = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            howManyNotes[i] = Integer.parseInt(strings[i]);
        }

        Map<Integer, Note> valueToNoteMap = initializeMap();
        List<Note> notes = new ArrayList<>();

        for (int i = 0; i < howManyNotes.length; i++) {
            for (int j = 0; j < howManyNotes[i]; j++) {
                notes.add(valueToNoteMap.get(i));
            }
        }
        return notes;
    }

    private Map<Integer, Note> initializeMap() {
        Map<Integer, Note> valueToNoteMap = new HashMap<>();
        valueToNoteMap.put(0, Note.ZL_10);
        valueToNoteMap.put(1, Note.ZL_20);
        valueToNoteMap.put(2, Note.ZL_50);
        valueToNoteMap.put(3, Note.ZL_100);
        valueToNoteMap.put(4, Note.ZL_200);
        return valueToNoteMap;
    }

    public void setMainView(ATMMainView mainView) {
        this.mainView = mainView;
    }
}

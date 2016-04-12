package pl.edu.amu.bawsj.javafx.presenters;

import pl.edu.amu.bawsj.Atm;
import pl.edu.amu.bawsj.domain.Note;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Rafa³ on 2016-04-12.
 */
public class DepositPresenter {
    Atm atm;

    public DepositPresenter(Atm atm) {
        this.atm = atm;
    }

    public void depositMoney(String ten, String twenty, String fifty, String hundred, String twohundred) throws IOException, ParseException {
        List<Note> notes = new ArrayList<>();
        if (isParsableToInt(ten))
            for (int i = 0; i < Integer.parseInt(ten); i++) {
                notes.add(new Note(10));
            }

        if (isParsableToInt(twenty))
            for (int i = 0; i < Integer.parseInt(twenty); i++) {
                notes.add(new Note(20));
            }

        if (isParsableToInt(fifty))
            for (int i = 0; i < Integer.parseInt(fifty); i++) {
                notes.add(new Note(50));
            }

        if (isParsableToInt(hundred))
            for (int i = 0; i < Integer.parseInt(hundred); i++) {
                notes.add(new Note(100));
            }

        if (isParsableToInt(twohundred))
            for (int i = 0; i < Integer.parseInt(twohundred); i++) {
                notes.add(new Note(200));
            }

        atm.depositMoney(notes);
    }

    private boolean isParsableToInt(String stringToCheck) {
        String intPattern = "[0-9]{1,13}";
        return Pattern.matches(intPattern, stringToCheck);
    }
}

package pl.edu.amu.bawsj.utils;

import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.exceptions.NotEnoughNotesException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by rafal on 3/17/16.
 */
public interface WithdrawingStrategy {
    List<Note> withdrawMoney(int amount) throws IOException, ParseException, NotEnoughNotesException;
}

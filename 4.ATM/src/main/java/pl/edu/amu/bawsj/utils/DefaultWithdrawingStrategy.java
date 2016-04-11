package pl.edu.amu.bawsj.utils;

import pl.edu.amu.bawsj.databases.NotesDatabase;
import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.exceptions.NotEnoughNotesException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rafal on 3/17/16.
 */
public class DefaultWithdrawingStrategy implements WithdrawingStrategy {

    private NotesDatabase notesDatabase;

    public DefaultWithdrawingStrategy(NotesDatabase notesDatabase) {
        this.notesDatabase = notesDatabase;
    }

    @Override
    public List<Note> withdrawMoney(int amount) throws IOException, ParseException, NotEnoughNotesException {
        Map<Integer, List<Note>> notes = notesDatabase.getAllNotes();
        List<Note> resultNotes = new ArrayList<>();
        while (!notes.isEmpty()) {
            int max = findMax(notes);
            for (Note note : notes.get(max)) {
                if (note.getValue() <= amount) {
                    amount -= note.getValue();
                    resultNotes.add(note);
                }
            }
            notes.remove(max);
        }

        if (amount != 0)
            throw new NotEnoughNotesException();

        for (Note note : resultNotes) {
            notesDatabase.substractNote(note.getValue());
        }
        return resultNotes;
    }

    private int findMax(Map<Integer, List<Note>> notes) {
        int max=0;
        for (int i : notes.keySet()) {
            if (i > max)
                max = i;
        }
        return max;
    }
}

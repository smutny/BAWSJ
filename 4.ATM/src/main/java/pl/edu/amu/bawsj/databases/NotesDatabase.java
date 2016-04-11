package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.Note;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by rafal on 4/8/16.
 */
public interface NotesDatabase {

    Map<Integer, List<Note>> getAllNotes() throws ParseException, IOException;
    void addNotes(int noteValue, int amount) throws ParseException, IOException;
    void substractNote(int noteValue) throws IOException, ParseException;
}

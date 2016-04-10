package pl.edu.amu.bawsj;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by rafal on 4/8/16.
 */
public class CsvNotesDatabase implements NotesDatabase {
    FileHandler fileHandler;

    public CsvNotesDatabase(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public Map<Integer, List<Note>> getAllNotes() throws ParseException, IOException {
        Map<Integer, List<Note>> notes = new HashMap<>();
        List<String[]> data = fileHandler.getData();
        for (String[] noteData : data) {
            parseNoteDataAndAddToNotes(noteData, notes);
        }
        return notes;
    }

    private void parseNoteDataAndAddToNotes(String[] noteData, Map<Integer, List<Note>> notes) throws ParseException {
        if (noteDataCanBeParsedToNote(noteData)) {
            notes.put(Integer.parseInt(noteData[0]), createAndReturnGivenNumberOfNotes(noteData[0], noteData[1]));
        } else {
            throw new ParseException("Wrong input file", 0);
        }
    }

    private List<Note> createAndReturnGivenNumberOfNotes(String noteValue, String numberOfNotes) {
        int value = Integer.parseInt(noteValue);
        int quantity = Integer.parseInt(numberOfNotes);
        List<Note> notes = new ArrayList<>();
        for (int i=0; i<quantity; i++) {
            notes.add(new Note(value));
        }
        return notes;
    }

    private boolean noteDataCanBeParsedToNote(String[] noteData) {
        if (noteData.length > 1 && isParsableToInt(noteData[0]) && isParsableToInt(noteData[1])) {
            return true;
        }
        return false;
    }

    private boolean isParsableToInt(String s) {
        String intPattern = "[0-9]*";
        return Pattern.matches(intPattern, s);
    }

    @Override
    public void addNotes(int noteValue, int amount) throws ParseException, IOException {
        if (isNoteValueValid(noteValue) && amount >= 0) {
            String value = Integer.toString(noteValue);
            String oldAmount = Integer.toString(getAllNotes().get(noteValue).size());
            String newAmount = Integer.toString(amount);
            fileHandler.replaceTextInFile(value+","+oldAmount+"\n", value+","+newAmount+"\n");
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNoteValueValid(int noteValue) throws ParseException, IOException {
        Map<Integer, List<Note>> notes = getAllNotes();
        return notes.containsKey(noteValue);
    }
}

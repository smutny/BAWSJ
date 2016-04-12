package pl.edu.amu.bawsj.databases;

import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.utils.FileHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by rafal on 4/8/16.
 */
public class CsvNotesDatabase implements NotesDatabase {
    private final FileHandler fileHandler;

    public CsvNotesDatabase(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
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
        for (int i = 0; i < quantity; i++) {
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
            int oldAmountInt = Integer.parseInt(oldAmount);
            String newAmount = Integer.toString(amount + oldAmountInt);
            if (fileHandler.hasLineWithPrefix(value))
                fileHandler.replaceTextInFile(value + "," + oldAmount, value + "," + newAmount);
//            else
//                fileHandler.addNewRow(value+","+newAmount);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void substractNote(int noteValue) throws IOException, ParseException {
        Map<Integer, List<Note>> allNotes = getAllNotes();
        if (!allNotes.containsKey(noteValue)) {
            throw new NoSuchElementException();
        }

        int size = allNotes.get(noteValue).size();
        int notesAmountAfterSubstraction = size - 1;
        fileHandler.replaceTextInFile(noteValue+","+size, noteValue+","+notesAmountAfterSubstraction);
    }

    private boolean isNoteValueValid(int noteValue) throws ParseException, IOException {
        Map<Integer, List<Note>> notes = getAllNotes();
        return notes.containsKey(noteValue);
    }
}

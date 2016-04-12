package pl.edu.amu.bawsj;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.amu.bawsj.databases.CsvNotesDatabase;
import pl.edu.amu.bawsj.databases.NotesDatabase;
import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.utils.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by rafal on 4/8/16.
 */
public class CsvNotesDatabaseTest {

    @Test(expected = ParseException.class)
    public void shouldThrowExceptionIfFileIsNotParsable() throws ParseException, IOException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        Mockito.when(fileHandlerMocked.getData()).thenReturn(mockIncorrectData());
        NotesDatabase csvNotesDatabase = new CsvNotesDatabase(fileHandlerMocked);
        csvNotesDatabase.getAllNotes();
    }

    private List<String[]> mockIncorrectData() {
        ArrayList<String[]> data = new ArrayList<>();
        String[] note1 = new String[1];
        String[] note2 = new String[2];
        note1[0] = "10";
        note2[0] = "20";
        note2[1] = "27";
        data.add(note1);
        data.add(note2);
        return data;

    }

    @Test
    public void shouldReturnNotesCorrectly() throws ParseException, IOException {
        NotesDatabase csvNotesDatabase = prepareCsvNotesDatabase();
        Map<Integer, List<Note>> allNotes = csvNotesDatabase.getAllNotes();
        Assert.assertNotNull(allNotes);
        Assert.assertEquals(true, allNotes.containsKey(10));
        Assert.assertEquals(true, allNotes.containsKey(20));
        Assert.assertEquals(false, allNotes.containsKey(30));
        Assert.assertEquals(13, allNotes.get(10).size());
        Assert.assertEquals(27, allNotes.get(20).size());
    }


    private NotesDatabase prepareCsvNotesDatabase() throws IOException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        Mockito.when(fileHandlerMocked.getData()).thenReturn(mockData());
        return new CsvNotesDatabase(fileHandlerMocked);
    }

    private List<String[]> mockData() {
        ArrayList<String[]> data = new ArrayList<>();
        String[] note1 = new String[2];
        String[] note2 = new String[2];
        note1[0] = "10";
        note1[1] = "13";
        note2[0] = "20";
        note2[1] = "27";
        data.add(note1);
        data.add(note2);
        return data;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldAddNoteMethodThrowExceptionForNoteValueNonExistingInDatabase() throws ParseException, IOException {
        NotesDatabase csvNotesDatabase = prepareCsvNotesDatabase();
        csvNotesDatabase.addNotes(3, 2);
    }

    @Test
    public void shouldAddNoteCorrectly() throws IOException, ParseException {
        NotesDatabase csvNotesDatabaseWithRealFile = createCsvNotesDatabaseWithRealFile();
        csvNotesDatabaseWithRealFile.addNotes(10, 3);
        csvNotesDatabaseWithRealFile.addNotes(20, 5);
        Map<Integer, List<Note>> notes = csvNotesDatabaseWithRealFile.getAllNotes();
        Assert.assertEquals(3, notes.get(10).size());
        Assert.assertEquals(5, notes.get(20).size());
    }

    private NotesDatabase createCsvNotesDatabaseWithRealFile() throws IOException {
        File file = Files.createTempFile("test", ".csv").toFile();
        FileHandler fileHandler = new FileHandler(file, ",");
        fileHandler.addNewRow("10,0");
        fileHandler.addNewRow("20,0");
        fileHandler.addNewRow("50,0");
        NotesDatabase csvNotesDatabase = new CsvNotesDatabase(fileHandler);
        System.out.println(file.getAbsolutePath());
        return csvNotesDatabase;
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenNoGivenNoteIsAvaliableInSubstractNoteMethod() throws IOException, ParseException {
        NotesDatabase csvNotesDatabase = prepareCsvNotesDatabase();
        csvNotesDatabase.substractNote(300);
    }

    @Test
    public void shouldSubstractNoteCorrectly() throws IOException, ParseException {
        NotesDatabase csvNotesDatabaseWithRealFile = createCsvNotesDatabaseWithRealFile();
        csvNotesDatabaseWithRealFile.addNotes(10, 3);
        int size = csvNotesDatabaseWithRealFile.getAllNotes().get(10).size();
        csvNotesDatabaseWithRealFile.substractNote(10);
        Assert.assertEquals(size-1, csvNotesDatabaseWithRealFile.getAllNotes().get(10).size());
    }

}

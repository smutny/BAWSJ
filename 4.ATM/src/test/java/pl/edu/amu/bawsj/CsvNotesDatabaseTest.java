package pl.edu.amu.bawsj;

import org.junit.*;
import org.mockito.Mockito;
import sun.security.pkcs.ParsingException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rafal on 4/8/16.
 */
public class CsvNotesDatabaseTest {
    private final String NOTES_TEST_FILE_PATH = "/home/rafal/Projects/budowa-aplikacji-w-rodowisku-java/files/testNotes.csv";

    @Test(expected = ParseException.class)
    public void shouldThrowExceptionIfFileIsNotParsable() throws ParseException, IOException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        Mockito.when(fileHandlerMocked.getData()).thenReturn(mockIncorrectData());
        CsvNotesDatabase csvNotesDatabase = new CsvNotesDatabase(fileHandlerMocked);
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
        CsvNotesDatabase csvNotesDatabase = prepareCsvNotesDatabase();
        Map<Integer, List<Note>> allNotes = csvNotesDatabase.getAllNotes();
        Assert.assertNotNull(allNotes);
        Assert.assertEquals(true, allNotes.containsKey(10));
        Assert.assertEquals(true, allNotes.containsKey(20));
        Assert.assertEquals(false, allNotes.containsKey(30));
        Assert.assertEquals(13, allNotes.get(10).size());
        Assert.assertEquals(27, allNotes.get(20).size());
    }


    private CsvNotesDatabase prepareCsvNotesDatabase() throws IOException, ParseException {
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
        CsvNotesDatabase csvNotesDatabase = prepareCsvNotesDatabase();
        csvNotesDatabase.addNotes(3, 2);
    }

}

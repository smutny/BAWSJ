package pl.edu.amu.bawsj;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.amu.bawsj.databases.NotesDatabase;
import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.exceptions.NotEnoughMoneyException;
import pl.edu.amu.bawsj.exceptions.NotEnoughNotesException;
import pl.edu.amu.bawsj.utils.DefaultWithdrawingStrategy;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafal on 4/10/16.
 */
public class DefaultWithdrawingStrategyTest {

    @Test
    public void shouldReturnMinimalAmountOfNotes() throws NotEnoughMoneyException, IOException, ParseException, NotEnoughNotesException {
        DefaultWithdrawingStrategy defaultWithdrawingStrategy = prepareDefaultWithdrawingStrategy();
        List<Note> notes = defaultWithdrawingStrategy.withdrawMoney(230);
        Assert.assertEquals(3, notes.size());
    }

    private DefaultWithdrawingStrategy prepareDefaultWithdrawingStrategy() throws ParseException, IOException {
        NotesDatabase notesDatabaseMocked = Mockito.mock(NotesDatabase.class);
        Mockito.when(notesDatabaseMocked.getAllNotes()).thenReturn(prepareData());
        return new DefaultWithdrawingStrategy(notesDatabaseMocked);
    }

    private Map<Integer, List<Note>> prepareData() {
        List<Note> tenNotes = new ArrayList<>();
        tenNotes.add(new Note(10));
        tenNotes.add(new Note(10));
        tenNotes.add(new Note(10));
        List<Note> twentyNotes = new ArrayList<>();
        twentyNotes.add(new Note(20));
        twentyNotes.add(new Note(20));
        twentyNotes.add(new Note(20));
        List<Note> fiftyNotes = new ArrayList<>();
        fiftyNotes.add(new Note(50));
        fiftyNotes.add(new Note(50));
        fiftyNotes.add(new Note(50));
        List<Note> hundredNotes = new ArrayList<>();
        hundredNotes.add(new Note(100));
        hundredNotes.add(new Note(100));
        hundredNotes.add(new Note(100));
        List<Note> twoHundredNotes = new ArrayList<>();
        twoHundredNotes.add(new Note(200));
        twoHundredNotes.add(new Note(200));
        twoHundredNotes.add(new Note(200));
        Map<Integer, List<Note>> data = new HashMap<>();
        data.put(10, tenNotes);
        data.put(20, twentyNotes);
        data.put(50, fiftyNotes);
        data.put(100, hundredNotes);
        data.put(200, twoHundredNotes);

        return data;
    }

    @Test(expected = NotEnoughNotesException.class)
    public void shouldThrowExceptionIfCantWithdrawWithAvailableNotes() throws IOException, ParseException, NotEnoughMoneyException, NotEnoughNotesException {
        DefaultWithdrawingStrategy defaultWithdrawingStrategy = prepareDefaultWithdrawingStrategy();
        defaultWithdrawingStrategy.withdrawMoney(999999);
    }


}

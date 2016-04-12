package pl.edu.amu.bawsj;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.amu.bawsj.databases.CardDatabase;
import pl.edu.amu.bawsj.databases.CsvCardDatabase;
import pl.edu.amu.bawsj.databases.NotesDatabase;
import pl.edu.amu.bawsj.domain.Card;
import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.exceptions.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rafal on 4/10/16.
 */
public class AtmTest {

    @Test (expected = WrongIdException.class)
    public void shouldThrowExceptionsIfCardIsIncorrect() throws ParseException, WrongPinException, IOException, AnotherCardInsertedException {
        Atm atm = prepareAtmInstance();
        atm.login("123342342342", 1234);
    }

    private Atm prepareAtmInstance() throws IOException, ParseException, WrongPinException {
        CardDatabase cardDatabaseMocked = mockCardDatabase();
        NotesDatabase notesDatabaseMocked = Mockito.mock(NotesDatabase.class);
        Mockito.when(notesDatabaseMocked.getAllNotes()).thenReturn(prepareNotes());
        Atm atm = new Atm(cardDatabaseMocked, notesDatabaseMocked);
        return atm;
    }

    private CardDatabase mockCardDatabase() throws IOException, ParseException {
        CardDatabase cardDatabaseMocked = Mockito.mock(CsvCardDatabase.class);
        Mockito.when(cardDatabaseMocked.findCardByNumber("11111111")).thenReturn(new Card("11111111", 1234, 50000.3));
        Mockito.when(cardDatabaseMocked.findCardByNumber(Mockito.argThat(CoreMatchers.not(CoreMatchers.equalTo("11111111")))))
                .thenThrow(WrongIdException.class);
        return cardDatabaseMocked;
    }

    private Map<Integer,List<Note>> prepareNotes() {
        List<Note> tenNotes = new ArrayList<>();
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

    @Test(expected = AnotherCardInsertedException.class)
    public void shouldThrowExceptionInLoginMethodIfAnotherCardIsInserted() throws AnotherCardInsertedException, WrongPinException, ParseException, IOException {
        Atm atm = prepareAtmInstance();
        atm.login("11111111", 1234);
        atm.login("12345678", 1234);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void shouldThrowExceptionIfTryingToWidthdrawTooMuchMoney() throws AnotherCardInsertedException, WrongPinException, ParseException, IOException, NotEnoughMoneyException, NotEnoughNotesException {
        Atm atm = prepareAtmInstance();
        atm.login("11111111", 1234);
        atm.withdrawMoney(50001);
    }

    @Test(expected = NotEnoughNotesException.class)
    public void shouldThrowExceptionIfThereAreNotEnoughNotesToWithdrawSomeAmount() throws ParseException, WrongPinException, IOException, AnotherCardInsertedException, NotEnoughNotesException, NotEnoughMoneyException {
        Atm atm = prepareAtmInstance();
        atm.login("11111111", 1234);
        atm.withdrawMoney(40000);
    }

    @Test
    public void shouldWithdrawMoneyCorrectlyIfLoginWasSuccessfulAndThereIsEnoughMoneyOnCard() throws AnotherCardInsertedException, WrongPinException, ParseException, IOException, NotEnoughMoneyException, NotEnoughNotesException {
        Atm atm = prepareAtmInstance();
        atm.login("11111111", 1234);
        List<Note> notes = atm.withdrawMoney(250);
        Assert.assertEquals(2, notes.size());
        Assert.assertEquals(true, notes.contains(new Note(200)));
        Assert.assertEquals(true, notes.contains(new Note(50)));
        Assert.assertEquals(false, notes.contains(new Note(20)));
        Assert.assertEquals(false, notes.contains(new Note(10)));
        Assert.assertEquals(false, notes.contains(new Note(1000)));
    }

    @Test
    public void shouldLogoutWorkProperly() throws ParseException, WrongPinException, IOException, AnotherCardInsertedException {
        Atm atm = prepareAtmInstance();
        atm.login("11111111", 1234);
        atm.logout();
        atm.login("11111111", 1234);
    }

}

package pl.edu.amu.bawsj;

import org.junit.Test;
import org.mockito.Mockito;
import pl.edu.amu.bawsj.databases.CardDatabase;
import pl.edu.amu.bawsj.databases.CsvCardDatabase;
import pl.edu.amu.bawsj.exceptions.WrongIdException;
import pl.edu.amu.bawsj.utils.FileHandler;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 4/10/16.
 */
public class CsvCardDatabaseTest {

    @Test(expected = WrongIdException.class)
    public void shouldThrowExceptionIfCardIsNotInDatabase() throws IOException, ParseException {
        CardDatabase csvCardDatabase = prepareCsvCardDatabase();
        csvCardDatabase.findCardByNumber("122");
    }

    private CsvCardDatabase prepareCsvCardDatabase() throws IOException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        Mockito.when(fileHandlerMocked.getData()).thenReturn(mockData());
        return new CsvCardDatabase(fileHandlerMocked);
    }

    private List<String[]> mockData() {
        List<String[]> data = new ArrayList<>();
        String[] card1 = new String[3];
        card1[0] = "1234567890123456";
        card1[1] = "1234";
        card1[2] = "222";
        String[] card2 = new String[3];
        card2[0] = "1234567890333333";
        card2[1] = "5678";
        card2[2] = "333";
        data.add(card1);
        data.add(card2);
        return data;
    }

    @Test(expected = ParseException.class)
    public void shouldThrowExceptionIfInputFileIsWrong() throws IOException, ParseException {
        FileHandler fileHandlerMocked = Mockito.mock(FileHandler.class);
        Mockito.when(fileHandlerMocked.getData()).thenReturn(mockIncorrectData());
        CardDatabase csvCardDatabase = new CsvCardDatabase(fileHandlerMocked);
        csvCardDatabase.findCardByNumber("1234567890123456");
    }

    private List<String[]> mockIncorrectData() {
        List<String[]> data = new ArrayList<>();
        String[] card1 = new String[3];
        card1[0] = "1234567890123456";
        card1[1] = "1234.2";
        card1[2] = "222";
        data.add(card1);
        return data;
    }
}

package pl.edu.amu.bawsj.javafx.presenters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.amu.bawsj.Atm;
import pl.edu.amu.bawsj.databases.CardDatabase;
import pl.edu.amu.bawsj.databases.CsvCardDatabase;
import pl.edu.amu.bawsj.databases.CsvNotesDatabase;
import pl.edu.amu.bawsj.databases.NotesDatabase;
import pl.edu.amu.bawsj.domain.Note;
import pl.edu.amu.bawsj.exceptions.AtmNotPreparedException;
import pl.edu.amu.bawsj.exceptions.NotEnoughMoneyException;
import pl.edu.amu.bawsj.exceptions.NotEnoughNotesException;
import pl.edu.amu.bawsj.exceptions.NotLoggedInException;
import pl.edu.amu.bawsj.javafx.views.*;
import pl.edu.amu.bawsj.utils.FileHandler;
import pl.edu.amu.bawsj.utils.PropertiesHandler;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Rafa³ on 2016-04-11.
 */
public class AtmPresenter {
    private Atm atm;
    private final AtmMainView atmMainView;
    private final Logger logger = LogManager.getLogger();
    private final String LOGOUT_TEXT = "Wylogowano";

    public AtmPresenter(AtmMainView atmMainView) {
        this.atmMainView = atmMainView;
    }

    public void numberButtonClicked(int number) {
        if (screenLabelHasIntText()) {
            String newText = atmMainView.getScreenLabelText() + Integer.toString(number);
            atmMainView.setScreenLabelText(newText);
        } else {
            atmMainView.setScreenLabelText(Integer.toString(number));
        }
    }

    private boolean screenLabelHasIntText() {
        return isParsableToInt(atmMainView.getScreenLabelText());
    }

    private boolean isParsableToInt(String stringToCheck) {
        String intPattern = "[0-9]{1,13}";
        return Pattern.matches(intPattern, stringToCheck);
    }

    public void deleteButtonClicked() {
        logger.info("deleting char");
        if (screenLabelHasIntText()) {
            String screenText = atmMainView.getScreenLabelText();
            String newText = screenText.substring(0, screenText.length() - 1);
            atmMainView.setScreenLabelText(newText);
        }
    }

    public void withdrawButtonClicked() {
        try {
            tryToGetAtm();
            tryToWithdrawMoney(atmMainView.getScreenLabelText());
        } catch (IOException e) {
            atmMainView.setScreenLabelText("Problem z zaladowaniem pliku");
        } catch (AtmNotPreparedException e) {
            atmMainView.setScreenLabelText("Prosze wybrac pliki z bazami danych w zakladce 'app'");
        } catch (NotLoggedInException e) {
            atmMainView.setScreenLabelText("Prosze wlozyc karte");
        } catch (NotEnoughMoneyException e) {
            atmMainView.setScreenLabelText("Niewystarczajaca ilosc srodkow na koncie");
        } catch (NotEnoughNotesException e) {
            atmMainView.setScreenLabelText("Brak wystarczajacej ilosci banknotow do wyplacenia");
        } catch (ParseException e) {
            atmMainView.setScreenLabelText("Prosze wpisac liczbe do wyplacenia");
        } catch (NumberFormatException e) {
            atmMainView.setScreenLabelText("Prosze wpisac liczbe do wyplacenia");
        }
    }

    private void tryToWithdrawMoney(String amount) throws NotLoggedInException, NotEnoughMoneyException, NotEnoughNotesException, IOException, ParseException {
        if (!atm.isLoggedIn()) {
            throw new NotLoggedInException();
        }

        List<Note> notes = atm.withdrawMoney(Integer.parseInt(amount));
        new WithdrawView(notes);
    }

    public void depositButtonClicked() {
        try {
            tryToGetAtm();
            tryToDepositMoney();
        } catch (RuntimeException ex) {
            atmMainView.setScreenLabelText(ex.getMessage());
        } catch (IOException e) {
            atmMainView.setScreenLabelText("Problem z zaladowaniem pliku");
        } catch (AtmNotPreparedException e) {
            atmMainView.setScreenLabelText("Prosze wybrac pliki z bazami danych w zakladce 'app'");
        } catch (NotLoggedInException e) {
            atmMainView.setScreenLabelText("Prosze wlozyc karte");
        }
    }

    private void tryToDepositMoney() throws NotLoggedInException {
        if (!atm.isLoggedIn())
            throw new NotLoggedInException();

        new DepositView(atm);
    }

    public void appButtonClicked() {
        try {
            new AppView();
        } catch (RuntimeException ex) {
            atmMainView.setScreenLabelText(ex.getMessage());
        }
    }

    public void helpButtonClicked() {
        new HelpView();
    }

    public void loginButtonClicked() {
        try {
            tryToGetAtm();
            new LoginView(atm, atmMainView);
        } catch (IOException e) {
            atmMainView.setScreenLabelText("Problem z zaladowaniem pliku");
        } catch (AtmNotPreparedException e) {
            atmMainView.setScreenLabelText("Prosze wybrac pliki z bazami danych w zakladce 'app'");
        }
    }

    private void tryToGetAtm() throws IOException, AtmNotPreparedException {
        CardDatabase cardDatabase = prepareCardsDatabase();
        NotesDatabase notesDatabase = prepareNotesDatabase();
        if (atm == null) {
            atm = new Atm(cardDatabase, notesDatabase);
        }
    }

    private CardDatabase prepareCardsDatabase() throws IOException, AtmNotPreparedException {
        PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();

        if (!propertiesHandler.isCardsFileSet())
            throw new AtmNotPreparedException();

        File cardsFile = new File(propertiesHandler.getCardsFile());
        FileHandler fileHandler = new FileHandler(cardsFile, ",");
        CardDatabase cardDatabase = new CsvCardDatabase(fileHandler);
        return cardDatabase;
    }

    private NotesDatabase prepareNotesDatabase() throws IOException, AtmNotPreparedException {
        PropertiesHandler propertiesHandler = PropertiesHandler.getInstance();

        if (!propertiesHandler.isNotesFileSet())
            throw new AtmNotPreparedException();

        File notesFile = new File(propertiesHandler.getNotesFile());
        FileHandler fileHandler = new FileHandler(notesFile, ",");
        NotesDatabase notesDatabase = new CsvNotesDatabase(fileHandler);
        return notesDatabase;
    }

    public void logoutButtonClicked() {
        try {
            tryToGetAtm();
            if (atm.isLoggedIn()) {
                atm.logout();
                atmMainView.setScreenLabelText(LOGOUT_TEXT);
            }
        } catch (IOException e) {
            atmMainView.setScreenLabelText("Problem z zaladowaniem pliku");
        } catch (AtmNotPreparedException e) {
            atmMainView.setScreenLabelText("Prosze wybrac pliki z bazami danych w zakladce 'app'");
        }
    }

    public void accountBalanceButtonClicked() {
        try {
            tryToGetAtm();
            double balance = atm.getBalance();
            new AccountBalanceView(Double.toString(balance));
        } catch (IOException e) {
            atmMainView.setScreenLabelText("Problem z zaladowaniem pliku");
        } catch (AtmNotPreparedException e) {
            atmMainView.setScreenLabelText("Prosze wybrac pliki z bazami danych w zakladce 'app'");
        } catch (NotLoggedInException e) {
            atmMainView.setScreenLabelText("Prosze wlozyc karte");
        }
    }
}

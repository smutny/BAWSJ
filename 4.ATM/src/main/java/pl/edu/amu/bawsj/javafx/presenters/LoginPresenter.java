package pl.edu.amu.bawsj.javafx.presenters;

import pl.edu.amu.bawsj.Atm;
import pl.edu.amu.bawsj.exceptions.AnotherCardInsertedException;
import pl.edu.amu.bawsj.exceptions.WrongPinException;
import pl.edu.amu.bawsj.javafx.views.LoginView;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * Created by Rafa³ on 2016-04-11.
 */
public class LoginPresenter {
    private final Atm atm;
    private final LoginView loginView;

    public LoginPresenter(Atm atm, LoginView loginView) {
        this.loginView = loginView;
        this.atm = atm;
    }

    public void okButtonClicked(String login, String pin) throws AnotherCardInsertedException, WrongPinException, ParseException, IOException {
        if (isParsableToInt(pin)) {
            atm.login(login, Integer.parseInt(pin));
            loginView.setLabelTextAndClose("Zalogowano");
        }
        else
            throw new WrongPinException();
    }

    private boolean isParsableToInt(String stringToCheck) {
        String intPattern = "[0-9]{1,13}";
        return Pattern.matches(intPattern, stringToCheck);
    }
}

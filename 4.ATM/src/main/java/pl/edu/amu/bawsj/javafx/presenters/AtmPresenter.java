package pl.edu.amu.bawsj.javafx.presenters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.amu.bawsj.Atm;
import pl.edu.amu.bawsj.javafx.views.AtmMainView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rafa³ on 2016-04-11.
 */
public class AtmPresenter {
    private Atm atm;
    private AtmMainView atmMainView;
    private Logger logger = LogManager.getLogger();
    private final String LOGOUT_TEXT = "Wylogowano";

    public AtmPresenter(AtmMainView atmMainView) {
//        atm = new Atm();
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

    public void okButtonClicked() {
        logger.info("OK button");
    }

    public void deleteButtonClicked() {
        logger.info("deleting char");
        if (screenLabelHasIntText()) {
            String screenText = atmMainView.getScreenLabelText();
            String newText = screenText.substring(0, screenText.length()-1);
            atmMainView.setScreenLabelText(newText);
        }
    }

    public void withdrawButtonClicked() {
    }

    public void depositButtonClicked() {
    }

    public void appButtonClicked() {
    }

    public void helpButtonClicked() {

    }

    public void loginButtonClicked() {
    }

    public void logoutButtonClicked() {
        if (atm.isLoggedIn()) {
            atm.logout();
            atmMainView.setScreenLabelText(LOGOUT_TEXT);
        }
    }
}

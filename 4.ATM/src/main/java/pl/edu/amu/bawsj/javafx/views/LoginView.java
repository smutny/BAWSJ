package pl.edu.amu.bawsj.javafx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.Atm;
import pl.edu.amu.bawsj.exceptions.AnotherCardInsertedException;
import pl.edu.amu.bawsj.exceptions.WrongIdException;
import pl.edu.amu.bawsj.exceptions.WrongPinException;
import pl.edu.amu.bawsj.javafx.presenters.LoginPresenter;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Rafa³ on 2016-04-12.
 */
public class LoginView extends FlowPane {
    private Stage loginStage;
    private Parent parent;
    private LoginPresenter loginPresenter;
    private AtmMainView atmMainView;

    public LoginView(Atm atm, AtmMainView atmMainView) {
        this.atmMainView = atmMainView;
        loginPresenter = new LoginPresenter(atm, this);
        loginStage = new Stage();
        try {
            parent = FXMLLoader.load(AtmMainView.class.getResource("/views/Login.fxml"));
            Scene scene = new Scene(parent);
            loginStage.setScene(scene);
            loginStage.show();
            initiliazeButtons();
        } catch (IOException e) {
            throw new RuntimeException("Problem with app initializing");
        }
    }

    private void initiliazeButtons() {
        Button okButton = (Button)parent.lookup("#okButton");
        TextField numberText = (TextField)parent.lookup("#accountNumberLabel");
        PasswordField pinField = (PasswordField)parent.lookup("#passwordLabel");
        okButton.setOnMouseClicked(event -> {
            try {
                loginPresenter.okButtonClicked(numberText.getText(), pinField.getText());
            } catch (AnotherCardInsertedException e) {
                setLabelTextAndClose("Inna karta jest wlozona");
            } catch (WrongPinException e) {
                setLabelTextAndClose("Zly pin");
            } catch (ParseException e) {
                setLabelTextAndClose("Problem z parsowaniem pliku wejsciowego");
            } catch (IOException e) {
                setLabelTextAndClose("Problem z parsowaniem pliku wejsciowego");
            } catch (WrongIdException ex) {
                setLabelTextAndClose("Zly numer konta");
            }
        });
    }

    public void setLabelTextAndClose(String text) {
        atmMainView.setScreenLabelText(text);
        loginStage.close();
    }
}

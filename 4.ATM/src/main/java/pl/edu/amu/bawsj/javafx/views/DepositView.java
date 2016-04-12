package pl.edu.amu.bawsj.javafx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.Atm;
import pl.edu.amu.bawsj.javafx.presenters.DepositPresenter;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Rafa³ on 2016-04-12.
 */
public class DepositView {
    private final Atm atm;
    private final Stage depositStage;
    private Parent parent;
    private final DepositPresenter depositPresenter;
    private TextField tenField;
    private TextField twentyField;
    private TextField fiftyField;
    private TextField hundredField;
    private TextField twoHundredsField;

    public DepositView(Atm atm) {
        this.atm = atm;
        depositPresenter = new DepositPresenter(atm);
        depositStage = new Stage();
        try {
            parent = FXMLLoader.load(AtmMainView.class.getResource("/views/Deposit.fxml"));
            Scene scene = new Scene(parent);
            depositStage.setScene(scene);
            depositStage.show();
            initializeTextFields();
            initiliazeButtons();
        } catch (IOException e) {
            throw new RuntimeException("Problem with app initializing");
        }
    }

    private void initializeTextFields() {
        tenField = (TextField)parent.lookup("#tenLabel");
        twentyField = (TextField)parent.lookup("#twentyLabel");
        fiftyField = (TextField)parent.lookup("#fiftyLabel");
        hundredField = (TextField)parent.lookup("#hundredLabel");
        twoHundredsField = (TextField)parent.lookup("#twoHundredLabel");

        tenField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!tenField.getText().matches("[0-9]{1,12}"))
                    tenField.setText("");
            }
        });
        twentyField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!twentyField.getText().matches("[0-9]{1,12}"))
                    twentyField.setText("");
            }
        });
        fiftyField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!fiftyField.getText().matches("[0-9]{1,12}"))
                    fiftyField.setText("");
            }
        });
        hundredField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!hundredField.getText().matches("[0-9]{1,12}"))
                    hundredField.setText("");
            }
        });
        twoHundredsField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (!twoHundredsField.getText().matches("[0-9]{1,12}"))
                    twoHundredsField.setText("");
            }
        });
    }

    private void initiliazeButtons() {
        Button depositButton = (Button)parent.lookup("#depositButton");

        depositButton.setOnMouseClicked(event -> {
            try {
                depositPresenter.depositMoney(tenField.getText(), twentyField.getText(),
                        fiftyField.getText(), hundredField.getText(), twoHundredsField.getText());
                depositStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

    }
}

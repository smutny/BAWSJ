package pl.edu.amu.bawsj.atm2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.atm2.presenter.ATMMainPresenter;
import pl.edu.amu.bawsj.atmjpa.model.PIN;
import pl.edu.amu.bawsj.atmjpa.model.exception.IncorrectPINException;

import java.io.IOException;

public class InsertCardView {
    private ATMMainPresenter presenter;
    private VBox root;
    private TextField cardNumberTextField;
    private TextField pinTextField;

    public void show() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/insertCardView.fxml"));

        Scene scene = new Scene(root, 300, 150);
        Stage stage = new Stage();

        initializeTextFields();

        Button confirmButton = (Button) root.lookup("#confirmButton");
        confirmButton.setOnMouseClicked(event -> {
            String digitsStr = pinTextField.getText();
            byte[] digits = new byte[digitsStr.length()];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = Byte.parseByte(digitsStr.substring(i, i + 1));
            }
            try {
                presenter.insertCard(cardNumberTextField.getText(), new PIN(digits));
            } catch (IncorrectPINException e) {
                new Dialog().showDialog("Podany PIN jest niepoprawny");
            }
            stage.close();
        });

        stage.setTitle("Podaj dane karty");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeTextFields() {
        cardNumberTextField = (TextField) root.lookup("#cardNumberTextField");
        pinTextField = (TextField) root.lookup("#pinTextField");
    }

    public void setPresenter(ATMMainPresenter presenter) {
        this.presenter = presenter;
    }
}

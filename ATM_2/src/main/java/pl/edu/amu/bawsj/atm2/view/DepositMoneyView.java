package pl.edu.amu.bawsj.atm2.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.atm2.presenter.ATMMainPresenter;

import java.io.IOException;

public class DepositMoneyView {
    private ATMMainPresenter presenter;
    private VBox root;
    private TextField note10TextField;
    private TextField note20TextField;
    private TextField note50TextField;
    private TextField note100TextField;
    private TextField note200TextField;

    public void show() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/depositMoneyView.fxml"));

        Scene scene = new Scene(root, 300, 150);
        Stage stage = new Stage();

        initializeTextFields();

        Button confirmButton = (Button) root.lookup("#confirmButton");
        confirmButton.setOnMouseClicked(event -> {
            presenter.depositMoney(makeStringArrayWithTextFields());
            stage.close();
        });

        stage.setTitle("Podaj jakie i ile banknotów chcesz wpłacić");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeTextFields() {
        note10TextField = (TextField) root.lookup("#note10TextField");
        note20TextField = (TextField) root.lookup("#note20TextField");
        note50TextField = (TextField) root.lookup("#note50TextField");
        note100TextField = (TextField) root.lookup("#note100TextField");
        note200TextField = (TextField) root.lookup("#note200TextField");
    }

    private String[] makeStringArrayWithTextFields() {
        String[] strings = new String[5];
        strings[0] = note10TextField.getText();
        strings[1] = note20TextField.getText();
        strings[2] = note50TextField.getText();
        strings[3] = note100TextField.getText();
        strings[4] = note200TextField.getText();
        return strings;
    }

    public void setPresenter(ATMMainPresenter presenter) {
        this.presenter = presenter;
    }
}

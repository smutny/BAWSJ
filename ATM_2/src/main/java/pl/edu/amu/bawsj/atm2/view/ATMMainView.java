package pl.edu.amu.bawsj.atm2.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.atm2.presenter.ATMMainPresenter;
import pl.edu.amu.bawsj.atmjpa.model.PIN;
import pl.edu.amu.bawsj.atmjpa.model.exception.IncorrectPINException;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ATMMainView extends Application {
    private ATMMainPresenter presenter;

    private VBox root;
    private TextField resultTextField;
    private static final String ACCOUNT_BALANCE_PREFIX = "stan konta: ";
    private Label accountBalanceLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        presenter = new ATMMainPresenter();
        presenter.setMainView(this);

        root = FXMLLoader.load(getClass().getResource("/mainView.fxml"));

        Scene scene = new Scene(root, 400, 250);

        initializeMenuBar(primaryStage);
        initializeResultTextField();
        initializePINPad();
        initializeInsertCardButton();
        initializeDepositMoneyButton();
        initializeButtons();
        initializeAccountBalanceLabel();

        primaryStage.setTitle("ATM");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showDialog(String alertText) {
        new Dialog().showDialog(alertText);
    }

    public void updateAccountBalanceInfo(BigDecimal balance) {
        String balanceStr = DecimalFormat.getCurrencyInstance().format(balance.doubleValue());
        accountBalanceLabel.setText(ACCOUNT_BALANCE_PREFIX + balanceStr);
    }

    public void removeAccountBalanceInfo() {
        accountBalanceLabel.setText(ACCOUNT_BALANCE_PREFIX);
    }

    private void initializeMenuBar(Stage primaryStage) {
        MenuBar menuBar = (MenuBar) root.lookup("#menuBar");
        Menu appMenu = new Menu("Aplikacja");

        MenuItem exitItem = new MenuItem("Wyjdź", null);
        exitItem.setOnAction((event -> Platform.exit()));
        appMenu.getItems().add(exitItem);

        Menu helpMenu = new Menu("Pomoc");

        MenuItem aboutItem = new MenuItem("O aplikacji...", null);
        aboutItem.setOnAction((event -> {
            //TODO
        }));
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(appMenu, helpMenu);
    }

    private void initializeInsertCardButton() {
        root.lookup("#insertCardButton").setOnMouseClicked(event -> {
            try {
                InsertCardView insertCardView = new InsertCardView();
                insertCardView.setPresenter(presenter);
                insertCardView.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeDepositMoneyButton() {
        root.lookup("#depositMoneyButton").setOnMouseClicked(event -> {
            try {
                DepositMoneyView depositMoneyView = new DepositMoneyView();
                depositMoneyView.setPresenter(presenter);
                depositMoneyView.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeResultTextField() {
        resultTextField = (TextField) root.lookup("#resultTextField");
    }

    private void initializePINPad() {
        for (int i = 0; i < 10; i++) {
            Button numButton = (Button) root.lookup("#num" + i + "Button");
            final int finalI = i;
            numButton.setOnMouseClicked(event -> resultTextField.appendText(String.valueOf(finalI)));
        }
        Button deleteButton = (Button) root.lookup("#deleteButton");
        deleteButton.setOnMouseClicked(event -> {
            int length = resultTextField.getLength();
            if (length > 0) {
                resultTextField.deleteText(length - 1, length);
            }
        });
    }

    private void initializeButtons() {
        Button withdrawMoneyButton = (Button) root.lookup("#withdrawMoneyButton");
        withdrawMoneyButton.setOnMouseClicked(event -> {
            if (resultTextField.getText().isEmpty()) {
                showDialog("Nie podano kwoty do wypłacenia");
            } else {
                presenter.withdrawMoney(Integer.parseInt(resultTextField.getText()));
            }
        });
        Button changePINButton = (Button) root.lookup("#changePINButton");
        changePINButton.setOnMouseClicked(event -> {
            String digitsStr = resultTextField.getText();
            byte[] digits = new byte[digitsStr.length()];
            for (int i = 0; i < digits.length; i++) {
                digits[i] = Byte.parseByte(digitsStr.substring(i, i + 1));
            }
            try {
                presenter.changePIN(new PIN(digits));
            } catch (IncorrectPINException e) {
                showDialog("Podany PIN jest niepoprawny");
            }
        });
        Button removeCardButton = (Button) root.lookup("#removeCardButton");
        removeCardButton.setOnMouseClicked(event -> presenter.removeCard());
    }

    private void initializeAccountBalanceLabel() {
        accountBalanceLabel = (Label) root.lookup("#accountBalanceLabel");
        removeAccountBalanceInfo();
    }
}

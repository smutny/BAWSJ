package pl.edu.amu.bawsj.javafx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Rafa³ on 2016-04-12.
 */
public class AccountBalanceView {
    private Stage accountBalanceStage;
    private Parent parent;

    public AccountBalanceView(String balanceText) {
        accountBalanceStage = new Stage();
        try {
            parent = FXMLLoader.load(AtmMainView.class.getResource("/views/AccountBalance.fxml"));
            Scene scene = new Scene(parent);
            Label balanceLabel = (Label)parent.lookup("#balanceLabel");
            balanceLabel.setText("Masz " + balanceText + " z³");
            accountBalanceStage.setScene(scene);
            accountBalanceStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Problem with app initializing");
        }
    }
}

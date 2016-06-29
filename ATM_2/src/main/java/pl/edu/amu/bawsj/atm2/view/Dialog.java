package pl.edu.amu.bawsj.atm2.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialog {
    public void showDialog(String alertText) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        Button okButton = new Button("OK");
        okButton.setOnMouseClicked(event -> dialogStage.close());

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(new Text(alertText), okButton);

        dialogStage.setScene(new Scene(vBox));
        dialogStage.show();
    }
}

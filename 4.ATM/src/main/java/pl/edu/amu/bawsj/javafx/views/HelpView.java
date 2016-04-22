package pl.edu.amu.bawsj.javafx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Rafa� on 2016-04-12.
 */
public class HelpView extends Pane {
    private final Stage helpStage;
    private Parent parent;

    public HelpView() {
        helpStage = new Stage();
        try {
            parent = FXMLLoader.load(AtmMainView.class.getResource("/views/About.fxml"));
            Scene scene = new Scene(parent);
            helpStage.setScene(scene);
            helpStage.show();
        } catch (IOException e) {

            // warto w takich patowych sytuacjach wyrzucac inny wyjatek: IllegalStateException.
            throw new RuntimeException("Problem with app initializing");
        }
    }
}

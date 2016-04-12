package pl.edu.amu.bawsj.javafx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.utils.PropertiesHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by Rafa� on 2016-04-11.
 */
public class AppView extends VBox {
    private final Stage appStage;
    private Parent parent;
    private final String NOTES_FILE_CHOOSER_TEXT = "Please pick notes file";
    private final String CARDS_FILE_CHOOSER_TEXT = "Please pick cards file";
    private final PropertiesHandler propertiesHandler;

    public AppView() {
        propertiesHandler = PropertiesHandler.getInstance();
        appStage = new Stage();
        try {
            parent = FXMLLoader.load(AtmMainView.class.getResource("/views/App.fxml"));
            Scene scene = new Scene(parent);
            initiliazeButtons();
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException e) {
            throw new RuntimeException("Problem with app initializing");
        }
    }

    private void initiliazeButtons() {
        Button notesFileButton = (Button) parent.lookup("#notesFileButton");
        Button cardsFileButton = (Button) parent.lookup("#cardsFileButton");
        Button exitButton = (Button) parent.lookup("#exitButton");

        notesFileButton.setOnMouseClicked(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle(NOTES_FILE_CHOOSER_TEXT);
            FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("Csv files", "*.csv");
            chooser.getExtensionFilters().add(csvFilter);
            File selectedFile = chooser.showOpenDialog(appStage);

            if (selectedFile != null) {
                propertiesHandler.setNotesFileUrl(selectedFile.getAbsolutePath());
            }
        });

        cardsFileButton.setOnMouseClicked(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle(CARDS_FILE_CHOOSER_TEXT);
            FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("Csv files", "*.csv");
            chooser.getExtensionFilters().add(csvFilter);
            File selectedFile = chooser.showOpenDialog(appStage);
            if (selectedFile != null) {
                propertiesHandler.setCardsFileUrl(selectedFile.getAbsolutePath());
            }
        });

        exitButton.setOnMouseClicked(event -> {
            this.appStage.close();
        });
    }
}

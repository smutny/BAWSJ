package pl.edu.amu.bawsj.javafx.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.amu.bawsj.domain.Note;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafa³ on 2016-04-12.
 */
public class WithdrawView extends VBox {
    Stage withdrawStage;
    Parent parent;

    public WithdrawView(List<Note> notes) {
        withdrawStage = new Stage();
        try {
            parent = FXMLLoader.load(AtmMainView.class.getResource("/views/WithdrawInfo.fxml"));
            Scene scene = new Scene(parent);
            withdrawStage.setScene(scene);
            withdrawStage.show();
            setCorrectNotesNumbers(notes);
        } catch (IOException e) {
            throw new RuntimeException("Problem with app initializing");
        }
    }

    private void setCorrectNotesNumbers(List<Note> notes) {
        Map<Integer, Integer> notesNumber = prepareNotesNumberMap();
        fillNotesNumberMap(notes, notesNumber);

        Label tenLabel = (Label)parent.lookup("#tenLabel");
        Label twentyLabel = (Label)parent.lookup("#twentyLabel");
        Label fiftyLabel = (Label)parent.lookup("#fiftyLabel");
        Label hundredLabel = (Label)parent.lookup("#hundredLabel");
        Label twoHundredLabel = (Label)parent.lookup("#twoHundredLabel");

        tenLabel.setText(notesNumber.get(10).toString());
        twentyLabel.setText(notesNumber.get(20).toString());
        fiftyLabel.setText(notesNumber.get(50).toString());
        hundredLabel.setText(notesNumber.get(100).toString());
        twoHundredLabel.setText(notesNumber.get(200).toString());
    }

    private Map<Integer, Integer> prepareNotesNumberMap() {
        Map<Integer, Integer> notesNumber = new HashMap<>();
        notesNumber.put(10, 0);
        notesNumber.put(20, 0);
        notesNumber.put(50, 0);
        notesNumber.put(100, 0);
        notesNumber.put(200, 0);
        return notesNumber;
    }

    private void fillNotesNumberMap(List<Note> notes, Map<Integer, Integer> notesNumber) {
        for (Note note : notes) {
            Integer newNumber = notesNumber.get(note.getValue()) + 1;
            notesNumber.put(note.getValue(), newNumber);
        }
    }
}

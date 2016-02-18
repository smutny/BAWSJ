package pl.edu.amu.bawsj.javafx.b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatorView extends Application {
    private CalculatorPresenter presenter;
    private Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            root = FXMLLoader.load(getClass()
                    .getResource("/b.fxml"));

            for (int i = 0; i < 10; i++) {
                Button lookup = (Button) root.lookup("#num" + i);
                final int finalI = i;
                lookup.setOnMouseClicked(event -> presenter.numClicked(finalI));
            }
            root.lookup("#multiplication")
                    .setOnMouseClicked(event -> presenter.multiplicationClicked());
            root.lookup("#addition")
                    .setOnMouseClicked(event -> presenter.additionClicked());
            root.lookup("#subtraction")
                    .setOnMouseClicked(event -> presenter.subtractionClicked());
            root.lookup("#division")
                    .setOnMouseClicked(event -> presenter.divisionClicked());
            root.lookup("#result")
                    .setOnMouseClicked(event -> presenter.resultClicked());

            presenter = new CalculatorPresenter(this);

            Scene scene = new Scene(root, 300, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showResult(String s) {
        ((TextField) (root.lookup("#resultTextField"))).setText(s);
    }
}

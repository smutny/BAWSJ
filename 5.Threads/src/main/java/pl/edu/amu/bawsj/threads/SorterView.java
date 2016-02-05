package pl.edu.amu.bawsj.threads;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.SplittableRandom;

public class SorterView extends Application {

    private SorterPresenter presenter;
    private Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass()
                .getResource("/a.fxml"));
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getNums() {
        return ((TextArea) root.lookup("#numsArea")).getText();
    }

    public void setNums(String nums) {
         ((TextArea) root.lookup("#numsArea")).setText(nums);
    }
}

package pl.edu.amu.bawsj.threads;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SorterView extends Application {
    @FXML
    Button sortButton;
    @FXML
    Button seedButton;
    @FXML
    TextField seedCount;
    @FXML
    TextArea numsArea;


    private SorterPresenter presenter;
    private Pane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass()
                .getResource("/a.fxml"));
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        presenter = new SorterPresenter(this);

        seedButton = (Button) root.lookup("#seedButton");
        seedButton.setOnMouseClicked(event -> presenter.seedClicked());

        sortButton = (Button) root.lookup("#sortButton");
        sortButton.setOnMouseClicked(event -> presenter.sortClicked());

        seedCount = (TextField) root.lookup("#seedCount");
        numsArea = (TextArea) root.lookup("#numsArea");
    }


    public String getSeedCount() {
        return seedCount.getText();
    }

    public String getNums() {
        return ((TextArea) root.lookup("#numsArea")).getText();
    }

    public void setNums(String nums) {
        ((TextArea) root.lookup("#numsArea")).setText(nums);
    }

    public void block() {

        root.lookup("#operationIndicator").setVisible(true);
    }

    public void unBlock() {
        root.lookup("#operationIndicator").setVisible(false);
    }
}

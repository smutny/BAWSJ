package pl.edu.amu.bawsj.javafx.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.edu.amu.bawsj.javafx.presenters.AtmPresenter;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Created by Rafa³ on 2016-04-11.
 */
public class AtmMainView extends Application {

    private final Logger logger = LogManager.getLogger();
    private Parent root;
    private AtmPresenter atmPresenter;
    private Label screenLabel;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Starting app");
        root = FXMLLoader.load(AtmMainView.class.getResource("/views/Atm.fxml"));

        prepareAtmPresenter();
        initalizeButtons();
        initalizeViews();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Atm ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareAtmPresenter() {
        atmPresenter = new AtmPresenter(this);
    }

    private void initalizeButtons() {
        for( int i = 0; i < 10; i++ ) {
            Button lookup = (Button)root.lookup( "#num" + i+"Button" );
            final int finalI = i;
            lookup.setOnMouseClicked( event -> atmPresenter.numberButtonClicked( finalI ) );
        }

        Button deleteButton = (Button)root.lookup("#deleteButton");
        Button okButton = (Button)root.lookup("#okButton");
        Button withdrawButton = (Button)root.lookup("#withdrawButton");
        Button depositButton = (Button)root.lookup("#depositButton");
        Button appButton = (Button)root.lookup("#appButton");
        Button helpButton = (Button)root.lookup("#helpButton");
        Button loginButton = (Button)root.lookup("#loginButton");
        Button logoutButton = (Button)root.lookup("#logoutButton");

        deleteButton.setOnMouseClicked(event -> atmPresenter.deleteButtonClicked());
        okButton.setOnMouseClicked(event -> atmPresenter.okButtonClicked());
        withdrawButton.setOnMouseClicked(event -> atmPresenter.withdrawButtonClicked());
        depositButton.setOnMouseClicked(event -> atmPresenter.depositButtonClicked());
        appButton.setOnMouseClicked(event -> openAppButton());
        helpButton.setOnMouseClicked(event -> atmPresenter.helpButtonClicked());
        loginButton.setOnMouseClicked(event -> atmPresenter.loginButtonClicked());
        logoutButton.setOnMouseClicked(event -> atmPresenter.logoutButtonClicked());
    }

    private void openAppButton() {
        Stage appStage = new Stage();
        try {
            new AppView();
        } catch (RuntimeException ex) {
            screenLabel.setText(ex.getMessage());
        }
    }

    private void initalizeViews() {
        screenLabel = (Label)root.lookup("#screenLabel");
    }

    public String getScreenLabelText() {
        return screenLabel.getText();
    }

    public void setScreenLabelText(String newText) {
        screenLabel.setText(newText);
    }
}

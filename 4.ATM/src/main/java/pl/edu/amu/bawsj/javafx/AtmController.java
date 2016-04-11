package pl.edu.amu.bawsj.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;


/**
 * Created by Rafa³ on 2016-04-11.
 */
public class AtmController extends Application {

    private final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        logger.info("Starting app");
        Parent root = FXMLLoader.load(AtmController.class.getResource("/Atm.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Atm ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

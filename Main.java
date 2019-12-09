package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static final int Width = 500;
    public static final int Height = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane sampleRoot = FXMLLoader.load (getClass ().getResource ("sample.fxml"));
        primaryStage.setTitle ("Evolution Simulator");
        Scene sampleScene = new Scene (sampleRoot, 500, 550);
        primaryStage.setScene (sampleScene);
        primaryStage.show ();

        Button butRun = (Button) sampleScene.lookup ("#butRun");
        butRun.setOnAction (event -> {
            try {
                AnchorPane canvasRoot = FXMLLoader.load (getClass ().getResource ("canvas.fxml"));
                Scene scene = new Scene (canvasRoot, Width, Height);
                primaryStage.setScene (scene);
                primaryStage.show ();

            } catch (IOException e) {
                e.printStackTrace ();
            }
        });

        /*AnchorPane canvasRoot = FXMLLoader.load (getClass ().getResource ("canvas.fxml"));

        Scene scene = new Scene (canvasRoot, Width, Height);
        primaryStage.setScene (scene);
        primaryStage.show ();*/
//        CanvasController controller = loader.getController ();
//        Environment environment = new Environment (10, Width, Height);
//        environment.tick ();

    }

    public static void main(String[] args) {
        launch (args);
    }
}

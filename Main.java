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
    public static final int Width = 646;
    public static final int Height = 439;
    public static final int butHeight = 26;
    private CanvasController canvasController;
    //private SampleController sampleController;
    //private Config config;

    /*private static Main instance;
    public Main() {
        instance = this;
    }
    // static method to get instance of view
    public static Main getInstance() {
        return instance;
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {

        //FXMLLoader sampleLoader = new FXMLLoader (getClass ().getResource ("sample.fxml"));
        GridPane sampleRoot = FXMLLoader.load (getClass ().getResource ("sample.fxml"));
        //sampleController = sampleLoader.getController ();
        primaryStage.setTitle ("Evolution Simulator");
        Scene sampleScene = new Scene (sampleRoot, 500, 550);
        primaryStage.setScene (sampleScene);
        primaryStage.show ();
        //sampleController.main = this;

        Button butRun = (Button) sampleScene.lookup ("#butRun");
        butRun.setOnAction (event -> {
            try {
                FXMLLoader canvasLoader = new FXMLLoader (getClass ().getResource ("canvas.fxml"));
                AnchorPane canvasRoot = FXMLLoader.load (getClass ().getResource ("canvas.fxml"));
                canvasController = canvasLoader.getController ();
                Scene scene = new Scene (canvasRoot, Width, Height + butHeight);
                primaryStage.setScene (scene);
                primaryStage.show ();
                //canvasController.setConfig(config);
                //canvasController.initialize1 ();

            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
    }


    @Override
    public void stop() throws Exception {
        super.stop ();
        if (canvasController != null) {
            canvasController.stop ();
        }

        System.exit (0);
    }
    /*public void setConfig(Config config){
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }*/

    public static void main(String[] args) {
        launch (args);
    }
}

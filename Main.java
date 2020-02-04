package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int Width = 646;
    public static final int Height = 439;
    public static final int butHeight = 26;
    private CanvasController canvasController;
    private Config config;

    private static Main instance;

    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane sampleRoot = FXMLLoader.load (getClass ().getResource ("sample.fxml"));
        primaryStage.setTitle ("Evolution Simulator");
        Scene sampleScene = new Scene (sampleRoot);
        primaryStage.setScene (sampleScene);
        primaryStage.show ();
    }

    @Override
    public void stop() throws Exception {
        super.stop ();
        if (canvasController != null) {
            canvasController.stop ();
        }

        System.exit (0);
    }

    public void setCanvasController(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }

    public static void main(String[] args) {
        launch (args);
    }
}

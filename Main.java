package sample;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.layout.AnchorPane;
        import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("canvas.fxml"));
        AnchorPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout, 500, 500);
        primaryStage.setScene(scene);

        CanvasController controller = loader.getController();
        controller.tick ();

        Parent root = FXMLLoader.load(getClass().getResource("canvas.fxml"));
        primaryStage.setTitle("Evolution Simulator");
        primaryStage.setScene(new Scene(root, 500, 550));
        primaryStage.show();

    }

    
    public static void main(String[] args) {
        launch(args);
    }
}

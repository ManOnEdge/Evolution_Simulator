package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SampleController {
    public Button butRun;
    public TextField orgNo;
    public TextField orgSpeed;
    public TextField orgSize;
    public TextField orgFood;
    public TextField speedMONo;
    public TextField mutSpeed;
    public TextField sizeMONo;
    public TextField mutSize;
    public TextField foodMONo;
    public TextField mutFoodReq;
    public TextField foodNo;

    public void pressRun() {
        int OrgNoValue = Integer.parseInt (orgNo.getText ());
        double OrgSpeedValue = Double.parseDouble (orgSpeed.getText ());
        int OrgSizeValue = Integer.parseInt (orgSize.getText ());
        int OrgFoodValue = Integer.parseInt (orgFood.getText ());
        int SpeedMONoValue = Integer.parseInt (speedMONo.getText ());
        double MutSpeedValue = Integer.parseInt (mutSpeed.getText ());
        int SizeMONoValue = Integer.parseInt (sizeMONo.getText ());
        int MutSizeValue = Integer.parseInt (mutSize.getText ());
        int FoodMONoValue = Integer.parseInt (foodMONo.getText ());
        int MutFoodReqValue = Integer.parseInt (mutFoodReq.getText ());
        int FoodNoValue = Integer.parseInt (foodNo.getText ());

        Config config = new Config (OrgNoValue, OrgSpeedValue, OrgSizeValue,
                OrgFoodValue, SpeedMONoValue, MutSpeedValue, SizeMONoValue,
                MutSizeValue, FoodMONoValue, MutFoodReqValue, FoodNoValue);
        Main.getInstance ().setConfig (config);
        if (OrgSizeValue <= MutSizeValue && OrgSpeedValue <= MutSpeedValue && OrgFoodValue >= MutFoodReqValue){
            try {
                FXMLLoader loader = new FXMLLoader ();
                loader.setLocation (Main.class.getResource ("canvas.fxml"));
                AnchorPane canvasRoot = loader.load ();
                CanvasController canvasController = loader.getController ();
                Scene scene = new Scene (canvasRoot, Main.Width, Main.Height + Main.butHeight);
                Stage stage = (Stage) orgNo.getScene ().getWindow ();
                stage.setScene (scene);
                stage.show ();
                Main.getInstance ().setCanvasController (canvasController);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        } else {
            System.out.println ("Invalid Input");
        }
    }
}

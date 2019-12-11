package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SampleController {
    public Button butRun;
    public TextField OrgNo;
    public TextField OrgSpeed;
    public TextField OrgSize;
    public TextField OrgFood;
    public TextField SpeedMONo;
    public TextField MutSpeed;
    public TextField SizeMONo;
    public TextField MutSize;
    public TextField FoodMONo;
    public TextField MutFoodReq;
    public TextField FoodNo;
    public TextField EnvSize;


    public void pressRun() //Activates the simulator
    {
        int OrgNoValue = Integer.valueOf (OrgNo.getText ());
        int OrgSizeValue = Integer.valueOf (OrgSize.getText ());
        Config config = new Config (OrgNoValue,OrgSizeValue);

    }

}

package sample;

public class Config {
    int orgNo;
    double orgSpeed;
    int orgSize;
    int orgFood;
    int speedMONo;
    double mutSpeed;
    int sizeMONo;
    int mutSize;
    int foodMONo;
    int mutFoodReq;
    int foodNo;

    public Config(int orgNo, double orgSpeed, int orgSize, int orgFood, int speedMONo, double mutSpeed,
                  int sizeMONo, int mutSize, int foodMONo, int mutFoodReq, int foodNo) {
        this.orgNo = orgNo;
        this.orgSpeed = orgSpeed;
        this.orgSize = orgSize;
        this.orgFood = orgFood;
        this.speedMONo = speedMONo;
        this.mutSpeed = mutSpeed;
        this.sizeMONo = sizeMONo;
        this.mutSize = mutSize;
        this.foodMONo = foodMONo;
        this.mutFoodReq = mutFoodReq;
        this.foodNo = foodNo;
    }
}

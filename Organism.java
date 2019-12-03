package sample;

public class Organism {
    public int size;
    public int speed;
    public int foodRequired;
    public int x;
    public int y;

    public Organism(int size, int speed,int foodRequired, int x, int y){
        this.size = size;
        this.speed = speed;
        this.foodRequired = foodRequired;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFoodRequired() {
        return foodRequired;
    }

    public void setFoodRequired(int foodRequired) {
        this.foodRequired = foodRequired;
    }
}

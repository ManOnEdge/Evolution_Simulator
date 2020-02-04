package sample;

import java.util.ArrayList;
import java.util.Random;

public class Organism {
    public int size;
    public double speed;
    public int foodRequired;
    public double x;
    public double y;
    private double energy = 100;
    public static final double MAX_ENERGY = 200;

    public Organism(int size, double speed, int foodRequired, double x, double y) {
        this.size = size;
        this.speed = speed;
        this.foodRequired = foodRequired;
        this.x = x;
        this.y = y;
    }

    public double getEnergy() {
        return energy;
    }

    public void move(ArrayList<FoodItem> foods) {
        if (foods.isEmpty ()) {
            move ();
            //return;
        } else {
            FoodItem closestFoodItem = foods.get (0);
            for (int i = 1; i < foods.size (); i++) {
                double distance1 = getDistance (foods.get (i));
                double distance2 = getDistance (closestFoodItem);
                if (distance1 < distance2) {
                    closestFoodItem = foods.get (i);
                }
            }
            double deltaX = closestFoodItem.x - x;
            double deltaY = closestFoodItem.y - y;
            double angle = Math.atan2 (deltaY, deltaX);
            x = x + speed * Math.cos (angle) * (energy / (MAX_ENERGY / 2));
            y = y + speed * Math.sin (angle) * (energy / (MAX_ENERGY / 2));
        }
    }

    public void decreaseEnergyOnMove() {
        energy = energy - 0.2;
    }

    public void increaseEnergyOnConsumption() {
        energy = energy + 10;
    }

    private double getDistance(FoodItem foodItem) {
        double deltaX = Math.pow (x - foodItem.x, 2);
        double deltaY = Math.pow (y - foodItem.y, 2);
        double result = Math.sqrt (deltaX + deltaY);
        return result;
    }

    public void move() {
        Random rnd = new Random ();
        changeXCoordinate (rnd.nextBoolean ());
        changeYCoordinate (rnd.nextBoolean ());
    }

    private void changeYCoordinate(boolean verticalDir) {
        if (verticalDir) {
            if (y + speed < Main.Height) {
                y = y + speed;
            } else {
                y = y - speed;
            }
        } else {
            if (y - speed > 0) {
                y = y - speed;
            } else {
                y = y + speed;
            }
        }
    }

    private void changeXCoordinate(boolean horizontalDir) {
        if (horizontalDir) {
            if (x + speed < Main.Width) {
                x = x + speed;
            } else {
                x = x - speed;
            }
        } else {
            if (x - speed > 0) {
                x = x - speed;
            } else {
                x = x + speed;
            }
        }
    }
}

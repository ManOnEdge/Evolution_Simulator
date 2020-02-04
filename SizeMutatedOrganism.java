package sample;

import java.util.ArrayList;
import java.util.Random;

public class SizeMutatedOrganism extends Organism {

    SizeMutatedOrganism(int size, double speed, int foodRequired, double x, double y) {
        super (size, speed, foodRequired, x, y);
    }

    public void move(ArrayList<FoodItem> foods, ArrayList<Organism> population) {
        if (foods.isEmpty () && population.isEmpty ()) {
            move ();
            //return;
        } else if (foods.isEmpty ()) {
            Organism closestOrganism = population.get (0);
            for (int i = 1; i < population.size (); i++) {
                double distance1 = getDistanceToOrganism (population.get (i));
                double distance2 = getDistanceToOrganism (closestOrganism);
                if (distance1 < distance2) {
                    closestOrganism = population.get (i);
                }
            }
            double deltaX = closestOrganism.x - x;
            double deltaY = closestOrganism.y - y;
            double angle = Math.atan2 (deltaY, deltaX);
            x = x + speed * Math.cos (angle);
            y = y + speed * Math.sin (angle);
        } else if (population.isEmpty ()) {
            FoodItem closestFoodItem = foods.get (0);
            for (int i = 1; i < foods.size (); i++) {
                double distance1 = getDistanceToFoodItem (foods.get (i));
                double distance2 = getDistanceToFoodItem (closestFoodItem);
                if (distance1 < distance2) {
                    closestFoodItem = foods.get (i);
                }
            }
            double deltaX = closestFoodItem.x - x;
            double deltaY = closestFoodItem.y - y;
            double angle = Math.atan2 (deltaY, deltaX);
            x = x + speed * Math.cos (angle);
            y = y + speed * Math.sin (angle);
        } else {
            FoodItem closestFoodItem = foods.get (0);
            Organism closestOrganism = population.get (0);
            for (int i = 1; i < foods.size (); i++) {
                double distance1 = getDistanceToFoodItem (foods.get (i));
                double distance2 = getDistanceToFoodItem (closestFoodItem);
                if (distance1 < distance2) {
                    closestFoodItem = foods.get (i);
                }
            }
            for (int i = 1; i < population.size (); i++) {
                double distance1 = getDistanceToOrganism (population.get (i));
                double distance2 = getDistanceToOrganism (closestOrganism);
                if (distance1 < distance2) {
                    closestOrganism = population.get (i);
                }
            }
            if (getDistanceToFoodItem (closestFoodItem) < getDistanceToOrganism (closestOrganism)) {
                double deltaX = closestFoodItem.x - x;
                double deltaY = closestFoodItem.y - y;
                double angle = Math.atan2 (deltaY, deltaX);
                x = x + speed * Math.cos (angle);
                y = y + speed * Math.sin (angle);
            } else {
                double deltaX = closestOrganism.x - x;
                double deltaY = closestOrganism.y - y;
                double angle = Math.atan2 (deltaY, deltaX);
                x = x + speed * Math.cos (angle);
                y = y + speed * Math.sin (angle);
            }
        }
    }

    private double getDistanceToFoodItem(FoodItem foodItem) {
        double deltaX = Math.pow (x - foodItem.x, 2);
        double deltaY = Math.pow (y - foodItem.y, 2);
        double distanceToFoodItem = Math.sqrt (deltaX + deltaY);
        return distanceToFoodItem;
    }

    private double getDistanceToOrganism(Organism organism) {
        double deltaX = Math.pow (x - organism.x, 2);
        double deltaY = Math.pow (y - organism.y, 2);
        double distanceToOrganism = Math.sqrt (deltaX + deltaY);
        return distanceToOrganism;
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

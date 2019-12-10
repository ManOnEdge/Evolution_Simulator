package sample;

import java.util.Random;

public class Organism {
    public int size;
    public int speed;
    public int foodRequired;
    public int x;
    public int y;

    public Organism(int size, int speed, int foodRequired, int x, int y) {
        this.size = size;
        this.speed = speed;
        this.foodRequired = foodRequired;
        this.x = x;
        this.y = y;
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

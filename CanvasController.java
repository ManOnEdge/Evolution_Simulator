package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class CanvasController {

    public Canvas Plane;
    public Button butDraw;
    public Environment env;
    public Button butStop;
    //public Config config;

    public void draw(ArrayList<Organism> population, ArrayList<FoodItem> foods) {

        GraphicsContext gc = Plane.getGraphicsContext2D ();
        gc.clearRect (0, 0, Main.Width, Main.Height);
        for (Organism organism : population) {
            gc.strokeOval (organism.x, organism.y, organism.size, organism.size);
        }
        for (FoodItem foodItem : foods) {
            gc.fillRect (foodItem.x, foodItem.y, 2, 2);
        }
    }

    public void pressDraw() {
        env.startEnv ();

    }

    public void pressStop() {
        env.stopEnv ();
    }

    public void initialize() {
        env = new Environment (3, 10, Main.Width, Main.Height, this);
        env.tick ();
    }

    public void stop() {
        env.stop ();
    }

    /*public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }*/
}

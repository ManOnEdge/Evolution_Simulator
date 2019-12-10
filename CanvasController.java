package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Random;

public class CanvasController {

    public Canvas Plane;
    public Button butDraw;
    public Environment env;
    public Button butStop;

    public void draw(ArrayList<Organism> population) {

        GraphicsContext gc = Plane.getGraphicsContext2D ();
        Random rnd = new Random ();

        gc.clearRect (0, 0, Main.Width, Main.Height);
        for (Organism organism : population) {
            gc.strokeOval (organism.x, organism.y, organism.size, organism.size);
        }
    }

    public void pressDraw() {
        //draw ();

        //env.startEnv ();
        env.startEnv ();
        //start thread for environment

        /*Runnable envThread = () -> {
            while (env.isRunning ()) {
                try {
                    env.tick ();
                    draw ();
                    Thread.sleep (100);
                } catch (InterruptedException e) {
                    System.out.println (e);
                }
            }

        };
        new Thread (envThread).start ();*/
    }

    public void pressStop() {
        env.stopEnv ();
    }

    public void initialize() {
        env = new Environment (3, Main.Width, Main.Height, this);
        env.tick ();
    }
}

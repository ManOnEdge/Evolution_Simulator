package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.util.Random;

public class CanvasController {

    public Canvas Plane;
    public Button butDraw;
    public Environment env;
    public Button butStop;


    public void Draw() {

        GraphicsContext gc = Plane.getGraphicsContext2D ();
        Random rnd = new Random ();

        gc.fillOval (rnd.nextInt (500), rnd.nextInt (500), 5, 5);
        //gc.strokeOval (rnd.nextInt (500), rnd.nextInt (500), 5, 5);

    }

    public void pressDraw() {
        Draw ();

        env.startEnv ();

        //start thread for environment

        Runnable envThread = () -> {
            while (env.isRunning ()) {
                try {
                    env.tick ();
                    Draw ();
                    Thread.sleep (100);
                } catch (InterruptedException e) {
                    System.out.println (e);
                }
            }

        };
        new Thread (envThread).start ();
    }

    public void pressStop() {
        env.stopEnv ();
    }

    public void initialize() {
        env = new Environment (20, 500, 500);

    }
}

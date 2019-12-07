package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Random;

public class CanvasController {

    public Canvas Plane;
    public Button butDraw;
    int height;
    int width;

    ArrayList<Organism> population = new ArrayList<>();
/*
    public CanvasController(int populationSize)
    {
        Random rnd = new Random();

        for(int i=0; i<populationSize; i++)
        {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
        }
    }
*/
    public void tick()
    {
        while(true){
            System.out.println ("++");
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
        }
        //for loop to update every organism
    }

    public void Draw()
    {
        GraphicsContext gc = Plane.getGraphicsContext2D();
        //gc.clearRect ();

        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);


    }
    public void pressDraw()
    {
        Draw ();
    }
}

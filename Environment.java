package sample;


import java.util.ArrayList;
import java.util.Random;

public class Environment {
    ArrayList<Organism> population = new ArrayList<> ();
    CanvasController canvasController;
    Runnable envThread = () -> {
        while (true) {
            try {
//                System.out.println ("+");
                if (isRunning ()) {
                    canvasController.draw (population);
                    for (Organism organism : population) {
                        organism.move ();
                    }
                }
                Thread.sleep (5);
            } catch (InterruptedException e) {
                System.out.println (e);
            }
        }
    };

    Thread thread = new Thread (envThread);
    private boolean running = false;

    public Environment(int populationSize, int width, int height, CanvasController canvasController) {
        this.canvasController = canvasController;
        Random rnd = new Random ();

        for (int i = 0; i < populationSize; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
            population.add (new Organism (15, 3, 0, x, y));
        }
    }

    public void tick() {

        thread.start ();  //for loop to update every organism
    }

    public boolean isRunning() {
        return running;
    }

    public void startEnv() {
        running = true;
    }

    public void stopEnv() {
        running = false;
    }
}

package sample;


import java.util.ArrayList;
import java.util.Random;

public class Environment {
    private static final int ticksToFoodCreationSize = 1;
    private int width;
    private int height;
    private Random rnd = new Random ();
    private ArrayList<Organism> population = new ArrayList<> ();
    private ArrayList<FoodItem> foods = new ArrayList<> ();
    private CanvasController canvasController;
    private int ticksToFoodCreation = ticksToFoodCreationSize;
    private Runnable envThread = () -> {
        while (true) {
            try {
                System.out.println ("+");
                if (isRunning ()) {
                    onTickAction ();
                }
                Thread.sleep (40);
            } catch (InterruptedException e) {
                System.out.println (e);
            }
        }
    };

    private void onTickAction() {
        for (Organism organism : population) {
            organism.move ();
            foods.removeIf (foodItem -> Math.abs (foodItem.x - organism.x) < 9 && Math.abs (foodItem.y - organism.y) < 9);
        }

        if (ticksToFoodCreation == 0) {
            addFoodItem (width, height);
            ticksToFoodCreation = ticksToFoodCreationSize;
        } else {
            ticksToFoodCreation--;
        }
        canvasController.draw (population, foods);
    }

    private Thread thread = new Thread (envThread);
    private boolean running = false;

    public Environment(int populationSize, int foodNo, int width, int height, CanvasController canvasController) {
        this.canvasController = canvasController;
        this.width = width;
        this.height = height;

        for (int i = 0; i < populationSize; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
            population.add (new Organism (15, 3, 0, x, y));
        }
        for (int i = 0; i < foodNo; i++) {
            addFoodItem (width, height);
        }
        canvasController.draw (population, foods);
    }

    private void addFoodItem(int width, int height) {
        int x = rnd.nextInt (width);
        int y = rnd.nextInt (height);
        foods.add (new FoodItem (x, y));
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

    public void stop() {
        thread.interrupt ();
    }
}

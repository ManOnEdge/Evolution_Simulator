package sample;


import java.util.ArrayList;
import java.util.Random;

public class Environment {

    private boolean running = false;

    ArrayList<Organism> population = new ArrayList<> ();

    public Environment(int populationSize, int width, int height) {
        Random rnd = new Random ();

        for (int i = 0; i < populationSize; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
        }
    }

    public void tick() {

        System.out.println ("++");

        //for loop to update every organism
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

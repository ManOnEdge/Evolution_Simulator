package sample;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Environment {
    private static final int ticksToFoodCreationSize = 1;
    private int width;
    private int height;
    private Config config;
    private Random rnd = new Random ();
    public static ArrayList<Organism> simpleOrganisms = new ArrayList<> ();
    public static ArrayList<SizeMutatedOrganism> sizeMutOrganisms = new ArrayList<> ();
    public static ArrayList<SpeedMutatedOrganism> speedMutOrganisms = new ArrayList<> ();
    public static ArrayList<FoodRequiredMutatedOrganism> foodRequiredMutatedOrganisms = new ArrayList<> ();
    private ArrayList<FoodItem> foods = new ArrayList<> ();
    private CanvasController canvasController;
    private int ticksToFoodCreation = ticksToFoodCreationSize;

    private Runnable envThread = () -> {
        int i = 0;
        while (true) {
            try {
                System.out.println (i++);
                if (isRunning ()) {
                    onTickAction ();
                }
                Thread.sleep (42);
            } catch (InterruptedException e) {
                System.out.println (e);
            }
        }
    };

    public ArrayList<Organism> totalPopulation() {
        ArrayList<Organism> combinedPopulation = new ArrayList<> ();
        combinedPopulation.addAll (Environment.simpleOrganisms);
        combinedPopulation.addAll (Environment.speedMutOrganisms);
        combinedPopulation.addAll (Environment.foodRequiredMutatedOrganisms);
        return combinedPopulation;
    }

    private Thread thread = new Thread (envThread);
    private boolean running = false;

    public Environment(Config config, int width, int height, CanvasController canvasController) {
        this.canvasController = canvasController;
        this.width = width;
        this.height = height;
        this.config = config;

        for (int i = 0; i < config.orgNo; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
            simpleOrganisms.add (new Organism (config.orgSize, config.orgSpeed, config.orgFood, x, y));
        }
        for (int i = 0; i < config.sizeMONo; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
            sizeMutOrganisms.add (new SizeMutatedOrganism (config.mutSize, config.orgSpeed, config.orgFood, x, y));
        }
        for (int i = 0; i < config.speedMONo; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
            speedMutOrganisms.add (new SpeedMutatedOrganism (config.orgSize, config.mutSpeed, config.orgFood, x, y));
        }
        for (int i = 0; i < config.foodMONo; i++) {
            int x = rnd.nextInt (width);
            int y = rnd.nextInt (height);
            foodRequiredMutatedOrganisms.add (new FoodRequiredMutatedOrganism (config.orgSize, config.orgSpeed, config.mutFoodReq, x, y));
        }
        canvasController.draw (simpleOrganisms, foods, sizeMutOrganisms, speedMutOrganisms, foodRequiredMutatedOrganisms);
    }

    private void onTickAction() {
        for (Organism organism : simpleOrganisms) {
            organism.move (foods);
            for (Iterator<FoodItem> iterator = foods.iterator (); iterator.hasNext (); ) {
                FoodItem foodItem = iterator.next ();
                if (Math.abs (foodItem.x - organism.x) < organism.size / 2 / Math.sqrt (2) &&
                        Math.abs (foodItem.y - organism.y) < organism.size / 2 / Math.sqrt (2)) {
                    organism.increaseEnergyOnConsumption ();
                    iterator.remove ();
                }
            }
            organism.decreaseEnergyOnMove ();
        }
        for (SizeMutatedOrganism sizeMutatedOrganism : sizeMutOrganisms) {
            sizeMutatedOrganism.move (foods, totalPopulation ());
            foods.removeIf (foodItem ->
                    Math.abs (foodItem.x - sizeMutatedOrganism.x) < sizeMutatedOrganism.size / 2 / Math.sqrt (2) &&
                            Math.abs (foodItem.y - sizeMutatedOrganism.y) < sizeMutatedOrganism.size / 2 / Math.sqrt (2));
            simpleOrganisms.removeIf (organism ->
                    Math.abs (organism.x - sizeMutatedOrganism.x) < sizeMutatedOrganism.size / 2 / Math.sqrt (2) &&
                            Math.abs (organism.y - sizeMutatedOrganism.y) < sizeMutatedOrganism.size / 2 / Math.sqrt (2));
            speedMutOrganisms.removeIf (speedMutatedOrganism ->
                    Math.abs (speedMutatedOrganism.x - sizeMutatedOrganism.x) < sizeMutatedOrganism.size / 2 / Math.sqrt (2) &&
                            Math.abs (speedMutatedOrganism.y - sizeMutatedOrganism.y) < sizeMutatedOrganism.size / Math.sqrt (2));
            foodRequiredMutatedOrganisms.removeIf (foodRequiredMutatedOrganism ->
                    Math.abs (foodRequiredMutatedOrganism.x - sizeMutatedOrganism.x) < sizeMutatedOrganism.size / 2 / Math.sqrt (2) &&
                            Math.abs (foodRequiredMutatedOrganism.y - sizeMutatedOrganism.y) < sizeMutatedOrganism.size / 2 / Math.sqrt (2));
        }
        for (SpeedMutatedOrganism speedMutatedOrganism : speedMutOrganisms) {
            speedMutatedOrganism.move (foods);
            foods.removeIf (foodItem ->
                    Math.abs (foodItem.x - speedMutatedOrganism.x) < speedMutatedOrganism.size / 2 / Math.sqrt (2) &&
                            Math.abs (foodItem.y - speedMutatedOrganism.y) < speedMutatedOrganism.size / 2 / Math.sqrt (2)
            );
        }
        for (FoodRequiredMutatedOrganism foodRequiredMutatedOrganism : foodRequiredMutatedOrganisms) {
            foodRequiredMutatedOrganism.move (foods);
            foods.removeIf (foodItem ->
                    Math.abs (foodItem.x - foodRequiredMutatedOrganism.x) < foodRequiredMutatedOrganism.size / 2 / Math.sqrt (2) &&
                            Math.abs (foodItem.y - foodRequiredMutatedOrganism.y) < foodRequiredMutatedOrganism.size / 2 / Math.sqrt (2)
            );
        }
        ArrayList<Organism> organismsToDelete = new ArrayList<> ();
        ArrayList<Organism> organismsToAdd = new ArrayList<> ();
        for(Organism organism:simpleOrganisms){
            if (organism.getEnergy () <= 0) {
                organismsToDelete.add (organism);
            } else if (organism.getEnergy () >= Organism.MAX_ENERGY) {
                double currentX = organism.x;
                double currentY = organism.y;
                organismsToDelete.add (organism);
                organismsToAdd.add (new Organism (config.orgSize, config.orgSpeed, config.orgFood, currentX, currentY));
                organismsToAdd.add (new Organism (config.orgSize, config.orgSpeed, config.orgFood, currentX, currentY));
            }
        }
        for(Organism organism:organismsToDelete){
            simpleOrganisms.remove(organism);
        }
        simpleOrganisms.addAll (organismsToAdd);
        if (ticksToFoodCreation == 0) {
            addFoodItem (width, height);
            ticksToFoodCreation = ticksToFoodCreationSize;
        } else {
            ticksToFoodCreation--;
        }
        canvasController.draw (simpleOrganisms, foods, sizeMutOrganisms, speedMutOrganisms, foodRequiredMutatedOrganisms);
    }

    private void addFoodItem(int width, int height) {
        int x = rnd.nextInt (width);
        int y = rnd.nextInt (height);
        foods.add (new FoodItem (x, y));
    }

    public void tick() {

        thread.start ();
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

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}

package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class CanvasController {

    public Canvas Plane;
    public Button butDraw;
    public Environment env;
    public Button butStop;

    public void draw(List<Organism> population,
                     ArrayList<FoodItem> foods,
                     ArrayList<SizeMutatedOrganism> sizeMutOrganisms,
                     ArrayList<SpeedMutatedOrganism> speedMutatedOrganisms,
                     ArrayList<FoodRequiredMutatedOrganism> foodRequiredMutatedOrganisms) {
        GraphicsContext gc = Plane.getGraphicsContext2D ();
        gc.clearRect (0, 0, Main.Width, Main.Height);
        for (Organism organism : population) {
            double x = organism.x - (organism.size >> 1);
            double y = organism.y - (organism.size >> 1);
            gc.strokeOval (x, y, organism.size, organism.size);
        }
        for (FoodItem foodItem : foods) {
            gc.fillRect (foodItem.x - 1, foodItem.y - 1, 2, 2);
        }
        for (SizeMutatedOrganism sizeMutatedOrganism : sizeMutOrganisms) {
            double x = sizeMutatedOrganism.x - (sizeMutatedOrganism.size >> 1);
            double y = sizeMutatedOrganism.y - (sizeMutatedOrganism.size >> 1);
            gc.fillOval (x, y, sizeMutatedOrganism.size, sizeMutatedOrganism.size);
        }
        for (SpeedMutatedOrganism speedMutatedOrganism : speedMutatedOrganisms ){
            double x = speedMutatedOrganism.x - (speedMutatedOrganism.size >> 1);
            double y = speedMutatedOrganism.y - (speedMutatedOrganism.size >> 1);
            gc.fillRect (x, y, speedMutatedOrganism.size, speedMutatedOrganism.size);
        }
        for (FoodRequiredMutatedOrganism foodRequiredMutatedOrganism : foodRequiredMutatedOrganisms){
            double x = foodRequiredMutatedOrganism.x - (foodRequiredMutatedOrganism.size >> 1);
            double y = foodRequiredMutatedOrganism.y - (foodRequiredMutatedOrganism.size >> 1);
            gc.strokeRect (x, y, foodRequiredMutatedOrganism.size, foodRequiredMutatedOrganism.size);
        }
    }

    public void pressDraw() {
        env.startEnv ();
    }

    public void pressStop() {
        env.stopEnv ();
    }

    public void initialize() {
        Config config = Main.getInstance ().getConfig ();
        env = new Environment (config, Main.Width, Main.Height, this);
        env.tick ();
    }

    public void stop() {
        env.stop ();
    }
}

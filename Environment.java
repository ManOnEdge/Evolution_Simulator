package sample;


import java.util.ArrayList;
import java.util.Random;

public class Environment {
    int height;
    int width;
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
}

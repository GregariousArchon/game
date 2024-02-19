package dev.kapil.models;
import java.util.Random;

public class Dice {
    Random random;
    public Dice(){
        random = new Random();
    }

    public int getNumberOfDice() {
        return random.nextInt(7);
    }
}

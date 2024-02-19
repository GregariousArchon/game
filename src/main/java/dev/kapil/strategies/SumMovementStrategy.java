package dev.kapil.strategies;

public class SumMovementStrategy implements MovementStrategy{


    @Override
    public int getEndPosition(int[] dices) {
        int res = 0;
        for(int i : dices) {
            res += dices[i];
        }
        return res;
    }
}

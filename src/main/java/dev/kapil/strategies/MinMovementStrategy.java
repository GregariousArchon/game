package dev.kapil.strategies;

public class MinMovementStrategy  implements MovementStrategy{
    @Override
    public int getEndPosition(int[] dices) {
        int min = Integer.MAX_VALUE;
        for(int i : dices) {
            min = Math.min(dices[i], min);
        }
        return min;
    }
}

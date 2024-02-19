package dev.kapil.strategies;

public class MaxMovementStrategy implements MovementStrategy{
    @Override
    public int getEndPosition(int[] dices) {
        int max = Integer.MIN_VALUE;
        for(int i : dices) {
            max = Math.max(dices[i], max);
        }
        return max;
    }
}

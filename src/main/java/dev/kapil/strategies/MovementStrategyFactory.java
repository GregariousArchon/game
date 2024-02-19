package dev.kapil.strategies;

public class MovementStrategyFactory {
    public static MovementStrategy getMovementStrategy(String movementStrategy) {
        switch(movementStrategy) {
            case "sum" -> {return new SumMovementStrategy();}
            case "max" -> {return new MaxMovementStrategy();}
            case "min" -> {return new MinMovementStrategy();}
        }
        return new SumMovementStrategy();
    }
}

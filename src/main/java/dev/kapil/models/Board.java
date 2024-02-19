package dev.kapil.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board extends BaseModel{
    private int size;
    private List<Cell> cells;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public Board(int dimension, List<Snake> snakes, List<Ladder> ladders) {
        this.size = dimension*dimension;
        cells = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            cells.add(new EmptyCell());
        }
        this.snakes = new HashMap<>();
        for(Snake s : snakes) {
            this.snakes.put(s.getStartIndex(), s.nextLocation());
            cells.remove(s.getStartIndex());
            cells.add(s.getStartIndex(), new Snake(s.getStartIndex(), s.nextLocation()));
        }
        this.ladders = new HashMap<>();
        for(Ladder l : ladders) {
            this.ladders.put(l.getStartIndex(), l.nextLocation());
            cells.remove(l.getStartIndex());
            cells.add(l.getStartIndex(), new Ladder(l.getStartIndex(), l.nextLocation()));
        }
    }

    public Map<Integer, Integer> getSnakes() {
        return this.snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return this.ladders;
    }
}

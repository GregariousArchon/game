package dev.kapil.models;

public class Ladder  extends Cell {
    private int startIndex;
    private int endIndex;

    public Ladder(int startIndex, int endIndex) {
        super(CellType.LADDER);
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public int nextLocation() {
        return this.endIndex;
    }
}

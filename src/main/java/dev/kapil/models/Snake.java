package dev.kapil.models;

public class Snake extends Cell{
    private int startIndex;
    private int endIndex;

    public Snake(int startIndex, int endIndex) {
        super(CellType.SNAKE);
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public int nextLocation() {
        return endIndex;
    }
}

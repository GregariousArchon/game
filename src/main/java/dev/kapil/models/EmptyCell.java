package dev.kapil.models;

public class EmptyCell extends Cell {

    EmptyCell() {
        super(CellType.EMPTY);
    }

    @Override
    public int nextLocation() {
        return this.getIndex();
    }
}

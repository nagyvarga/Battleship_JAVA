package battleship;

import java.util.Arrays;

public class Ship {
    final private ShipType type;
    Coordinate beginning;
    Coordinate end;
    Coordinate[] parts;
    boolean[] hitParts;

    Ship (ShipType type, Coordinate beginning, Coordinate end) {
        this.type = type;
        this.beginning = beginning;
        this.end = end;
        int rowInc = (end.getRow() - beginning.getRow()) / (type.getSize() - 1);
        int colInc = (end.getCol() - beginning.getCol()) / (type.getSize() - 1);

        parts = new Coordinate[type.getSize()];

        parts[0] = beginning;
        for (int i = 1; i < type.getSize(); i++) {
            parts[i] = new Coordinate(beginning.getRow() + i * rowInc, beginning.getCol() + i * colInc);
        }

        hitParts = new boolean[type.getSize()];
        for (int i = 0; i < type.getSize(); i++) {
            hitParts[i] = false;
        }
    }

    public ShipType getType () {
        return type;
    }

    public boolean isSunk() {
        boolean isSunk = true;
        for (boolean hitPart : hitParts) {
            isSunk &= hitPart;
        }
        return isSunk;
    }

    public Coordinate[] getParts() {
        return parts.clone();
    }

}

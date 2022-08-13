package battleship;

public class Coordinate {

    enum Rows {A, B, C, D, E, F, G, H, I, J}

    private int row;
    private int col;

    public Coordinate(String coords) {
        boolean isException = false;
        char rowInChar = coords.charAt(0);
        if (rowInChar >= 'A' && rowInChar <= 'J') {
            this.row = rowInChar - 'A';
        } else {
            isException = true;
        }
        try {
            col = Integer.parseInt(coords.substring(1)) - 1;
        } catch (Exception e) {
            isException = true;
        }
        if (col < 0 || col > 9) {
            isException = true;
        }
        if (isException) {
            throw new RuntimeException("Error! Wrong coordinates!");
        }
    }

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isEqual(Coordinate other) {
        return row == other.getRow() && col == other.getCol();
    }
}

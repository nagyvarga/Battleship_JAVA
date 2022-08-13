package battleship;

public class Board {

    private int[][] fields;
    final int num = 10;
    Player player;

    public Board() {
        this.fields = new int[10][10];
    }

    public void printBoard(boolean showShips) {
        System.out.print(" ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(" " + i);
        }
        System.out.print("\n");
        char start = 'A';
        for (int i = 0; i < num; i++) {
            System.out.print((char) (start + i));
            for (int j = 0; j < num; j++) {
                Coordinate coord = new Coordinate(i, j);
                char tempChar = getFields(i, j);
                if (showShips && fields[i][j] == 0) {
                    if (player.fleet.isPartOfShip(coord)) {
                        tempChar = 'O';
                    }
                }
                System.out.print(" " + tempChar);
            }
            System.out.print("\n");
        }
    }

    private char getFields(int row, int col) {
        char symbol = ' ';
        switch (fields[row][col]) {
            case 0:
                symbol = '~';
                break;
            case 1:
                symbol = 'O';
                break;
            case 2:
                symbol = 'X';
                break;
            case 3:
                symbol = 'M';
                break;
        }
        return symbol;
    }

    public boolean isEmptyField(Coordinate coord) {
        return fields[coord.getRow()][coord.getCol()] == 0;
    }

    public void addPropertyToFields(Coordinate coord, int property) {
        fields[coord.getRow()][coord.getCol()] = property;
    }

}

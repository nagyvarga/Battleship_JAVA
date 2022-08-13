package battleship;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fleet {
    private List<Ship> ships;
    private Board board;

    public Fleet(Board board) {
        this.board = board;
    }

    public void initFleet() {
        ships = new ArrayList<>();
        for (ShipType type : ShipType.values()) {
            placeShipToBoard(type);
        }
    }

    public boolean isPartOfShip(Coordinate coord) {
        for (Ship ship : ships) {
            for (Coordinate part : ship.getParts()) {
                if (part.isEqual(coord)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Ship hitPart(Coordinate coord) {
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getParts().length; i++) {
                if (coord.isEqual(ship.getParts()[i])) {
                    ship.hitParts[i] = true;
                    return ship;
                }
            }
        }
        return null;
    }

    public boolean isAllShipsSunk() {
        boolean isAllSunk = true;
        for (Ship ship : ships) {
            isAllSunk &= ship.isSunk();
        }
        return isAllSunk;
    }

    private void placeShipToBoard(ShipType type) {
        Scanner scanner = new Scanner(System.in);
        Coordinate beginning;
        Coordinate end;
        boolean isError;
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", type.getName(), type.getSize());

        do {
            isError = false;
            beginning = new Coordinate(scanner.next());
            end = new Coordinate(scanner.next());

            if (isAngled(beginning, end)) {
                isError = true;
                System.out.println("\nError! Wrong ship location! Try again:\n");
            } else if (!isValidLength(type, beginning, end)) {
                isError = true;
                System.out.printf("\nError! Wrong length of the %s! Try again:\n\n", type.getName());
            } else {
                Ship tempShip = new Ship(type, beginning, end);
                boolean isTooClose = false;
                for (Coordinate item : tempShip.getParts()) {
                    isTooClose |= isClose(item);
                }
                if (isTooClose) {
                    isError = true;
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                }
            }


        } while (isError);

        ships.add(new Ship(type, beginning, end));
        System.out.println();
        board.printBoard(true);

    }

    private boolean isValidLength(ShipType type, Coordinate beginning, Coordinate end) {
        int calculateLength = 0;
        if (beginning.getRow() == end.getRow()) {
            if (beginning.getCol() != end.getCol()) {
                calculateLength = Math.abs(beginning.getCol() - end.getCol()) + 1;
            }
        } else {
            if (beginning.getCol() == end.getCol()) {
                calculateLength = Math.abs(beginning.getRow() - end.getRow()) + 1;
            }
        }
        return calculateLength == type.getSize();
    }

    private boolean isClose(Coordinate coord) {
        boolean isClose;
        for (Ship ship : ships) {
            for (Coordinate part : ship.getParts()) {
                isClose = coord.getRow() >= (part.getRow() - 1) && coord.getRow() <= (part.getRow() + 1) &&
                        coord.getCol() >= (part.getCol() - 1) && coord.getCol() <= (part.getCol() + 1);
                if (isClose) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAngled(Coordinate beginning, Coordinate end) {
        return beginning.getRow() != end.getRow() && beginning.getCol() != end.getCol();
    }

}

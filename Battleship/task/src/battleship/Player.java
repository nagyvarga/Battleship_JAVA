package battleship;

import java.util.Scanner;

public class Player {
    String name;

    Board board;
    Fleet fleet;

    Player (String name, Board board) {
        this.name = name;
        this.board = board;
        fleet = new Fleet(this.board);
    }

    public void shoot(Player opponent) {
        boolean isException;
        Scanner scanner = new Scanner(System.in);
        Coordinate coord = null;
        System.out.printf("\n%s, it's your turn:\n\n", name);

        do {
            isException = false;
            try {
                coord = new Coordinate(scanner.next());
            } catch (RuntimeException e) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
                isException = true;
            }
        } while(isException);

        String msg = "";
        int property;
        if (opponent.fleet.isPartOfShip(coord)) {
            Ship ship = opponent.fleet.hitPart(coord);
            if (opponent.fleet.isAllShipsSunk()) {
                msg = "\nYou sank the last ship. You won. Congratulations!";
            } else {
                if (ship.isSunk()) {
                    msg = "\nYou sank a ship!";
                } else {
                    msg = "\nYou hit a ship!";
                }
            }
            property = 2;

        } else {
            property = 3;
            msg = "\nYou missed!";
        }

        opponent.board.addPropertyToFields(coord, property);
        System.out.println(msg);
    }

    public String getName() {
        return name;
    }

}

package battleship;

import java.io.InputStream;

public class Game {
    Board boardOne;
    Board boardTwo;
    Player playerOne;
    Player playerTwo;

    Game(String nameOne, String nameTwo) {
        boardOne = new Board();
        playerOne = new Player(nameOne, boardOne);
        boardOne.player = playerOne;

        boardTwo = new Board();
        playerTwo = new Player(nameTwo, boardTwo);
        boardTwo.player = playerTwo;
    }

    public void init() {
        initialize(playerOne, boardOne);
        System.out.println();
        pressEnter();
        initialize(playerTwo, boardTwo);
    }

    private void initialize(Player player, Board board) {
        System.out.printf("%s, place your ships on the game field\n\n", player.getName());
        board.printBoard(false);
        player.fleet.initFleet();
    }

    public void play() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        pressEnter();

        while (true) {
            printBoards(playerOne, playerTwo);
            playerOne.shoot(playerTwo);
            if (playerOne.fleet.isAllShipsSunk()) {
                break;
            }

            pressEnter();

            printBoards(playerTwo, playerOne);
            playerTwo.shoot(playerOne);
            if (playerTwo.fleet.isAllShipsSunk()) {
                break;
            }

            pressEnter();
        }
    }

    private void printBoards(Player player, Player opponent) {
        opponent.board.printBoard(false);
        System.out.println("---------------------");
        player.board.printBoard(true);
    }

    private static void pressEnter() {
        System.out.println("Press Enter and pass the move to another player");
        try{System.in.read();}
        catch(Exception e){}
    }
}

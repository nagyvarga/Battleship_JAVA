package battleship;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("Player 1", "Player 2");
        game.init();
        game.play();
    }
}

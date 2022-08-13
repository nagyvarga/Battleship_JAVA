package battleship;

public enum ShipType {
    Aircraft ("Aircraft Carrier", 5),
    Battleship ("Battleship", 4),
    Submarine ("Submarine", 3),
    Cruiser ("Cruiser", 3),
    Destroyer ("Destroyer", 2);

    final String name;
    final int size;

    ShipType (String name, int size) {
        this.name = name;
        this.size = size;

    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }
}

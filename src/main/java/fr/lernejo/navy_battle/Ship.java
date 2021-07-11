package fr.lernejo.navy_battle;

public class Ship {
    final protected String name;
    final protected int size;

    protected Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
    public String getName() {
        return this.name;
    }

    public Boolean isOk(Game game) {
        for(int i = 0; i < game.get_grid()[0].length ; i++) {
            for (int j = 0; j < game.get_grid().length; j++) {
                Ship ship = game.get_grid()[i][j];
                if(ship != null && ship.getName().equals(this.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Ship[] createShips() {
        Ship aircraftCarrier = new Ship("aircraft-carrier", 5);
        Ship cruiser = new Ship("cruiser", 4);
        Ship destroyer1 = new Ship("destroyer-1", 3);
        Ship destroyer2 = new Ship("destroyer-2", 3);
        Ship torpedoBoat = new Ship("torpedo-boat", 2);

        Ship[] ships = {torpedoBoat, destroyer1, destroyer2, cruiser, aircraftCarrier};

        return ships;
    }


}

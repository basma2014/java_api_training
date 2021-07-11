package fr.lernejo.navy_battle;

public class ShipCreate {

    public Ship[] shipCreate()
    {
        Ship aircraftCarrier = new Ship("aircraft-carrier", 5);
        Ship cruiser = new Ship("cruiser", 4);
        Ship destroyer_1 = new Ship("destroyer-1", 3);
        Ship destroyer_2 = new Ship("destroyer-2", 3);
        Ship torpedoBoat = new Ship("torpedo-boat", 2);

        Ship[] ships = {torpedoBoat, destroyer_1, destroyer_2, cruiser, aircraftCarrier};

        return ships;
    }
}

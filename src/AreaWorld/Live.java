package AreaWorld;

import AreaWorld.Objects.People;
import AreaWorld.Objects.Population;

import java.awt.*;

public class Live {
    private World world;

    Live(World world) throws InterruptedException {
        this.world = world;
        liveCicule();
    }

    private void liveCicule() throws InterruptedException {
        Coord coordPeople = world.getRandomCoordinats();
        world.createPeople(coordPeople.x, coordPeople.y);
        Coord coord = world.getRandomFreeCoordinate();
        world.createFood(coord.x, coord.y);
        while (true) {
            Thread.sleep(1000);
            coordPeople = world.movePrime(coordPeople.x, coordPeople.y,  Course.RIGHT);
        }
    }
}

package AreaWorld.Objects;

import AreaWorld.Prime;

import java.awt.*;

public class People extends Prime {
    private int x;
    private int y;

    public People(int x, int y) {
        super(Color.magenta, PrimeTypes.PEOPLE);
        this.x = x;
        this.y = y;
    }



}

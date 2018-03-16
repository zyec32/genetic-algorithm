package AreaWorld;

import AreaWorld.Objects.PrimeTypes;

import java.awt.*;

public class Prime {
    private Color color;
    private PrimeTypes type;

    public Color getColor() {
        return color;
    }
    public PrimeTypes getType() {
        return type;
    }

    public Prime(Color color, PrimeTypes type){
        this.color = color;
        this.type = type;
    }
}

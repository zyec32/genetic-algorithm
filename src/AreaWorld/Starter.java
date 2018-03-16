package AreaWorld;

import AreaWorld.Objects.Wall;

import javax.swing.*;

public class Starter {
    private static final int startW = 20;
    private static final int startH = 40;
    private static final int sizeW = 15;
    private static final int sizeH = 15;
    private static final int space = 1;
    private static final int countW = 90;
    private static final int countH = 50;

    public static void main(String[] args) throws InterruptedException {
        World world = new World(countW, countH);

        JFrame frame = new JFrame("Evol");
        Area area = new Area(sizeW, sizeH, space, world);
        world.setArea(area);
        frame.add(area);
        frame.setSize(startW+countW*(sizeW+ space), startH+countH*(sizeH+ space));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Live live = new Live(world);

    }
}

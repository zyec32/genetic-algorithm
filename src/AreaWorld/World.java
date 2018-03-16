package AreaWorld;

import AreaWorld.Objects.*;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.Random;

public class World {
    private Prime[][] worldMatrix;
    private int width;
    private int height;
    private Area area;

    public void setArea(Area area) {
        this.area = area;
    }
    public Prime[][] getMatrix() {
        return worldMatrix;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    World(int W, int H) throws InterruptedException {
        worldMatrix = new Prime[W][H];
        width = W;
        height = H;
        fillAll(new Empty());
        buildFence(new Wall());
    }

    private void fillAll(Prime prime) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                worldMatrix[i][j] = prime;
            }
        }
    }

    private void buildFence(Prime prime) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || j == 0 || i == width-1 || j == height-1) {
                    worldMatrix[i][j] = prime;
                }
            }
        }
    }

    private void addPrime(Prime prime, int x, int y) {
        worldMatrix[x][y] = prime;
        area.setMatrix(worldMatrix);
    }

    public void clear(int x, int y) {
        addPrime(new Empty(), x, y);
    }
    public void createWall(int x, int y) {
        addPrime(new Wall(), x, y);
    }
    public void createFood(int x, int y) {
        addPrime(new Food(), x, y);
    }
    public void createPoison(int x, int y) {
        addPrime(new Poison(), x, y);
    }
    public void createPeople(int x, int y) {
        addPrime(new People(x, y), x, y);
    }

    //TODO factory pattern (abstrakt factory)

    //Observable (pull list, add subsc)
    /*
    мир подписчик налюдей
    человек имеет координаты
    при изменении координат человека мир его двигает
    (1 ~ many где 1 меняется при изменении любого мэни)
    субъект - чел
    наблюдатель - мир
    (диаграммы реального времени)

    субъект наблюдатель
    субъект аттчить, (деаттач не надо), нотификация


    */
    public Coord movePrime(int x, int y, int newx, int newy) {
        worldMatrix[newx][newy] = worldMatrix[x][y];
        if (!(x == newx && y == newy)) {
            clear(x, y);
        }
        area.setMatrix(worldMatrix);
        return new Coord(newx, newy);
    }
    public Coord movePrime(int x, int y, Course course) {
        int newx = x;
        int newy = y;
        switch (course) {
            case UP:
                newy = y - 1;
                break;
            case RIGHT:
                newx = x + 1;
                break;
            case DOWN:
                newy = y + 1;
                break;
            case LEFT:
                newx = x - 1;
        }
        movePrime(x, y, newx, newy);
        return new Coord(newx, newy);
    }

    public Coord getRandomCoordinats() {
        Random rnd = new Random();
        Coord coord = new Coord(rnd.nextInt(width-2)+1, rnd.nextInt(height-2)+1);
        return coord;
    }
    public Coord getRandomFreeCoordinate() {
        Coord coord = getRandomCoordinats();
        if (worldMatrix[coord.x][coord.y].getType() == PrimeTypes.EMPTY) {
            return  coord;
        } else {
            for (int i = 1; i < width-1; i++) {
                for (int j = coord.y; j < height-1; j++) {
                    if (worldMatrix[i][j].getType() == PrimeTypes.EMPTY) {
                        coord.x = i;
                        coord.y = j;
                        return coord;
                    }
                }
            }
        }
        return coord;
    }
    /*
    при изменении ячейки мы обнавляем массив свободных ячеек
    ищем по свободным ячейкам
     */

}

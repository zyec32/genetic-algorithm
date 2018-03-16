import java.awt.*;

public class Point {
    boolean visible;
    Color color;
    PointTypes type;
    int x, y;

    Point(boolean visible, Color  color, PointTypes type, int x, int y) {
        this.visible = visible;
        this.color = color;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCoordinats(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointTypes getType() {
        return type;
    }
}

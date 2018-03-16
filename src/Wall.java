import java.awt.*;

public class Wall extends Point {
    Wall(int x, int y) {
        super(true, Color.blue, PointTypes.WALL, x, y);
    }
}

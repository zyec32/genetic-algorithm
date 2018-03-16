import java.awt.*;

public class Food extends Point {
    int volume;
    Food(int x , int y, int volume) {
        super(true, Color.green.brighter(), PointTypes.FOOD, x, y);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}
